package com.hys.mgt.view.common.utils;

import com.hys.commons.conf.ProfileManager;

/**
 * 读取“hys_webapp”配置文件中的值
 * 
 */
public class WebConfUtils
{

    public static int getInt(String key, int defaultInt)
    {
        return ProfileManager.getIntByKey("hys_webapp." + key, defaultInt);
    }

    public static long getLong(String key, int defaultLong)
    {
        return ProfileManager.getLongByKey("hys_webapp." + key, defaultLong);
    }

    public static String getString(String key, String defaultString)
    {
        return ProfileManager.getStringByKey("hys_webapp." + key, defaultString);
    }

}
