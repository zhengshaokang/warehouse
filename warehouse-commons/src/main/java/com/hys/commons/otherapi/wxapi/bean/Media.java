package com.hys.commons.otherapi.wxapi.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 多媒体文件
 * 
 */
public class Media extends Base
{
    private static final long serialVersionUID = 1L;

    // 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
    private String type;
    // 媒体文件上传后，获取时的唯一标识
    private String media_id;
    // 媒体文件上传时间戳
    @JsonProperty("created_at")
    private String createdAt;

    /**
     * @return type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type
     *        the type to set
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return media_id
     */
    public String getMedia_id()
    {
        return media_id;
    }

    /**
     * @param media_id
     *        the media_id to set
     */
    public void setMedia_id(String media_id)
    {
        this.media_id = media_id;
    }

    /**
     * @return createdAt
     */
    public String getCreatedAt()
    {
        return createdAt;
    }

    /**
     * @param createdAt
     *        the createdAt to set
     */
    public void setCreatedAt(String createdAt)
    {
        this.createdAt = createdAt;
    }

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Media [type=%s, media_id=%s, createdAt=%s, getErrcode()=%s, getErrmsg()=%s]", type,
                media_id, createdAt, getErrcode(), getErrmsg());
    }

}
