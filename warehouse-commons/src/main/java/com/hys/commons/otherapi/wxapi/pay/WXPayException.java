package com.hys.commons.otherapi.wxapi.pay;

/**
 * 自定义异常支付异常
 * 
 */
public class WXPayException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public WXPayException(String str)
    {
        super(str);
    }
}
