package com.hys.mgt.view.common.constants;

public class WebConstants
{
    /**
     * 用户保存到Session的Key
     */
    public final static String SESSION_USER = "SESSION_USER";

    /**
     * 手机注册验证码
     */
    public final static String SESSION_USER_REGISTER_CODE = "SESSION_USER_REGISTER_CODE";

    /**
     * 手机找回密码验证码
     */
    public final static String SESSION_USER_FINDPWD_CODE = "SESSION_USER_FINDPWD_CODE";

    /**
     * cookies ID
     */
    public final static String JSESSIONID = "JSESSIONID";

    /**
     * cookies 用户名
     */
    public final static String COOKIES_LOGIN_USERNAME = "COOKIES_LOGIN_USERNAMESSIONID";

    public final static String ERROR_VIEW = "error/error";


    public final static int PAGESIZE = 20;// 默认显示行数
    
    public final static String RESET_PASSWORD = "12345678";
    
 // 消息类型
    public final static String MSGTYPE_TEXT = "text";
    public final static String MSGTYPE_IMAGE = "image";
    public final static String MSGTYPE_VOICE = "voice";
    public final static String MSGTYPE_VIDEO = "video";
    public final static String MSGTYPE_LOCATION = "location";
    public final static String MSGTYPE_LINK = "link";
    public final static String MSGTYPE_EVEN = "event";
    public final static String MSGTYPE_NEWS = "news";
    /**
     * 开发模式下，多客服接入
     */
    public final static String MSGTYPE_CUSTOMER = "transfer_customer_service";

    // 事件类型
    public final static String EVEN_SUBSCRIBE = "subscribe";// 订阅
    public final static String EVEN_UNSUBSCRIBE = "unsubscribe";// 取消订阅
    public final static String EVEN_LOCATION = "LOCATION";
    public final static String EVEN_CLICK = "CLICK";

}
