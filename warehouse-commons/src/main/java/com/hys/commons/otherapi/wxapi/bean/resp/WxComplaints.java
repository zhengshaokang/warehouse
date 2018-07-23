package com.hys.commons.otherapi.wxapi.bean.resp;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信用户新增投诉|用户确认处理完毕投诉
 * 
 */
@XStreamAlias("xml")
public class WxComplaints
{
    // 用户微信ID
    @XStreamAlias("OpenId")
    @XStreamCDATA
    private String openId;

    // 服务号ID
    @XStreamAlias("AppId")
    @XStreamCDATA
    private String appId;

    // 时间戳
    @XStreamAlias("TimeStamp")
    private String timeStamp;

    // req用户提交投诉
    @XStreamAlias("MsgType")
    @XStreamCDATA
    private String msgType;

    // req用户提交投诉
    @XStreamAlias("FeedBackId")
    @XStreamCDATA
    private String feedBackId;

    //
    @XStreamAlias("TransId")
    @XStreamCDATA
    private String transId;

    // 投诉原因
    @XStreamAlias("Reason")
    @XStreamCDATA
    private String reason;

    // 投诉原因
    @XStreamAlias("Solution")
    @XStreamCDATA
    private String solution;

    @XStreamAlias("ExtInfo")
    @XStreamCDATA
    private String extInfo;

    // 签名
    @XStreamAlias("AppSignature")
    @XStreamCDATA
    private String appSignature;

    // 加密算法
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
     * @return msgType
     */
    public String getMsgType()
    {
        return msgType;
    }

    /**
     * @param msgType
     *        the msgType to set
     */
    public void setMsgType(String msgType)
    {
        this.msgType = msgType;
    }

    /**
     * @return feedBackId
     */
    public String getFeedBackId()
    {
        return feedBackId;
    }

    /**
     * @param feedBackId
     *        the feedBackId to set
     */
    public void setFeedBackId(String feedBackId)
    {
        this.feedBackId = feedBackId;
    }

    /**
     * @return reason
     */
    public String getReason()
    {
        return reason;
    }

    /**
     * @param reason
     *        the reason to set
     */
    public void setReason(String reason)
    {
        this.reason = reason;
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

    /**
     * @return transId
     */
    public String getTransId()
    {
        return transId;
    }

    /**
     * @param transId
     *        the transId to set
     */
    public void setTransId(String transId)
    {
        this.transId = transId;
    }

    /**
     * @return solution
     */
    public String getSolution()
    {
        return solution;
    }

    /**
     * @param solution
     *        the solution to set
     */
    public void setSolution(String solution)
    {
        this.solution = solution;
    }

    /**
     * @return extInfo
     */
    public String getExtInfo()
    {
        return extInfo;
    }

    /**
     * @param extInfo
     *        the extInfo to set
     */
    public void setExtInfo(String extInfo)
    {
        this.extInfo = extInfo;
    }

    @Override
    public String toString()
    {
        return "WxRights [openId=" + openId + ", appId=" + appId + ", timeStamp=" + timeStamp + ", msgType=" + msgType
                + ", feedBackId=" + feedBackId + ", transId=" + transId + ", reason=" + reason + ", solution="
                + solution + ", extInfo=" + extInfo + ", appSignature=" + appSignature + ", signMethod=" + signMethod
                + "]";
    }

}
