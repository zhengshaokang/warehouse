package com.hys.commons.otherapi.wxapi.bean.resp;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 消息基类（公众帐号 -> 普通用户）
 * 
 */
public class BaseMessage
{
    // 接收方帐号（收到的OpenID）
    @XStreamAlias("ToUserName")
    @XStreamCDATA
    private String toUserName;

    // 开发者微信号
    @XStreamAlias("FromUserName")
    @XStreamCDATA
    private String fromUserName;

    // 消息创建时间 （整型）
    @XStreamAlias("CreateTime")
    @XStreamCDATA
    private long createTime;

    // 消息类型（text/music/news）
    @XStreamAlias("MsgType")
    @XStreamCDATA
    private String msgType;

    /**
     * @return toUserName
     */
    public String getToUserName()
    {
        return toUserName;
    }

    /**
     * @param toUserName
     *        the toUserName to set
     */
    public void setToUserName(String toUserName)
    {
        this.toUserName = toUserName;
    }

    /**
     * @return fromUserName
     */
    public String getFromUserName()
    {
        return fromUserName;
    }

    /**
     * @param fromUserName
     *        the fromUserName to set
     */
    public void setFromUserName(String fromUserName)
    {
        this.fromUserName = fromUserName;
    }

    /**
     * @return createTime
     */
    public long getCreateTime()
    {
        return createTime;
    }

    /**
     * @param createTime
     *        the createTime to set
     */
    public void setCreateTime(long createTime)
    {
        this.createTime = createTime;
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

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("BaseMessage [toUserName=%s, fromUserName=%s, createTime=%s, msgType=%s]", toUserName,
                fromUserName, createTime, msgType);
    }

}
