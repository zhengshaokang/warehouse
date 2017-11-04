package com.hys.mgt.view.common.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil
{

    public static void responseStr(HttpServletResponse response, String str)
    {
        response.setContentType("TEXT/HTML");
        response.setHeader("Cache-Control", "no-store");
        try
        {
            PrintWriter pw = response.getWriter();
            pw.write(str);
            pw.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void closePage(HttpServletResponse response, String str)
    {
        response.setContentType("TEXT/HTML");
        response.setHeader("Cache-Control", "no-store");
        try
        {
            String script = "<script>var browserName=navigator.appName; if (browserName==\"Netscape\") { window.open('','_parent',''); window.close(); }else if (browserName==\"Microsoft Internet Explorer\") { window.opener = \"whocares\"; window.close(); }</script>";

            PrintWriter pw = response.getWriter();
            pw.write(str + "   " + script);
            pw.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
