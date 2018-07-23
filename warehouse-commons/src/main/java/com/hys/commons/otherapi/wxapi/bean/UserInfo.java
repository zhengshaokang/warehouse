package com.hys.commons.otherapi.wxapi.bean;

/**
 * 普通消息
 * 
 */
public class UserInfo extends Base
{
    private static final long serialVersionUID = 1L;

    // 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
    private String subscribe;
    // 用户的标识，对当前公众号唯一
    private String openid;
    // 用户的昵称
    private String nickname;
    // 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
    private String sex;
    // 用户所在城市
    private String city;
    // 用户所在国家
    private String country;
    // 用户所在省份
    private String province;
    // 用户的语言，简体中文为zh_CN
    private String language;
    // 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
    private String headimgurl;
    // 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
    private String subscribe_time;
    // 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
    private String privilege;

    /**
     * @return privilege
     */
    public String getPrivilege()
    {
        return privilege;
    }

    /**
     * @param privilege
     *        the privilege to set
     */
    public void setPrivilege(String privilege)
    {
        this.privilege = privilege;
    }

    /**
     * @return subscribe
     */
    public String getSubscribe()
    {
        return subscribe;
    }

    /**
     * @param subscribe
     *        the subscribe to set
     */
    public void setSubscribe(String subscribe)
    {
        this.subscribe = subscribe;
    }

    /**
     * @return openid
     */
    public String getOpenid()
    {
        return openid;
    }

    /**
     * @param openid
     *        the openid to set
     */
    public void setOpenid(String openid)
    {
        this.openid = openid;
    }

    /**
     * @return nickname
     */
    public String getNickname()
    {
        return nickname;
    }

    /**
     * @param nickname
     *        the nickname to set
     */
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    /**
     * @return sex
     */
    public String getSex()
    {
        return sex;
    }

    /**
     * @param sex
     *        the sex to set
     */
    public void setSex(String sex)
    {
        this.sex = sex;
    }

    /**
     * @return city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @param city
     *        the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * @return country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * @param country
     *        the country to set
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * @return province
     */
    public String getProvince()
    {
        return province;
    }

    /**
     * @param province
     *        the province to set
     */
    public void setProvince(String province)
    {
        this.province = province;
    }

    /**
     * @return language
     */
    public String getLanguage()
    {
        return language;
    }

    /**
     * @param language
     *        the language to set
     */
    public void setLanguage(String language)
    {
        this.language = language;
    }

    /**
     * @return headimgurl
     */
    public String getHeadimgurl()
    {
        return headimgurl;
    }

    /**
     * @param headimgurl
     *        the headimgurl to set
     */
    public void setHeadimgurl(String headimgurl)
    {
        this.headimgurl = headimgurl;
    }

    /**
     * @return subscribe_time
     */
    public String getSubscribe_time()
    {
        return subscribe_time;
    }

    /**
     * @param subscribe_time
     *        the subscribe_time to set
     */
    public void setSubscribe_time(String subscribe_time)
    {
        this.subscribe_time = subscribe_time;
    }

    @Override
    public String toString()
    {
        return String
                .format("UserInfo [subscribe=%s, openid=%s, nickname=%s, sex=%s, city=%s, country=%s, province=%s, language=%s, headimgurl=%s, subscribe_time=%s, privilege=%s]",
                        subscribe, openid, nickname, sex, city, country, province, language, headimgurl,
                        subscribe_time, privilege);
    }

}
