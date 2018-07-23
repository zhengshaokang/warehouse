package com.hys.commons.otherapi.wxapi.bean.resp;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 文本消息
 * 
 */
@XStreamAlias("xml")
public class TextMessage extends BaseMessage
{
    // 回复的消息内容
    @XStreamAlias("Content")
    @XStreamCDATA
    private String content;

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

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {

        return this.getContent() + ";" + this.getCreateTime() + ";" + this.getFromUserName() + ";" + this.getMsgType()
                + ";" + this.getToUserName() + ";";
    }
}
