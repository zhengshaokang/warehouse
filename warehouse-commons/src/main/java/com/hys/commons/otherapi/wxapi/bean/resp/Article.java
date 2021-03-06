package com.hys.commons.otherapi.wxapi.bean.resp;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 图文model
 * 
 */
@XStreamAlias("xml")
public class Article
{
    // 图文消息名称
    @XStreamAlias("Title")
    @XStreamCDATA
    private String title;

    // 图文消息描述
    @XStreamAlias("Description")
    @XStreamCDATA
    private String description;

    // 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80，限制图片链接的域名需要与开发者填写的基本资料中的Url一致
    @XStreamAlias("Picurl")
    @XStreamCDATA
    private String picurl;

    // 点击图文消息跳转链接
    @XStreamAlias("Url")
    @XStreamCDATA
    private String url;

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

    public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
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

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Article [title=%s, description=%s, picUrl=%s, url=%s, getClass()=%s, hashCode()=%s]",
                title, description, picurl, url, getClass(), hashCode());
    }

}
