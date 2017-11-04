package com.hys.commons.util;

import javax.servlet.http.HttpServletRequest;

import com.hys.commons.string.StringUtil;

/**
 * Ua工具类
 * 
 */
public abstract class BrowserFeatureUtil
{
    /** ua的http header 名字 */
    public static final String UAHEADER = "User-Agent";
    private static final String WEXINUA = "MicroMessenger/";

    // Mobile/9B206 MicroMessenger/5.0" 5.0为用户安装的微信版本号
    public boolean isCanWxPay(HttpServletRequest req)
    {
        String ua = StringUtil.convertString(req.getHeader(UAHEADER), "");
        int pos = ua.indexOf(WEXINUA);
        String ver = "";
        if (pos != -1)
        {
            ver = ua.substring(pos + WEXINUA.length(), ua.length());
        }
        float version = StringUtil.convertFloat(ver, 5.0f);
        if (version >= 5.0f)
        {
            return true;
        }
        return false;
    }
}
