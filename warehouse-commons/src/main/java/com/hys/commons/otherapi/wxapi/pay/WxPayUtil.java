package com.hys.commons.otherapi.wxapi.pay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;

import com.hys.commons.conf.EncryptedProperties;
import com.hys.commons.crypto.MD5Coding;
import com.hys.commons.crypto.SHA1Coding;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.logutil.LogUtil;
import com.hys.commons.string.StringUtil;


public abstract class WxPayUtil
{
    private static final Logger log = LogProxy.getLogger(WxPayUtil.class);
    private static EncryptedProperties ep = null;

    static
    {
        try
        {
            ep = new EncryptedProperties();
            ep.load(WxPayUtil.class.getResourceAsStream("/autoconf/maowu_pay_wx.properties"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        WxPayUtil.getCftPackage(null);
    }

    public static List<Entry<String, String>> sortMap(Map<String, String> parameters)
    {
        List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(parameters.entrySet());

        Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>()
        {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2)
            {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        return infoIds;
    }

    // 把请求参数的key按asc排序,然后按顺序转url参数
    // key不转小写,但一定UTF8转码
    public static String formatQueryParaMap(Map<String, String> parameters) throws WXPayException
    {
        return formatQueryParaMap(parameters, false, true);
    }

    // 把请求参数的key按asc排序,然后按顺序转url参数
    // key一定小写,但value根据urlencode要求是否转
    public static String formatQueryParaMap(Map<String, String> paraMap, boolean urlencode)
    {
        return formatQueryParaMap(paraMap, true, urlencode);
    }

    public static String formatQueryParaMap(Map<String, String> paraMap, boolean isToLowerCase, boolean urlencode)
    {
        List<Map.Entry<String, String>> infoIds = sortMap(paraMap);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> item : infoIds)
        {
            if (!StringUtil.isEmpty(item.getKey()))
            {
                String key = item.getKey();
                String val = item.getValue();
                if (urlencode)
                {
                    val = StringUtil.urlEncodeUTF8(val);
                }
                if (isToLowerCase)
                {
                    key = key.toLowerCase();
                }

                sb.append(key).append("=").append(val).append("&");
            }
        }

        if (sb.length() != 0)
        {
            return sb.substring(0, sb.length() - 1);
        }

        return sb.toString();
    }

    // 自己算个签名
    public static String sign(String content, String key) throws WXPayException
    {
        String signStr = "";

        if (StringUtil.isEmpty(key))
        {
            throw new WXPayException("财付通签名key不能为空！");
        }
        if (StringUtil.isEmpty(content))
        {
            throw new WXPayException("财付通签名内容不能为空");
        }
        signStr = content + "&key=" + key;

        String sign = MD5Coding.encodeForWx(signStr).toUpperCase();

        LogUtil.debug(log, "signStr=[%s],sign=[%s]", signStr, sign);
        return sign;
    }

    // 校验财付通来的签名是否正确
    public static boolean verifySignature(String content, String sign, String md5Key)
    {
        String calculateSign = sign(content, md5Key);

        String tenpaySign = sign.toUpperCase();
        return (calculateSign.equals(tenpaySign));
    }

    public static String getPaySignKey()
    {
        return ep.getProperty("paysignkey");
    }

    public static String getPartnerKey()
    {
        return ep.getProperty("partnerkey");
    }

    // 告诉我你要签名的字段,我给你算,算法是标准sha1转16进制小写
    public static String getSignature(Map<String, String> nativeObj)
    {
        // key一定小写,但value不转
        String bizString = WxPayUtil.formatQueryParaMap(nativeObj, false);

        // 要测试,sh-1后转16进制时大写,再转小写能和微信对上吗？
        String appSignature = SHA1Coding.sha1ForWx(bizString).toLowerCase();

        LogUtil.debug(log, "getSignature nativeObj=[%s],appSignature=[%s]", nativeObj, appSignature);
        return appSignature;
    }

    // 财付通packge生成方法
    public static String getCftPackage(Map<String, String> parameters) throws WXPayException
    {
        // key一定小写,但value不转
        String unSignParaString = WxPayUtil.formatQueryParaMap(parameters, false);
        // key一定小写,但value转utf
        String paraString = WxPayUtil.formatQueryParaMap(parameters, true);

        String cftPackage = paraString + "&sign=" + sign(unSignParaString, WxPayUtil.getPartnerKey());

        LogUtil.debug(log, "getCftPackage parameters=[%s],cftPackage=[%s]", parameters, cftPackage);
        return cftPackage;
    }

    // 传入签名字段在map里,然后再增加appkey,一起sh-1加密算
    public static String getAppSignature(Map<String, String> nativeObj)
    {
        nativeObj.put("appkey", WxPayUtil.getPaySignKey());

        return getSignature(nativeObj);
    }
}
