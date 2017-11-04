package com.hys.mgt.view.common.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hys.commons.logutil.LogProxy;

/**
 * Servlet工具类
 * 
 */
public class ServletUtils
{
    private static Logger log = LogProxy.getLogger(ServletUtils.class);

    /**
     * 获取根目录
     * 
     * @return
     */
    public static String getRealPath() {
    	 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                 .getRequest();
    	String realPath = request.getSession().getServletContext().getRealPath("/")+"\\";
    	if(null == realPath)  {
    		realPath = request.getSession().getServletContext().getRealPath(File.separator);
    	}
        return realPath;
    }

    /**
     * 获取请求地址
     * 
     * @return
     */
    public static String serverUrl()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String temp = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
        return request.getScheme() + "://" + request.getServerName() + temp + request.getContextPath() + "/";
    }

    /**
     * 获取当前请求的URL地址域参数
     * 
     * @param request
     * @return
     */
    public static Map<String, String> getParams(HttpServletRequest request)
    {
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Entry<String, String[]> en : requestParams.entrySet())
        {
            String name = en.getKey();
            String[] values = en.getValue();

            String valueStr = "";
            for (int i = 0; i < values.length; i++)
            {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }

    public static String getRemortIp()
    {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        if (request.getHeader("x-forwarded-for") == null)
        {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    public static String getIpAddress(HttpServletRequest request)
    {
        String localIP = "127.0.0.1";
        String ip = request.getHeader("x-forwarded-for");
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if ((ip == null) || (ip.length() == 0) || (ip.equalsIgnoreCase(localIP)) || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static void print2Client(HttpServletResponse res, String rslt)
    {
        try
        {
            ServletOutputStream sos = res.getOutputStream();
            sos.write(rslt.getBytes("UTF8"));
            res.setHeader("Content-Type", "text/html;charset=UTF8");
            res.setHeader("Connection", "close");
            res.setHeader("Content-Length", String.valueOf(rslt.getBytes("UTF8").length));
            sos.close();
        }
        catch (Throwable e)
        {
            log.error("print2Client", e);
        }
    }
}
