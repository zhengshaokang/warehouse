package com.hys.commons.logutil;

import org.slf4j.Logger;

/**
 * 输出debug及业务调试日志
 * 
 */
public class LogUtil
{

    private static String CLASS_NAME = LogUtil.class.getName();

    /**
     * 方便调试,直接打印日志堆栈
     * 
     * @param log
     *        日志对象
     * @param str
     *        格式化字符串 或者 日志消息
     * @param obj
     *        消息
     */
    public static void debug(Logger log, String str, Object... obj)
    {
        if (log.isDebugEnabled())
        {
            String prefix = "";
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            if (stackTraceElements != null && stackTraceElements.length > 2)
            {
                int deep = 2;
                String c = stackTraceElements[deep].getClassName();
                if (c != null && c.contains(CLASS_NAME))
                {
                    deep = 3;
                    c = stackTraceElements[deep].getClassName();
                }
                if (c.contains("."))
                {
                    c = c.substring(c.lastIndexOf('.') + 1);
                }
                prefix = c + "." + stackTraceElements[deep].getMethodName() + " line:"
                        + stackTraceElements[deep].getLineNumber();
            }
            try
            {
                if (obj != null)
                {
                    log.debug(prefix + " " + String.format(str, obj));
                }
                else
                {
                    log.debug(prefix + " " + str);
                }
            }
            catch (Exception ex)
            {
                log.debug(prefix + " format message error: " + str);
            }
        }
    }

    /**
     * 方便调试,直接打印日志堆栈
     * 
     * @param log
     *        日志对象
     * @param str
     *        格式化字符串 或者 日志消息
     * @param obj
     *        消息
     */
    public static void info(Logger log, String str, Object... obj)
    {
        if (log.isInfoEnabled())
        {
            String prefix = "";
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            if (stackTraceElements != null && stackTraceElements.length > 2)
            {
                int deep = 2;
                String c = stackTraceElements[deep].getClassName();
                if (c != null && c.contains(CLASS_NAME))
                {
                    deep = 3;
                    c = stackTraceElements[deep].getClassName();
                }
                if (c.contains("."))
                {
                    c = c.substring(c.lastIndexOf('.') + 1);
                }
                prefix = c + "." + stackTraceElements[deep].getMethodName() + " line:"
                        + stackTraceElements[deep].getLineNumber();
            }
            try
            {
                if (obj != null)
                {
                    log.info(prefix + " " + String.format(str, obj));
                }
                else
                {
                    log.info(prefix + " " + str);
                }
            }
            catch (Exception ex)
            {
                log.info(prefix + " format message error: " + str);
            }
        }
    }
}
