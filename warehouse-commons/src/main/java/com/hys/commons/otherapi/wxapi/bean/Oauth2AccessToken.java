package com.hys.commons.otherapi.wxapi.bean;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 公众号的全局唯一票据 access_token
 * 
 */
public class Oauth2AccessToken extends Base
{
    private static final long serialVersionUID = 1L;

    // 获取access_token填写client_credential
    @JsonProperty("grant_type")
    private String grantType = "authorization_code";
    // 第三方用户唯一凭证
    private String appid;
    // 第三方用户唯一凭证密钥，即appsecret
    private String secret;

    // 唯一凭证
    @JsonProperty("access_token")
    private String accessToken;
    // 超时时间 秒
    @JsonProperty("expires_in")
    private String expiresIn;

    // 用户刷新access_token
    @JsonProperty("refresh_token")
    private String refreshToken;

    // 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
    @JsonProperty("openid")
    private String openId;

    // 用户授权的作用域，使用逗号（,）分隔
    @JsonProperty("scope")
    private String scope;

    /**
     * @return refreshToken
     */
    public String getRefreshToken()
    {
        return refreshToken;
    }

    /**
     * @param refreshToken
     *        the refreshToken to set
     */
    public void setRefreshToken(String refreshToken)
    {
        this.refreshToken = refreshToken;
    }

    /**
     * @return openId
     */
    public String getOpenId()
    {
        return openId;
    }

    /**
     * @param openId
     *        the openId to set
     */
    public void setOpenId(String openId)
    {
        this.openId = openId;
    }

    /**
     * @return scope
     */
    public String getScope()
    {
        return scope;
    }

    /**
     * @param scope
     *        the scope to set
     */
    public void setScope(String scope)
    {
        this.scope = scope;
    }

    /**
     * @return grantType
     */
    public String getGrantType()
    {
        return grantType;
    }

    /**
     * @param grantType
     *        the grantType to set
     */
    public void setGrantType(String grantType)
    {
        this.grantType = grantType;
    }

    /**
     * @return appid
     */
    public String getAppid()
    {
        return appid;
    }

    /**
     * @param appid
     *        the appid to set
     */
    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    /**
     * @return secret
     */
    public String getSecret()
    {
        return secret;
    }

    /**
     * @param secret
     *        the secret to set
     */
    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    /**
     * @return accessToken
     */
    public String getAccessToken()
    {
        return accessToken;
    }

    /**
     * @param accessToken
     *        the accessToken to set
     */
    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }

    /**
     * @return expiresIn
     */
    public String getExpiresIn()
    {
        return expiresIn;
    }

    /**
     * @param expiresIn
     *        the expiresIn to set
     */
    public void setExpiresIn(String expiresIn)
    {
        this.expiresIn = expiresIn;
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
                .format("Oauth2AccessToken [grantType=%s, appid=%s, secret=%s, accessToken=%s, expiresIn=%s, refreshToken=%s, openId=%s, scope=%s, getErrcode()=%s, getErrmsg()=%s]",
                        grantType, appid, secret, accessToken, expiresIn, refreshToken, openId, scope, getErrcode(),
                        getErrmsg());
    }

}
