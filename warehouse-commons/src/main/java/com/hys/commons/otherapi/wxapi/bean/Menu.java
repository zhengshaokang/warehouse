package com.hys.commons.otherapi.wxapi.bean;

import java.util.Arrays;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 自定义菜单
 * 
 */
public class Menu extends Base
{
    private static final long serialVersionUID = 1L;

    // 菜单的响应动作类型，目前有click、view两种类型
    private String type;
    // 菜单标题，不超过16个字节，子菜单不超过40个字节
    private String name;
    // 菜单KEY值，用于消息接口推送，不超过128字节
    private String key;
    // 网页链接，用户点击菜单可打开链接，不超过256字节
    private String url;
    // 二级菜单数组，个数应为1~5个
    @JsonProperty("sub_button")
    private Menu[] subButton;

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
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name
     *        the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return key
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key
     *        the key to set
     */
    public void setKey(String key)
    {
        this.key = key;
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
     * @return subButton
     */
    public Menu[] getSubButton()
    {
        return subButton;
    }

    /**
     * @param subButton
     *        the subButton to set
     */
    public void setSubButton(Menu[] subButton)
    {
        this.subButton = subButton;
    }

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Menu [type=%s, name=%s, key=%s, url=%s, subButton=%s, getErrcode()=%s, getErrmsg()=%s]",
                type, name, key, url, Arrays.toString(subButton), getErrcode(), getErrmsg());
    }

}
