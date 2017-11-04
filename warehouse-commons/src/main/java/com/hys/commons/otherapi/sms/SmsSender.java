package com.hys.commons.otherapi.sms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.slf4j.Logger;

import com.hys.commons.crypto.MD5Coding;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.logutil.LogUtil;

public class SmsSender
{
    private final static Logger log = LogProxy.getLogger(SmsSender.class);

    /**
     * 向指定手机号发送指定内容的短信
     * 
     * @param mobile
     *        手机号码
     * @param context
     *        短信内容
     * @return 成功为true 异常或失败都为false
     * @throws Exception
     */
    public final static boolean smsSend(String mobile, String context) throws Exception
    {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        try
        {
            long time = Math.round(new Date().getTime() / 1000);
            String pass = MD5Coding.encode2HexStr(("newmaowuszmaowu" + time).getBytes()).toLowerCase();
            String getURL = "http://211.147.222.37:8080/sendsms/?username=newmaowu&pwd=" + pass + "&dt=" + time
                    + "&msg=" + ToDBC(context) + "&mobiles=" + mobile + "&code=";

            // 根据拼凑的URL，打开连接，URL.openConnection函数会根据URL的类型，返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
            URL getUrl = new URL(getURL);
            conn = (HttpURLConnection) getUrl.openConnection();
            conn.connect();
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));// 设置编码,否则中文乱码
            String str = "";
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                str += line;
            }
            LogUtil.debug(SmsSender.log, "getURL[%s],mobile[%s],context[%s],str[%s]", getURL, mobile, context, str);
            return "0".equals(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (reader != null)
                {
                    reader.close();// 关闭
                }
                if (conn != null)
                {
                    conn.disconnect();// 断开连接
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static String ToDBC(String input)
    {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (c[i] == 12288)
            {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
            {
                c[i] = (char) (c[i] - 65248);
            }
        }
        return new String(c);
    }

    public static void main(String[] args) throws Exception
    {

        /*
         * boolean bb = SmsSender.smsSend("13570857022",
         * ToDBC("【猫屋】您购买的订单号为5429795725535866880的商品已到达[猫屋酷购汇便利店 （2174）],请尽快到门店提取"));
         * 亲，您的货物已于[%s]到达[%s],运单号：[%s]，取货密码:[%s]，请妥善保管并及时领取，如有疑问请致电4001009111,关注猫屋门店中的“猫屋”二维码，”扫码购果蔬、红包送邻居”！"
         */
        boolean bb = SmsSender.smsSend("13570857022",
                ToDBC("亲，您的货物已于到达,运单号：111，取货密码:111，请妥善保管并及时领取，如有疑问请致电4001009111。关注猫屋门店中的”猫屋“二维码，”扫码购果蔬、红包送邻居“!"));

        System.out.println(bb);
    }

}
