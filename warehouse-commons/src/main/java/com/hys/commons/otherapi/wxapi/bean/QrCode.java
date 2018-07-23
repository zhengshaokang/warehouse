package com.hys.commons.otherapi.wxapi.bean;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 * 二维码
 * 
 */
public class QrCode extends Base
{
    private static final long serialVersionUID = 1L;

    // 该二维码有效时间，以秒为单位。 最大不超过1800
    @JsonProperty("expire_seconds")
    private String expireSeconds;

    // 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
    @JsonProperty("action_name")
    private String actionName;

    // 二维码详细信息
    @JsonProperty("action_info")
    private QrCodeInfo actionInfo;

    // 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
    private String ticket;

    public static class QrCodeInfo
    {
        private Map<String, Object> scene;

        /**
         * @return scene
         */
        public Map<String, Object> getScene()
        {
            return scene;
        }

        /**
         * @param scene
         *        the scene to set
         */
        public void setScene(Map<String, Object> scene)
        {
            this.scene = scene;
        }
    }

    /**
     * @return expireSeconds
     */
    public String getExpireSeconds()
    {
        return expireSeconds;
    }

    /**
     * @param expireSeconds
     *        the expireSeconds to set
     */
    public void setExpireSeconds(String expireSeconds)
    {
        this.expireSeconds = expireSeconds;
    }

    /**
     * @return actionName
     */
    public String getActionName()
    {
        return actionName;
    }

    /**
     * @param actionName
     *        the actionName to set
     */
    public void setActionName(String actionName)
    {
        this.actionName = actionName;
    }

    /**
     * @return actionInfo
     */
    public QrCodeInfo getActionInfo()
    {
        return actionInfo;
    }

    /**
     * @param actionInfo
     *        the actionInfo to set
     */
    public void setActionInfo(QrCodeInfo actionInfo)
    {
        this.actionInfo = actionInfo;
    }

    /**
     * @return ticket
     */
    public String getTicket()
    {
        return ticket;
    }

    /**
     * @param ticket
     *        the ticket to set
     */
    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format(
                "QrCode [expireSeconds=%s, actionName=%s, actionInfo=%s, ticket=%s, getErrcode()=%s, getErrmsg()=%s]",
                expireSeconds, actionName, actionInfo, ticket, getErrcode(), getErrmsg());
    }

}
