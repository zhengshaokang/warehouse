package com.hys.mgt.view.common.vo;

import java.io.Serializable;

/**
 * 用于提示返回
 * 
 */
public class ResultPrompt implements Serializable
{

    private static final long serialVersionUID = 1L;

    private String statusCode; // 提示状态码 200：操作成功，300：操作失败（服务器错误），301：请求超时
    private String message; // 提示信息内容
    private String navTabId; // tab id，表示刷新指定的tab，可以是dialog
    private String callbackType; // 回调方式，closeCurrent表示关闭当前的tab或者dialog
    private String forwardUrl; // 转向url，表示当前tab或者dialog跳转到指定的url

    public ResultPrompt()
    {
        super();
    }

    public ResultPrompt(String statusCode, String message, String navTabId, String callbackType, String forwardUrl)
    {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.navTabId = navTabId;
        this.callbackType = callbackType;
        this.forwardUrl = forwardUrl;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode(String statusCode)
    {
        this.statusCode = statusCode;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getNavTabId()
    {
        return navTabId;
    }

    public void setNavTabId(String navTabId)
    {
        this.navTabId = navTabId;
    }

    public String getCallbackType()
    {
        return callbackType;
    }

    public void setCallbackType(String callbackType)
    {
        this.callbackType = callbackType;
    }

    public String getForwardUrl()
    {
        return forwardUrl;
    }

    public void setForwardUrl(String forwardUrl)
    {
        this.forwardUrl = forwardUrl;
    }

}
