package com.hys.commons.otherapi.wxapi.bean.resp;

import com.hys.commons.xml.XStreamCDATA;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 音乐消息
 * 
 */
@XStreamAlias("xml")
public class MusicMessage extends BaseMessage
{
    // 音乐
    @XStreamAlias("Music")
    @XStreamCDATA
    private Music music;

    /**
     * @return music
     */
    public Music getMusic()
    {
        return music;
    }

    /**
     * @param music
     *        the music to set
     */
    public void setMusic(Music music)
    {
        this.music = music;
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
                .format("MusicMessage [music=%s, getToUserName()=%s, getFromUserName()=%s, getCreateTime()=%s, getMsgType()=%s]",
                        music, getToUserName(), getFromUserName(), getCreateTime(), getMsgType());
    }

}
