package com.hys.commons.otherapi.wxapi.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 消息
 * 
 */
@XStreamAlias("xml")
public class Message extends Base
{
    private static final long serialVersionUID = 1L;

    // 开发者微信号
    @XStreamAlias("ToUserName")
    private String toUserName;
    // 发送方帐号（一个OpenID）
    @XStreamAlias("FromUserName")
    private String fromUserName;
    // 消息创建时间 （整型）
    @XStreamAlias("CreateTime")
    private String createTime;
    // 消息类型
    @XStreamAlias("MsgType")
    private String msgType;
    // 文本消息内容
    @XStreamAlias("Content")
    private String content;
    // 消息id，64位整型
    @XStreamAlias("MsgId")
    private String msgId;
    // 图片链接
    @XStreamAlias("PicUrl")
    private String picUrl;
    // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    @XStreamAlias("MediaId")
    private String mediaId;
    // 语音格式，如amr，speex等
    @XStreamAlias("Format")
    private String format;
    // 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    @XStreamAlias("ThumbMediaId")
    private String thumbMediaId;
    // 地理位置纬度
    @XStreamAlias("Location_X")
    private String locationX;
    // 地理位置经度
    @XStreamAlias("Location_Y")
    private String locationY;
    // 地图缩放大小
    @XStreamAlias("Scale")
    private String scale;
    // 地理位置信息
    @XStreamAlias("Label")
    private String label;
    // 消息标题
    @XStreamAlias("Title")
    private String title;
    // 消息描述
    @XStreamAlias("Description")
    private String description;
    // 消息链接
    @XStreamAlias("Url")
    private String url;
    // 事件类型
    @XStreamAlias("Event")
    private String event;
    // 事件KEY值，与自定义菜单接口中KEY值对应
    @XStreamAlias("EventKey")
    private String eventKey;
    // 语音识别结果，UTF8编码
    @XStreamAlias("Recognition")
    private String recognition;
    // 语音消息媒体id，可以调用多媒体文件下载接口拉取该媒体
    @XStreamAlias("MediaID")
    private String mediaID;
    // 地理位置精度
    @XStreamAlias("Precision")
    private String precision;

    // 地理位置纬度 事件推送
    @XStreamAlias("Latitude")
    private String latitude;

    // 地理位置经度 事件推送
    @XStreamAlias("Longitude")
    private String longitude;

    // 二维码的ticket，可用来换取二维码图片
    @XStreamAlias("Ticket")
    private String ticket;

    /**
     * @return ticket
     */
    public String getTicket()
    {
        return ticket;
    }

    /**
     * @param ticket
     *        the ticket to set
     */
    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }

    /**
     * @return latitude
     */
    public String getLatitude()
    {
        return latitude;
    }

    /**
     * @param latitude
     *        the latitude to set
     */
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    /**
     * @return longitude
     */
    public String getLongitude()
    {
        return longitude;
    }

    /**
     * @param longitude
     *        the longitude to set
     */
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

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
    public String getCreateTime()
    {
        return createTime;
    }

    /**
     * @param createTime
     *        the createTime to set
     */
    public void setCreateTime(String createTime)
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

    /**
     * @return content
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @param content
     *        the content to set
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * @return msgId
     */
    public String getMsgId()
    {
        return msgId;
    }

    /**
     * @param msgId
     *        the msgId to set
     */
    public void setMsgId(String msgId)
    {
        this.msgId = msgId;
    }

    /**
     * @return picUrl
     */
    public String getPicUrl()
    {
        return picUrl;
    }

    /**
     * @param picUrl
     *        the picUrl to set
     */
    public void setPicUrl(String picUrl)
    {
        this.picUrl = picUrl;
    }

    /**
     * @return mediaId
     */
    public String getMediaId()
    {
        return mediaId;
    }

    /**
     * @param mediaId
     *        the mediaId to set
     */
    public void setMediaId(String mediaId)
    {
        this.mediaId = mediaId;
    }

    /**
     * @return format
     */
    public String getFormat()
    {
        return format;
    }

    /**
     * @param format
     *        the format to set
     */
    public void setFormat(String format)
    {
        this.format = format;
    }

    /**
     * @return thumbMediaId
     */
    public String getThumbMediaId()
    {
        return thumbMediaId;
    }

    /**
     * @param thumbMediaId
     *        the thumbMediaId to set
     */
    public void setThumbMediaId(String thumbMediaId)
    {
        this.thumbMediaId = thumbMediaId;
    }

    /**
     * @return locationX
     */
    public String getLocationX()
    {
        return locationX;
    }

    /**
     * @param locationX
     *        the locationX to set
     */
    public void setLocationX(String locationX)
    {
        this.locationX = locationX;
    }

    /**
     * @return locationY
     */
    public String getLocationY()
    {
        return locationY;
    }

    /**
     * @param locationY
     *        the locationY to set
     */
    public void setLocationY(String locationY)
    {
        this.locationY = locationY;
    }

    /**
     * @return scale
     */
    public String getScale()
    {
        return scale;
    }

    /**
     * @param scale
     *        the scale to set
     */
    public void setScale(String scale)
    {
        this.scale = scale;
    }

    /**
     * @return label
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @param label
     *        the label to set
     */
    public void setLabel(String label)
    {
        this.label = label;
    }

    /**
     * @return title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title
     *        the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     *        the description to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url
     *        the url to set
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return event
     */
    public String getEvent()
    {
        return event;
    }

    /**
     * @param event
     *        the event to set
     */
    public void setEvent(String event)
    {
        this.event = event;
    }

    /**
     * @return eventKey
     */
    public String getEventKey()
    {
        return eventKey;
    }

    /**
     * @param eventKey
     *        the eventKey to set
     */
    public void setEventKey(String eventKey)
    {
        this.eventKey = eventKey;
    }

    /**
     * @return recognition
     */
    public String getRecognition()
    {
        return recognition;
    }

    /**
     * @param recognition
     *        the recognition to set
     */
    public void setRecognition(String recognition)
    {
        this.recognition = recognition;
    }

    /**
     * @return mediaID
     */
    public String getMediaID()
    {
        return mediaID;
    }

    /**
     * @param mediaID
     *        the mediaID to set
     */
    public void setMediaID(String mediaID)
    {
        this.mediaID = mediaID;
    }

    /**
     * @return precision
     */
    public String getPrecision()
    {
        return precision;
    }

    /**
     * @param precision
     *        the precision to set
     */
    public void setPrecision(String precision)
    {
        this.precision = precision;
    }

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String
                .format("Message [toUserName=%s, fromUserName=%s, createTime=%s, msgType=%s, content=%s, msgId=%s, picUrl=%s, mediaId=%s, format=%s, thumbMediaId=%s, locationX=%s, locationY=%s, scale=%s, label=%s, title=%s, description=%s, url=%s, event=%s, eventKey=%s, recognition=%s, mediaID=%s, precision=%s, latitude=%s, longitude=%s]",
                        toUserName, fromUserName, createTime, msgType, content, msgId, picUrl, mediaId, format,
                        thumbMediaId, locationX, locationY, scale, label, title, description, url, event, eventKey,
                        recognition, mediaID, precision, latitude, longitude);
    }

}
