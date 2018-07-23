package com.hys.commons.otherapi.wxapi.bean.resp;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 图文消息
 * 
 */
@XStreamAlias("xml")
public class NewsMessage extends BaseMessage
{

    /**
     * 图文消息个数，限制为10条以内
     */
    @XStreamAlias("ArticleCount")
    @XStreamCDATA
    private int articleCount;

    @XStreamAlias("Articles")
    @XStreamCDATA
    private Articles articles;

    /**
     * @return articles
     */
    public Articles getArticles()
    {
        return articles;
    }

    /**
     * @param articles
     *        the articles to set
     */
    public void setArticles(Articles articles)
    {
        this.articles = articles;
    }

    /**
     * @return articleCount
     */
    public int getArticleCount()
    {
        return articleCount;
    }

    /**
     * @param articleCount
     *        the articleCount to set
     */
    public void setArticleCount(int articleCount)
    {
        this.articleCount = articleCount;
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
                .format("NewsMessage [articleCount=%s, articles=%s, getToUserName()=%s, getFromUserName()=%s, getCreateTime()=%s, getMsgType()=%s]",
                        articleCount, articles, getToUserName(), getFromUserName(), getCreateTime(), getMsgType());
    }

}
