package com.hys.commons.concurrent.poolutil;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hys.commons.concurrent.TaskQueue;
import com.hys.commons.concurrent.TaskThreadFactory;
import com.hys.commons.concurrent.TaskThreadPoolExecutor;
import com.hys.commons.conf.ProfileManager;
import com.hys.commons.logutil.LogUtil;
import com.hys.commons.util.TimeCostUtil;

/**
 * 异步处理管理器。上层仍一个异步处理的对象进来，这里进行处理。适合不需要返回结果的旁路数据的处理。 提交处理的数据并不进行持久化，适合不太重要信息的处理，例如写日志到数据库等这些次要逻辑
 * 
 */
public class AsyncProcessManager
{
    private static AsyncProcessManager process = new AsyncProcessManager();

    private static final Logger BUSI_LOG = LoggerFactory.getLogger("asyncproc_busi");
    public static final Logger PROCESS_LOG = LoggerFactory.getLogger("asyncproc_process");

    private final TaskThreadPoolExecutor exec;

    private AsyncProcessManager()
    {
        TaskQueue taskqueue = new TaskQueue(ProfileManager.getIntByKey(
                "maowu_threadpool_conf.async_process_queue_length", 20000));

        exec = new TaskThreadPoolExecutor(ProfileManager.getIntByKey(
                "maowu_threadpool_conf.async_process_core_pool_size", 20), ProfileManager.getIntByKey(
                "maowu_threadpool_conf.async_process_max_pool_size", 120), ProfileManager.getIntByKey(
                "maowu_threadpool_conf.async_process_keep_alive_time", 120), TimeUnit.SECONDS, taskqueue,
                new TaskThreadFactory("async-exec-"));
        taskqueue.setParent(exec);

        exec.setRejectedExecutionHandler(new RejectedExecutionHandler()
        {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor)
            {
                AsyncProcessManager.BUSI_LOG.error("AsyncProcessManager rejectedExecution r:" + r + ",task count:"
                        + executor.getTaskCount());
            }
        });
    }

    public static AsyncProcessManager getIntance()
    {
        return AsyncProcessManager.process;
    }

    public static void addTask(final Runnable runnable)
    {
        AsyncProcessManager.getIntance().exec.submit(new Runnable()// 再包一层是为了做些日志咯
                {
                    @Override
                    public void run()
                    {
                        TimeCostUtil timeCostUtil = new TimeCostUtil();
                        try
                        {
                            runnable.run();
                        }
                        catch (Exception ex)
                        {
                            LogUtil.debug(AsyncProcessManager.PROCESS_LOG, "[async process] error.runnable:"
                                    + runnable);
                        }
                        LogUtil.debug(AsyncProcessManager.PROCESS_LOG, "[async process] %s runnable:%s",
                                timeCostUtil, runnable);
                    }
                });
    }

    public static String getQueueInfo()
    {
        return "completed task:" + AsyncProcessManager.getIntance().exec.getCompletedTaskCount() + "queue size:"
                + AsyncProcessManager.getIntance().exec.getQueue().size() + ",task count:"
                + AsyncProcessManager.getIntance().exec.getTaskCount();
    }

}
