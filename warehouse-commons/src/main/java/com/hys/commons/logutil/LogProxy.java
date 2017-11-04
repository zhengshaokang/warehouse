package com.hys.commons.logutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

/**
 * 方便调试,直接打印日志堆栈
 * 
 */
public class LogProxy implements Logger
{
    private final Logger log;

    private LogProxy(Class<?> clazz)
    {
        this.log = LoggerFactory.getLogger(clazz);
    }

    public static Logger getLogger(Class<?> clazz)
    {
        return new LogProxy(clazz);
    }

    @Override
    public void error(String msg, Throwable e)
    {
        log.error(msg + new ThrowableInfo(e).getThrowableStr());
    }

    @Override
    public String getName()
    {
        return log.getName();
    }

    @Override
    public boolean isTraceEnabled()
    {
        return log.isTraceEnabled();
    }

    @Override
    public void trace(String msg)
    {
        log.trace(msg);
    }

    @Override
    public void trace(String format, Object arg)
    {
        log.trace(format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2)
    {
        log.trace(format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments)
    {
        log.trace(format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t)
    {
        log.trace(msg, t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker)
    {
        return log.isTraceEnabled(marker);
    }

    @Override
    public void trace(Marker marker, String msg)
    {
        log.trace(marker, msg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg)
    {
        log.trace(marker, format, arg);
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2)
    {
        log.trace(marker, format, arg1, arg2);
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray)
    {
        log.trace(marker, format, argArray);
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t)
    {
        log.trace(marker, msg, t);
    }

    @Override
    public boolean isDebugEnabled()
    {
        return log.isDebugEnabled();
    }

    @Override
    public void debug(String msg)
    {
        log.debug(msg);
    }

    @Override
    public void debug(String format, Object arg)
    {
        log.debug(format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2)
    {
        log.debug(format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments)
    {
        log.debug(format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t)
    {
        log.debug(msg, t);
    }

    @Override
    public boolean isDebugEnabled(Marker marker)
    {
        return log.isDebugEnabled(marker);
    }

    @Override
    public void debug(Marker marker, String msg)
    {
        log.debug(marker, msg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg)
    {
        log.debug(marker, format, arg);
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2)
    {
        log.debug(marker, format, arg1, arg2);
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments)
    {
        log.debug(marker, format, arguments);
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t)
    {
        log.debug(marker, msg, t);
    }

    @Override
    public boolean isInfoEnabled()
    {
        return log.isInfoEnabled();
    }

    @Override
    public void info(String msg)
    {
        log.info(msg);
    }

    @Override
    public void info(String format, Object arg)
    {
        log.info(format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2)
    {
        log.info(format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments)
    {
        log.info(format, arguments);
    }

    @Override
    public void info(String msg, Throwable t)
    {
        log.info(msg, t);
    }

    @Override
    public boolean isInfoEnabled(Marker marker)
    {
        return log.isInfoEnabled(marker);
    }

    @Override
    public void info(Marker marker, String msg)
    {
        log.info(marker, msg);
    }

    @Override
    public void info(Marker marker, String format, Object arg)
    {
        log.info(marker, format, arg);
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2)
    {
        log.info(marker, format, arg1, arg2);
    }

    @Override
    public void info(Marker marker, String format, Object... arguments)
    {
        log.info(marker, format, arguments);
    }

    @Override
    public void info(Marker marker, String msg, Throwable t)
    {
        log.info(marker, msg, t);
    }

    @Override
    public boolean isWarnEnabled()
    {
        return log.isWarnEnabled();
    }

    @Override
    public void warn(String msg)
    {
        log.warn(msg);
    }

    @Override
    public void warn(String format, Object arg)
    {
        log.warn(format, arg);
    }

    @Override
    public void warn(String format, Object... arguments)
    {
        log.warn(format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2)
    {
        log.warn(format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t)
    {
        log.warn(msg, t);
    }

    @Override
    public boolean isWarnEnabled(Marker marker)
    {
        return log.isWarnEnabled(marker);
    }

    @Override
    public void warn(Marker marker, String msg)
    {
        log.warn(marker, msg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg)
    {
        log.warn(marker, format, arg);
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2)
    {
        log.warn(marker, format, arg1, arg2);
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments)
    {
        log.warn(marker, format, arguments);
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t)
    {
        log.warn(marker, msg, t);
    }

    @Override
    public boolean isErrorEnabled()
    {
        return log.isErrorEnabled();
    }

    @Override
    public void error(String msg)
    {
        log.error(msg);
    }

    @Override
    public void error(String format, Object arg)
    {
        log.error(format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2)
    {
        log.error(format, arg1, arg2);
    }

    @Override
    public void error(String format, Object... arguments)
    {
        log.error(format, arguments);
    }

    @Override
    public boolean isErrorEnabled(Marker marker)
    {
        return log.isErrorEnabled(marker);
    }

    @Override
    public void error(Marker marker, String msg)
    {
        log.error(marker, msg);
    }

    @Override
    public void error(Marker marker, String format, Object arg)
    {
        log.error(marker, format, arg);
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2)
    {
        log.error(marker, format, arg1, arg2);
    }

    @Override
    public void error(Marker marker, String format, Object... arguments)
    {
        log.error(marker, format, arguments);
    }

    @Override
    public void error(Marker marker, String msg, Throwable t)
    {
        log.error(marker, msg, t);
    }

}
