package com.hys.commons.otherapi.wxapi.bean.resp;

import java.util.List;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("Articles")
public class Articles
{
    /**
     * 多条图文消息信息，默认第一个item为大图
     */
    @XStreamImplicit(itemFieldName = "item")
    @XStreamCDATA
    private List<Article> articles = null;

    /**
     * @return articles
     */
    public List<Article> getArticles()
    {
        return articles;
    }

    /**
     * @param articles
     *        the articles to set
     */
    public void setArticles(List<Article> articles)
    {
        this.articles = articles;
    }

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Articles [articles=%s]", articles);
    }

}
