package com.hys.commons.otherapi.wxapi.bean;

import java.io.Serializable;

public class Base implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String errcode;
    private String errmsg;

    /**
     * @return errcode
     */
    public String getErrcode()
    {
        return errcode;
    }

    /**
     * @param errcode
     *        the errcode to set
     */
    public void setErrcode(String errcode)
    {
        this.errcode = errcode;
    }

    /**
     * @return errmsg
     */
    public String getErrmsg()
    {
        return errmsg;
    }

    /**
     * @param errmsg
     *        the errmsg to set
     */
    public void setErrmsg(String errmsg)
    {
        this.errmsg = errmsg;
    }

    /*
     * (no-Javadoc) <p>Title: toString</p> <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("Base [errcode=%s, errmsg=%s]", errcode, errmsg);
    }

}
