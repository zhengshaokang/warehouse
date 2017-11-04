package com.hys.commons.concurrent.poolutil;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hys.commons.concurrent.TaskQueue;
import com.hys.commons.concurrent.TaskThreadFactory;
import com.hys.commons.concurrent.TaskThreadPoolExecutor;
import com.hys.commons.conf.ProfileManager;
import com.hys.commons.util.Pair;

/**
 * 可以并发提交任务，每次提交一个任务后会返回一个独一无二的编号，可以通过这个编号来索引到任务
 * 
 */
public class ConcurrentCalculator<T>
{
    private static final Logger BUSI_LOG = LoggerFactory.getLogger("concurrentcalculatorv2_busi");

    private final TaskThreadPoolExecutor exec;
    private final Map<Long, Pair<Long, FutureTask<T>>> tasks = new ConcurrentHashMap<Long, Pair<Long, FutureTask<T>>>();// 放置时间戳和任务。时间戳用于超时扫描检查这个任务是不是没有被去掉，以免map膨胀
    private final AtomicLong currentSeqNo = new AtomicLong(0);
    private int resultTimeout = 4000;// 超时时间4秒
    private boolean isClose = false;// 是否被关闭了

    private static final int EXPIRE_INTERVAL = 20 * 1000;// 一个任务超时时间，这里设置20秒，超过这个时间就有可能被从map中清理掉
    private final Thread removeThread;// 一个对象要多一个线程用于定时删除过时记录

    private static ConcurrentCalculator<Object> concurrentCalculator = new ConcurrentCalculator<Object>();

    public static ConcurrentCalculator<Object> getIntance()
    {
        return ConcurrentCalculator.concurrentCalculator;
    }

    /**
     * 初始化函数
     */
    public ConcurrentCalculator()
    {

        TaskQueue taskqueue = new TaskQueue(ProfileManager.getIntByKey("appcenter_petrel.concurrCal_taskQueueLength",
                2000));
        exec = new TaskThreadPoolExecutor(ProfileManager.getIntByKey("appcenter_petrel.concurrCal_corePoolSize", 20),
                ProfileManager.getIntByKey("appcenter_petrel.concurrCal_maxPoolSize", 120), ProfileManager.getIntByKey(
                        "appcenter_petrel.concurrCal_keepAliveTime", 120), TimeUnit.SECONDS, taskqueue,
                new TaskThreadFactory("cal-exec-"));
        taskqueue.setParent(exec);
        resultTimeout = ProfileManager.getIntByKey("appcenter_petrel.concurrCal_taskTimeout", 5000);

        removeThread = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (!isClose)
                {
                    try
                    {
                        Thread.sleep(1000 * 60);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    removeTimeoutTasks();
                }
            }
        });
        removeThread.start();
    }

    /**
     * 真正的并行工作方法 把一个一个任务分配到每一个线程中 设置后立即提交任务并处理 任务以key，value方式来索引最终的处理结果
     */
    public long submitTask(Callable<T> callable)
    {
        long seqno = currentSeqNo.incrementAndGet();
        // 默认根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
        FutureTask<T> task = new FutureTask<T>(callable);
        // 任务放入到hashtable，把每一个callalbe放入到线程池中执行
        tasks.put(seqno, Pair.makePair(System.currentTimeMillis(), task));
        exec.submit(task);
        return seqno;
    }

    /**
     * 返回任务合集 key是你提交时的key 任务未完成时会阻塞在此
     * 
     * @return
     */
    public T getResult(long seqNo)
    {
        T result = null;
        Pair<Long, FutureTask<T>> task = tasks.get(seqNo);
        if (task != null)
        {
            try
            {
                tasks.remove(seqNo);// 不管是否成功，查了一次就要删除
                result = task.second.get(resultTimeout, TimeUnit.MILLISECONDS);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            catch (ExecutionException e)
            {
                e.printStackTrace();
            }
            catch (TimeoutException e)
            {
                e.printStackTrace();
            }
        }

        if (ConcurrentCalculator.BUSI_LOG.isDebugEnabled())
        {
            ConcurrentCalculator.BUSI_LOG.debug("concurr getresult:" + seqNo + "\t" + result);
        }
        return result;
    }

    /**
     * 删除超时的记录
     */
    public void removeTimeoutTasks()
    {
        ConcurrentCalculator.BUSI_LOG.info("task node remove,start....");
        Iterator<Map.Entry<Long, Pair<Long, FutureTask<T>>>> iterator = tasks.entrySet().iterator();
        Long timeoutTime = System.currentTimeMillis() - ConcurrentCalculator.EXPIRE_INTERVAL;
        while (iterator.hasNext())
        {
            Map.Entry<Long, Pair<Long, FutureTask<T>>> node = iterator.next();
            if (node.getValue().first < timeoutTime)
            {
                ConcurrentCalculator.BUSI_LOG.info("task node remove,id:" + node.getKey());
                iterator.remove();
            }
        }
        ConcurrentCalculator.BUSI_LOG.info("task node remove,finish....");
    }

    private void close()
    {
        tasks.clear();
        exec.shutdown();
        isClose = true;
    }

    /**
     * main 测试方法
     * 
     * @param args
     */
    public static void main(String[] args) throws InterruptedException
    {
        ConcurrentCalculator<Object> concurrentCalculator = ConcurrentCalculator.getIntance();
        for (int i = 0; i < 100; i++)
        {
            final int ii = i;
            concurrentCalculator.submitTask(new Callable<Object>()
            {
                @Override
                public Object call() throws Exception
                {
                    for (int j = 0; j < 100; j++)
                    {
                        System.out.print("hello" + ii + "," + j + "   ");
                    }
                    System.out.println("");
                    return ii;
                }
            });

        }
        Thread.sleep(20000);
        concurrentCalculator.close();
    }

}
