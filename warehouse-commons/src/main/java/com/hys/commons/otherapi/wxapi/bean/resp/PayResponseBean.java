package com.hys.commons.otherapi.wxapi.bean.resp;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 */
@XStreamAlias("xml")
public class PayResponseBean
{
    @XStreamAlias("OpenId")
    @XStreamCDATA
    private String openId;

    @XStreamAlias("AppId")
    @XStreamCDATA
    private String appId;

    @XStreamAlias("IsSubscribe")
    private String isSubscribe;

    @XStreamAlias("TimeStamp")
    private String timeStamp;

    @XStreamAlias("AppSignature")
    @XStreamCDATA
    private String appSignature;

    @XStreamAlias("NonceStr")
    @XStreamCDATA
    private String nonceStr;

    @XStreamAlias("SignMethod")
    @XStreamCDATA
    private String signMethod;

    /**
     * @return openId
     */
    public String getOpenId()
    {
        return openId;
    }

    /**
     * @param openId
     *        the openId to set
     */
    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

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
     * @return isSubscribe
     */
    public String getIsSubscribe()
    {
        return isSubscribe;
    }

    /**
     * @param isSubscribe
     *        the isSubscribe to set
     */
    public void setIsSubscribe(String isSubscribe)
    {
        this.isSubscribe = isSubscribe;
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

    /**
     * @return nonceStr
     */
    public String getNonceStr()
    {
        return nonceStr;
    }

    /**
     * @param nonceStr
     *        the nonceStr to set
     */
    public void setNonceStr(String nonceStr)
    {
        this.nonceStr = nonceStr;
    }

    /**
     * @return signMethod
     */
    public String getSignMethod()
    {
        return signMethod;
    }

    /**
     * @param signMethod
     *        the signMethod to set
     */
    public void setSignMethod(String signMethod)
    {
        this.signMethod = signMethod;
    }

    @Override
    public String toString()
    {
        return "PayResponseBean [openId=" + openId + ", appId=" + appId + ", isSubscribe=" + isSubscribe
                + ", timeStamp=" + timeStamp + ", appSignature=" + appSignature + ", nonceStr=" + nonceStr
                + ", signMethod=" + signMethod + "]";
    }

}
