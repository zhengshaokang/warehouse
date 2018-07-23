package com.hys.commons.otherapi.wxapi.bean.resp;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信告警通知
 * 
 * @author: smartlv
 * @date 2014年3月8日 下午6:16:00
 */
@XStreamAlias("xml")
public class WxAlarmNotify
{

    // 服务号ID
    @XStreamAlias("AppId")
    @XStreamCDATA
    private String appId;

    @XStreamAlias("ErrorType")
    private String errorType;

    @XStreamAlias("Description")
    @XStreamCDATA
    private String desc;

    @XStreamAlias("AlarmContent")
    @XStreamCDATA
    private String alarmContent;

    // 时间戳
    @XStreamAlias("TimeStamp")
    private String timeStamp;

    // 签名
    @XStreamAlias("AppSignature")
    @XStreamCDATA
    private String appSignature;

    // 加密算法
    @XStreamAlias("SignMethod")
    @XStreamCDATA
    private String signMethod;

    /**
     * @return appId
     */
    public String getAppId()
    {
        return appId;
    }

    /**
     * @param appId
     *        the appId to set
     */
    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    /**
     * @return errorType
     */
    public String getErrorType()
    {
        return errorType;
    }

    /**
     * @param errorType
     *        the errorType to set
     */
    public void setErrorType(String errorType)
    {
        this.errorType = errorType;
    }

    /**
     * @return desc
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc
     *        the desc to set
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return alarmContent
     */
    public String getAlarmContent()
    {
        return alarmContent;
    }

    /**
     * @param alarmContent
     *        the alarmContent to set
     */
    public void setAlarmContent(String alarmContent)
    {
        this.alarmContent = alarmContent;
    }

    /**
     * @return timeStamp
     */
    public String getTimeStamp()
    {
        return timeStamp;
    }

    /**
     * @param timeStamp
     *        the timeStamp to set
     */
    public void setTimeStamp(String timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    /**
     * @return appSignature
     */
    public String getAppSignature()
    {
        return appSignature;
    }

    /**
     * @param appSignature
     *        the appSignature to set
     */
    public void setAppSignature(String appSignature)
    {
        this.appSignature = appSignature;
    }

    public String getSignMethod()
    {
        return signMethod;
    }

    public void setSignMethod(String signMethod)
    {
        this.signMethod = signMethod;
    }

    @Override
    public String toString()
    {
        return "WxAlarmNotify [appId=" + appId + ", errorType=" + errorType + ", desc=" + desc + ", alarmContent="
                + alarmContent + ", timeStamp=" + timeStamp + ", appSignature=" + appSignature + ", signMethod="
                + signMethod + "]";
    }

}
