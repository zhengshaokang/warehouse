package com.hys.commons.conf;

import java.util.Properties;

import com.hys.commons.crypto.DESCoding;

/**
 * 对微信秘钥,等核心密码加密,一般的properties文件不用加密
 * 
 */
public class EncryptedProperties extends Properties
{
    private static final long serialVersionUID = -693644425932843130L;
    private DESCoding des = null;

    public EncryptedProperties()
    {
        super();
        try
        {
            des = new DESCoding("%$#(*N@MHGPL><NRMvMghsO*".getBytes("UTF-8"), "DESede/OFB/PKCS5Padding");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getProperty(String key)
    {
        try
        {
            return decrypt(super.getProperty(key).getBytes("ISO8859-1"));
        }
        catch (Exception e)
        {
            throw new RuntimeException("Couldn't decrypt property");
        }
    }

    private synchronized String decrypt(byte[] bytes) throws Exception
    {
        return new String(des.decode(bytes), "UTF-8");
    }

    @Override
    public synchronized Object setProperty(String key, String value)
    {
        try
        {
            return super.setProperty(key, new String(encrypt(value), "ISO8859-1"));
        }
        catch (Exception e)
        {
            throw new RuntimeException("Couldn't encrypt property");
        }
    }

    private synchronized byte[] encrypt(String message) throws Exception
    {
        return des.encode(message.getBytes("UTF-8"));
    }

}
