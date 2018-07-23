package com.hys.commons.otherapi.wxapi.pay;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;

import org.slf4j.Logger;

import com.hys.commons.conf.ProfileManager;
import com.hys.commons.crypto.SHA1Coding;
import com.hys.commons.json.JsonConverter;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.logutil.LogUtil;
import com.hys.commons.string.RandomUtil;
import com.hys.commons.xml.XStreamUtil;


/**
 * 微信支付神器啊,各种加密,请求组包参数给你封装 好
 * 
 */
public class WxPayHelper
{
    private static final Logger log = LogProxy.getLogger(WxPayUtil.class);

    private final HashMap<String, String> parameters = new HashMap<>();
    private String appId = "";
    private String appKey = "";
    private String signType = "sha1";
    private String partnerKey = "";

    // 构造注入,免得写那么多烂set
    public WxPayHelper(String signType)
    {
        super();
        // 先设置基本信息
        this.appId = ProfileManager.getStringByKey("maowu_webapp_wx.wx_appId", "wx");

        // PaySingKey 对应于支付场景中的 appKey。
        this.appKey = WxPayUtil.getPaySignKey();

        // 财付通商户权限密钥 Key
        this.partnerKey = WxPayUtil.getPartnerKey();

        // 加密方式
        this.signType = signType;
    }

    public void setParameter(String key, String value)
    {
        parameters.put(key, value);
    }

    public String getParameter(String key)
    {
        return parameters.get(key);
    }

    private boolean checkCftParameters()
    {
        if (parameters.get("bank_type") == "" || parameters.get("body") == "" || parameters.get("partner") == ""
                || parameters.get("out_trade_no") == "" || parameters.get("total_fee") == ""
                || parameters.get("fee_type") == "" || parameters.get("notify_url") == null
                || parameters.get("spbill_create_ip") == "" || parameters.get("input_charset") == "")
        {
            return false;
        }
        return true;
    }

    // 最复杂蛋疼的packge生成方法
    public String getPayPackage() throws WXPayException
    {
        if ("" == partnerKey)
        {
            throw new WXPayException("密钥不能为空！");
        }

        return WxPayUtil.getCftPackage(parameters);
    }

    public String getBizSign(HashMap<String, String> bizObj) throws WXPayException
    {

        if (appKey == "")
        {
            throw new WXPayException("APPKEY为空！");
        }
        bizObj.put("appkey", appKey);

        // key一定小写,但value不转
        String bizString = WxPayUtil.formatQueryParaMap(bizObj, false);

        // 要测试,sh-1后转16进制时大写,再转小写能和微信对上吗？
        String bizSign = SHA1Coding.sha1ForWx(bizString).toLowerCase();

        LogUtil.debug(log, "getBizSign bizObj=[%s],bizSign=[%s],bizString=[%s]", bizObj, bizSign, bizString);
        return bizSign;
    }

    // 生成app支付请求json
    /*
     * { "appid":"wwwwb4f85f3a797777", "traceid":"crestxu", "noncestr":"111112222233333", "package":
     * "bank_type=WX&body=XXX&fee_type=1&input_charset=GBK&notify_url=http%3a%2f%2f www
     * .qq.com&out_trade_no=16642817866003386000&partner=1900000109&spbill_create_ip
     * =127.0.0.1&total_fee=1&sign=BEEF37AD19575D92E191C1E4B1474CA9", "timestamp":1381405298,
     * "app_signature":"53cca9d47b883bd4a5c85a9300df3da0cb48565c", "sign_method":"sha1" }
     */
    public String createAppPackage(String traceid) throws WXPayException
    {
        HashMap<String, String> nativeObj = new HashMap<String, String>();
        if (checkCftParameters() == false)
        {
            throw new WXPayException("生成package参数缺失！");
        }

        nativeObj.put("appid", appId);
        nativeObj.put("package", getPayPackage());
        nativeObj.put("timestamp", Long.toString(new Date().getTime() / 1000));
        nativeObj.put("traceid", traceid);
        nativeObj.put("noncestr", RandomUtil.getString(16));
        nativeObj.put("app_signature", getBizSign(nativeObj));
        nativeObj.put("sign_method", signType);

        return JsonConverter.format(nativeObj);
    }

