package com.hys.commons.otherapi.wxapi.bean.resp;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 音乐model
 * 
 */
@XStreamAlias("Music")
public class Music
{
    // 音乐名称
    @XStreamAlias("Title")
    @XStreamCDATA
    private String title;

    // 音乐描述
    @XStreamAlias("Description")
    @XStreamCDATA
    private String description;

    // 音乐链接
    @XStreamAlias("MusicUrl")
    @XStreamCDATA
    private String musicUrl;

    // 高质量音乐链接，WIFI环境优先使用该链接播放音乐
    @XStreamAlias("HQMusicUrl")
    @XStreamCDATA
    private String hQMusicUrl;

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
     * @return musicUrl
     */
    public String getMusicUrl()
    {
        return musicUrl;
    }

    /**
     * @param musicUrl
     *        the musicUrl to set
     */
    public void setMusicUrl(String musicUrl)
    {
        this.musicUrl = musicUrl;
    }

    /**
     * @return hQMusicUrl
     */
    public String gethQMusicUrl()
    {
        return hQMusicUrl;
    }

    /**
     * @param hQMusicUrl
     *        the hQMusicUrl to set
     */
    public void sethQMusicUrl(String hQMusicUrl)
    {
        this.hQMusicUrl = hQMusicUrl;
    }

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Music [title=%s, description=%s, musicUrl=%s, hQMusicUrl=%s]", title, description,
                musicUrl, hQMusicUrl);
    }

}