    // 生成js 的api支付请求json
    /*
     * "appId" : "wxf8b4f85f3a794e77", //公众号名称，由商户传入 "timeStamp" : "189026618", //时间戳这里随意使用了一个值 "nonceStr" :
     * "adssdasssd13d", //随机串 "package" : "bank_type=WX&body=XXX&fee_type=1&input_charset=GBK&notify_url=http%3a%2f
     * %2fwww.qq.com&out_trade_no=16642817866003386000&partner=1900000109& spbill_create_i
     * p=127.0.0.1&total_fee=1&sign=BEEF37AD19575D92E191C1E4B1474CA9", //扩展字段，由商户传入 "signType" : "SHA1", //微信签名方式:sha1
     * "paySign" : "7717231c335a05165b1874658306fa431fe9a0de" //微信签名
     */
    public String createBizPackage() throws WXPayException
    {
        HashMap<String, String> nativeObj = new HashMap<String, String>();
        if (!checkCftParameters())
        {
            throw new WXPayException("生成package参数缺失！");
        }

        nativeObj.put("appId", appId);
        nativeObj.put("package", getPayPackage());
        nativeObj.put("timestamp", Long.toString(new Date().getTime()));
        nativeObj.put("noncestr", RandomUtil.getString(32));
        nativeObj.put("paySign", getBizSign(nativeObj));

        // 千万别搞错,signType不参与签名
        nativeObj.put("signType", signType);

        return JsonConverter.format(nativeObj);
    }

    // 生成原生支付url,就是扫描下二维码就支付
    /*
     * weixin://wxpay/bizpayurl?sign=XXXXX&appid=XXXXXX&productid=XXXXXX&timestamp =XXXXXX&noncestr=XXXXXX
     */
    public String createNativeUrl(String productid) throws WXPayException
    {
        String bizString = "";
        try
        {
            HashMap<String, String> nativeObj = new HashMap<String, String>();
            nativeObj.put("appid", appId);
            nativeObj.put("productid", URLEncoder.encode(productid, "utf-8"));
            nativeObj.put("timestamp", Long.toString(new Date().getTime() / 1000));
            nativeObj.put("noncestr", RandomUtil.getString(16));
            nativeObj.put("sign", getBizSign(nativeObj));
            bizString = WxPayUtil.formatQueryParaMap(nativeObj, false);

        }
        catch (Exception e)
        {
            throw new WXPayException(e.getMessage());
        }
        return "weixin://wxpay/bizpayurl?" + bizString;
    }

    // 生成原生支付请求xml
    /*
     * <xml> <AppId><![CDATA[wwwwb4f85f3a797777]]></AppId>
     * <Package><![CDATA[a=1&url=http%3A%2F%2Fwww.qq.com]]></Package> <TimeStamp> 1369745073</TimeStamp>
     * <NonceStr><![CDATA[iuytxA0cH6PyTAVISB28]]></NonceStr> <RetCode>0</RetCode> <RetErrMsg><![CDATA[ok]]></ RetErrMsg>
     * <AppSignature><![CDATA[53cca9d47b883bd4a5c85a9300df3da0cb48565c]]> </AppSignature> <SignMethod><![CDATA[sha1]]></
     * SignMethod > </xml>
     */
    public String createNativePackage(String retcode, String reterrmsg) throws WXPayException
    {
        HashMap<String, String> nativeObj = new HashMap<String, String>();
        if (checkCftParameters() == false && retcode == "0")
        {
            throw new WXPayException("生成package参数缺失！");
        }
        nativeObj.put("AppId", appId);
        nativeObj.put("Package", getPayPackage());
        nativeObj.put("TimeStamp", Long.toString(new Date().getTime() / 1000));
        nativeObj.put("RetCode", retcode);
        nativeObj.put("RetErrMsg", reterrmsg);
        nativeObj.put("NonceStr", RandomUtil.getString(16));
        nativeObj.put("AppSignature", getBizSign(nativeObj));
        nativeObj.put("SignMethod", signType);

        return XStreamUtil.mapToXml(nativeObj);
    }
}
