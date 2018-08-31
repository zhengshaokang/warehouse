package com.hys.commons.otherapi.wxapi;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;

import com.hys.commons.conf.ProfileManager;
import com.hys.commons.crypto.Base64Ext;
import com.hys.commons.crypto.SHA1Coding;
import com.hys.commons.http.HttpInvokeUtil;
import com.hys.commons.json.JsonConverter;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.logutil.LogUtil;
import com.hys.commons.otherapi.wxapi.bean.AccessToken;
import com.hys.commons.otherapi.wxapi.bean.FollowersList;
import com.hys.commons.otherapi.wxapi.bean.Groups;
import com.hys.commons.otherapi.wxapi.bean.Media;
import com.hys.commons.otherapi.wxapi.bean.Menu;
import com.hys.commons.otherapi.wxapi.bean.Message;
import com.hys.commons.otherapi.wxapi.bean.Oauth2AccessToken;
import com.hys.commons.otherapi.wxapi.bean.QrCode;
import com.hys.commons.otherapi.wxapi.bean.UserInfo;
import com.hys.commons.otherapi.wxapi.bean.resp.Article;
import com.hys.commons.otherapi.wxapi.bean.resp.Articles;
import com.hys.commons.otherapi.wxapi.bean.resp.NewsMessage;
import com.hys.commons.otherapi.wxapi.bean.resp.PayResponseBean;
import com.hys.commons.otherapi.wxapi.bean.resp.TextMessage;
import com.hys.commons.otherapi.wxapi.bean.resp.WxAlarmNotify;
import com.hys.commons.otherapi.wxapi.bean.resp.WxComplaints;
import com.hys.commons.otherapi.wxapi.bean.resp.WxPayOrder;
import com.hys.commons.string.StringUtil;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.commons.util.Triple;
import com.hys.commons.xml.XStreamUtil;


public class WeiXinApiUtil
{
    private static final Logger log = LogProxy.getLogger(WeiXinApiUtil.class);

    /**
     * 获取用户OpenId;session中存在从session取；session中不存在，通过接口从微信取
     * 
     * @param code
     * @param session
     * @return
     */
    public static String getOpenIdWidthSession(String code, HttpSession session)
    {
        String openId = (String) session.getAttribute("openId");
        if (StringUtils.isBlank(openId))
        {
            Oauth2AccessToken accessToken = WeiXinApiUtil.getOauth2AccessToken(code);
            if (null != accessToken && StringUtils.isNotBlank(accessToken.getAccessToken()))
            {
                openId = accessToken.getOpenId();
                session.setAttribute("openId", openId);
            }
        }

        if ("001".equals(code))
        {
            openId = "ofjtSuEEnnT7SPgioyeBTKeud2Bk";
            session.setAttribute("openId", openId);
        }

        return openId;
    }

    /**
     * 获取网页授权access_token 与基础支持中的access_token不同
     * 
     * @param code
     */
    public static Oauth2AccessToken getOauth2AccessToken(String code)
    {
        if (StringUtils.isNotBlank(code))
        {
            String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_qrCode_create_url",
                    "https://api.weixin.qq.com/sns/oauth2/access_token");
            String appId = ProfileManager.getStringByKey("maowu_webapp_wx.wx_appId", "wx14003bdd65360399");
            String secret = ProfileManager.getStringByKey("maowu_webapp_wx.wx_appSecret", "");
            String grant_type = "authorization_code";

            Map<String, String> map = new HashMap<String, String>();
            map.put("appId", appId);
            map.put("secret", secret);
            map.put("grant_type", grant_type);
            map.put("code", code);
            String json = HttpInvokeUtil.httpGet(url, map);

            return JsonConverter.parse(json, Oauth2AccessToken.class);
        }
        else
        {
            return null;
        }
    }
    
    
    /**
	 * 获得ACCESS_TOKEN
	 * @param appid
	 * @param secret
	 * @return ACCESS_TOKEN
	 */
	public static String getAccessToken(String appid, String secret) {
		String url = "https://api.weixin.qq.com/cgi-bin/token";
		Map<String,String> param = new HashMap<String,String>();
		param.put("grant_type", "client_credential");
		param.put("appid", appid);
		param.put("secret", secret);
		try {
			String json = HttpInvokeUtil.httpGet(url, param);
			Map<String,String> map  = JsonConverter.parse(json, HashMap.class);
			return map.get("access_token");
		}catch (Exception e) {
		}
		return "";
	}

    /**
     * 通过ticket换取二维码<br/>
     * 返回String，是一个数据流
     * 
     * @param ticket
     * @return String
     */
    public static HttpEntity getQrCode(String ticket)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_qrCode_get_url",
                "https://mp.weixin.qq.com/cgi-bin/showqrcode");
        Map<String, String> map = new HashMap<String, String>();
        map.put("ticket", ticket);
        return HttpInvokeUtil.streamHttpGet(url, map);
    }

    /**
     * 创建二维码ticket<br/>
     * 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
     * 
     * @param qrCode
     *        QrCode
     * @param accessToken
     * @return
     */
    public static QrCode createQrCode(QrCode qrCode, String accessToken)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_qrCode_create_url",
                "https://api.weixin.qq.com/cgi-bin/qrcode/create");
        String jsonStr = JsonConverter.format(qrCode);
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        map.put("body ", jsonStr);
        String json = HttpInvokeUtil.httpPost(url, map);
        return JsonConverter.parse(json, QrCode.class);
    }

    /**
     * 通过网页授权，拉取用户信息
     * 
     * @param accessToken
     * @param openid
     * @return
     */
    public static UserInfo getUserInfoSNS(String accessToken, String openid)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_sns_getUserInfo_url",
                "https://api.weixin.qq.com/sns/userinfo");
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        map.put("openid", openid);
        map.put("lang", "zh_CN");
        String json = HttpInvokeUtil.httpGet(url, map);
        return JsonConverter.parse(json, UserInfo.class);
    }
    /**
     * @param accessToken 调用接口凭证
     * @param touser  要发送的微信openid
     * @param templateId 模板Id
     * @param url   模板跳转链接 
     * @param miniprogram   跳小程序所需数据，不需跳小程序可不用传该数据
     * @param data 模板数据
     * @return
     */
    public static String sendTemplateMessage (String accessToken,String touser,String templateId,String url,Map<String,Object> miniprogram,Map<String,Object> data){
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("touser", touser);
        map.put("template_id", templateId);
        map.put("url", url);
        map.put("miniprogram", miniprogram);
        map.put("data", data);
        
        String rqurl = "https://api.weixin.qq.com/cgi-bin/message/template/send";
        String jsonStr = JsonConverter.format(map);
        
        
        HttpPost httpPost = new HttpPost(rqurl + "?access_token=" + accessToken);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
        
        String json = HttpInvokeUtil.httpPost(httpPost);
        System.out.println("返回数据："+json);
        return json;
    }

    /**
     * 发送图文消息
     * 
     * @param accessToken
     *        调用接口凭证
     * @param touser
     *        普通用户openid
     * @param articles
     *        Article
     * @return
     */
    public static String responseCustomNews(String accessToken, String touser, Article[] articles)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", touser);
        map.put("msgtype", "news");
//
        Map<String, Object> mapArticle = new HashMap<String, Object>();
        mapArticle.put("articles", articles);
        map.put("news", mapArticle);
//
//        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_custom_send_url",
//                "https://api.weixin.qq.com/cgi-bin/message/custom/send");
//        String jsonStr = JsonConverter.format(map);
//
//        Map<String, String> paramMap = new HashMap<String, String>();
//        paramMap.put("access_token", accessToken);
//        paramMap.put("body", jsonStr);
//        return HttpInvokeUtil.httpPost(url, paramMap);
    	
    	
    	String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
        String jsonStr = JsonConverter.format(map);
        
        System.out.println(jsonStr);
        
        HttpPost httpPost = new HttpPost(url + "?access_token=" + accessToken);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
        
        String json = HttpInvokeUtil.httpPost(httpPost);
        return json;
    }
    
    /**
     * 获取素材列表
     * 
     */
    
    public static String getMaterials(String accessToken) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("type", "news");
        map.put("offset", 0);
        map.put("count", 20);

        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material";
        String jsonStr = JsonConverter.format(map);
        
        HttpPost httpPost = new HttpPost(url + "?access_token=" + accessToken);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
        
        String json = HttpInvokeUtil.httpPost(httpPost);
        
        JsonNode jsonNode = JsonConverter.getNode(json, "item");
        
        if(null != jsonNode) {
        	List<Map<String,Object>> list = JsonConverter.jsonNode2GenericObject(jsonNode, new TypeReference<List<Map<String,Object>>>(){});
        	if(null !=list ) {
        		for (Map<String, Object> map2 : list) {
        			String media_id = (String) map2.get("media_id");
        			Map<String,Object> content = (HashMap<String, Object>) map2.get("content");
        			List<Map<String,Object>> newsItem = (ArrayList<Map<String,Object>>) content.get("news_item");
        			if(LogicUtil.isNotNullAndEmpty(newsItem)) {
        				for (Map<String, Object> map3 : newsItem) {
        					String title = (String) map3.get("title"); //图文消息的标题
        					String thumb_media_id = (String) map3.get("thumb_media_id"); //图文消息的封面图片素材id（必须是永久mediaID）
        					String show_cover_pic = (String) map3.get("show_cover_pic"); //是否显示封面，0为false，即不显示，1为true，即显示
        					String author = (String) map3.get("author"); //	作者
        					String digest = (String) map3.get("digest"); //图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
        					String html = (String) map3.get("content"); //图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
        					String newsurl = (String) map3.get("url"); //图文页的URL，或者，当获取的列表是图片素材列表时，该字段是图片的URL
        					String content_source_url = (String) map3.get("content_source_url"); //图文消息的原文地址，即点击“阅读原文”后的URL
						}
        			}
        			
				}
        	}
        }
        
        return HttpInvokeUtil.httpPost(httpPost);
    }

    /**
     * 发送音乐消息
     * 
     * @param touser
     *        普通用户openid
     * @param title
     *        音乐标题
     * @param des
     *        音乐描述
     * @param musicurl
     *        音乐链接
     * @param hqmusicurl
     *        高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumb_media_id
     *        缩略图的媒体ID
     * @param accessToken
     *        调用接口凭证
     * @return
     */
    public static String responseCustomMusic(String touser, String title, String des, String musicurl,
            String hqmusicurl, String thumb_media_id, String accessToken)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_custom_send_url",
                "https://api.weixin.qq.com/cgi-bin/message/custom/send");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", touser);
        map.put("msgtype", "image");

        Map<String, String> m = new HashMap<String, String>();
        m.put("title", title);
        m.put("description", des);
        m.put("musicurl", musicurl);
        m.put("hqmusicurl", hqmusicurl);
        m.put("thumb_media_id", thumb_media_id);
        map.put("music", m);
        String jsonStr = JsonConverter.format(map);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        paramMap.put("body", jsonStr);
        return HttpInvokeUtil.httpPost(url, paramMap);
    }

    /**
     * 发送视频消息
     * 
     * @param touser
     *        普通用户openid
     * @param media_id
     *        发送的视频的媒体ID
     * @param title
     *        视频消息的标题
     * @param des
     *        视频消息的描述
     * @param accessToken
     *        调用接口凭证
     * @return
     */
    public static String responseCustomVideo(String touser, String media_id, String title, String des,
            String accessToken)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_custom_send_url",
                "https://api.weixin.qq.com/cgi-bin/message/custom/send");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", touser);
        map.put("msgtype", "image");

        Map<String, String> m = new HashMap<String, String>();
        m.put("media_id", media_id);
        m.put("title", title);
        m.put("description", des);
        map.put("video", m);
        String jsonStr = JsonConverter.format(map);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        paramMap.put("body", jsonStr);
        return HttpInvokeUtil.httpPost(url, paramMap);
    }

    /**
     * 发送语音消息
     * 
     * @param touser
     *        普通用户openid
     * @param media_id
     *        发送的语音的媒体ID
     * @param accessToken
     *        调用接口凭证
     * @return
     */
    public static String responseCustomVoice(String touser, String media_id, String accessToken)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_custom_send_url",
                "https://api.weixin.qq.com/cgi-bin/message/custom/send");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", touser);
        map.put("msgtype", "image");

        Map<String, String> m = new HashMap<String, String>();
        m.put("media_id", media_id);
        map.put("voice", m);
        String jsonStr = JsonConverter.format(map);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        paramMap.put("body", jsonStr);
        return HttpInvokeUtil.httpPost(url, paramMap);
    }

    /**
     * 发送图片消息
     * 
     * @param touser
     *        调用接口凭证
     * @param media_id
     *        发送的图片的媒体ID
     * @param accessToken
     *        调用接口凭证
     * @return
     */
    public static String responseCustomImage(String touser, String media_id, String accessToken)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_custom_send_url",
                "https://api.weixin.qq.com/cgi-bin/message/custom/send");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", touser);
        map.put("msgtype", "image");

        Map<String, String> m = new HashMap<String, String>();
        m.put("media_id", media_id);
        map.put("image", m);
        String jsonStr = JsonConverter.format(map);

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        paramMap.put("body", jsonStr);
        return HttpInvokeUtil.httpPost(url, paramMap);
    }

    /**
     * 发送客服消息
     * 
     * @param touser
     *        普通用户openid
     * @param content
     *        文本消息内容
     * @param accessToken
     *        调用接口凭证
     * @return
     */
    public static String responseCustomText(String touser, String content, String accessToken)
    {
//        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_custom_send_url",
//                "https://api.weixin.qq.com/cgi-bin/message/custom/send");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", touser);
        map.put("msgtype", "text");
        Map<String, String> m = new HashMap<String, String>();
        m.put("content", content);
        map.put("text", m);
        //String jsonStr = JsonConverter.format(map);
       // System.out.println(jsonStr);
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        //paramMap.put("body", jsonStr);
       // return HttpInvokeUtil.httpPost(url, paramMap);
        
        
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
        String jsonStr = JsonConverter.format(map);
        
        HttpPost httpPost = new HttpPost(url + "?access_token=" + accessToken);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
        
        String json = HttpInvokeUtil.httpPost(httpPost);
        System.out.println(json);
        return json;
    }
    
    public static String responseCustomImag(String touser, String content, String accessToken){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("touser", touser);
        map.put("msgtype", "image");
        Map<String, String> m = new HashMap<String, String>();
        m.put("media_id", content);
        map.put("image", m);
        //String jsonStr = JsonConverter.format(map);
       // System.out.println(jsonStr);
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        //paramMap.put("body", jsonStr);
       // return HttpInvokeUtil.httpPost(url, paramMap);
        
        
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
        String jsonStr = JsonConverter.format(map);
        
        HttpPost httpPost = new HttpPost(url + "?access_token=" + accessToken);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
        
        String json = HttpInvokeUtil.httpPost(httpPost);
        System.out.println(json);
        return json;
    }

    /**
     * bean转换成xml
     * 
     * @param msg
     * @return xml String
     */
    public static String responseMsgXml(Object msg)
    {
        return XStreamUtil.toXML(msg, true);
    }

    /**
     * 解析用户消息，需自行判断消息类型，事件类型
     * 
     * @param in
     *        InputStream xml数据流
     * @return Message
     */
    public static Message paseWXMessage(InputStream in)
    {
        return XStreamUtil.readXml2Bean(in, Message.class);
    }

    public static PayResponseBean pasePayRes(InputStream in)
    {
        return XStreamUtil.readXml2Bean(in, PayResponseBean.class);
    }

    /**
     * 自定义菜单删除接口
     * 
     * @param accessToken
     * @return Menu
     */
    public static Menu delMenu(String accessToken)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_delMenu_url",
                "https://api.weixin.qq.com/cgi-bin/menu/delete");
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        String json = HttpInvokeUtil.httpGet(url, map);
        return JsonConverter.parse(json, Menu.class);
    }

    /**
     * 自定义菜单查询接口
     * 
     * @param accessToken
     * @return Menu[]
     */
    public static Menu[] getMenu(String accessToken)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_getMenu_url",
                "https://api.weixin.qq.com/cgi-bin/menu/get");
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        String json = HttpInvokeUtil.httpGet(url, map);
        JsonNode menuNode = JsonConverter.parse(json, JsonNode.class);
        Menu[] menuArry = JsonConverter.parse(menuNode.path("menu").path("button"), Menu[].class);
        return menuArry;
    }

    /**
     * 自定义菜单创建接口
     * 
     * @param accessToken
     * @param jsonStr
     *        菜单json
     * @return
     */
    public static Menu createMenu(String accessToken, String jsonStr)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_createMenu_url",
                "https://api.weixin.qq.com/cgi-bin/menu/create");

        HttpPost httpPost = new HttpPost(url + "?access_token=" + accessToken);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
        String json = HttpInvokeUtil.httpPost(httpPost);
        return JsonConverter.parse(json, Menu.class);
    }

    /**
     * 获取关注者列表
     * 
     * @param accessToken
     *        调用接口凭证
     * @param next_openid
     *        第一个拉取的OPENID ，不填默认从头开始拉取
     * @return
     */
    public static FollowersList getFollower(String accessToken, String next_openid)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_userInfo_url",
                "https://api.weixin.qq.com/cgi-bin/user/get");
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        map.put("next_openid", next_openid);
        String json = HttpInvokeUtil.httpGet(url, map);
        JsonNode node = JsonConverter.parse(json, JsonNode.class);
        FollowersList f = JsonConverter.parse(node, FollowersList.class);
        f.setData(JsonConverter.parse(node.path("data").path("openid"), String[].class));
        return f;
    }

    /**
     * 获取用户基本信息
     * 
     * @param accessToken
     *        调用接口凭证
     * @param openId
     *        普通用户的标识，对当前公众号唯一
     * @param lang
     *        返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return UserInfo
     */
    public static UserInfo getUserInfo(String accessToken, String openId, String lang)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_userInfo_url",
                "https://api.weixin.qq.com/cgi-bin/user/info");
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        map.put("openId", openId);
        map.put("lang", lang);
        String json = HttpInvokeUtil.httpGet(url, map);
        return JsonConverter.parse(json, UserInfo.class);
    }

    /**
     * 微信接口验证 加密/校验流程： 1. 将token、timestamp、nonce三个参数进行字典序排序 2. 将三个参数字符串拼接成一个字符串进行sha1加密 3.
     * 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce, String echostr, String token)
    {
        String[] tmpArr = { token, timestamp, nonce };
        Arrays.sort(tmpArr);
        String bigStr = tmpArr[0] + tmpArr[1] + tmpArr[2];
        // SHA1加密
        String digest = SHA1Coding.encode2HexStr(bigStr.getBytes());
        if (digest.equalsIgnoreCase(signature))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 上传多媒体文件 上传的多媒体文件有格式和大小限制，如下：<br/>
     * 图片（image）: 128K，支持JPG格式<br/>
     * 语音（voice）：256K，播放长度不超过60s，支持AMR\MP3格式<br/>
     * 视频（video）：1MB，支持MP4格式<br/>
     * 缩略图（thumb）：64KB，支持JPG格式
     * 
     * @param accessToken
     *        调用接口凭证
     * @param types
     *        媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param file
     *        文件
     * @return Media
     */
    public static Media weixUpload(String accessToken, String type, String filePath)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_UploadMedia_url",
                "http://file.api.weixin.qq.com/cgi-bin/media/upload");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        paramMap.put("type", type);
        String json = HttpInvokeUtil.httpPostFile(url, paramMap, "media", filePath);
        return JsonConverter.parse(json, Media.class);
    }

    /**
     * 下载多媒体文件 <br/>
     * 下载成功返回为数据流，需判断是否为InputStream<br/>
     * 下载失败返回json {"errcode":40007,"errmsg":"invalid media_id"}
     * 
     * @param accessToken
     *        调用接口凭证
     * @param media_id
     *        媒体文件ID
     * @return Object
     * @throws Exception
     */
    public static HttpEntity weixDowload(String accessToken, String media_id) throws Exception
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_getMedia_url",
                "http://file.api.weixin.qq.com/cgi-bin/media/get");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("access_token", accessToken);
        paramMap.put("media_id", media_id);
        return HttpInvokeUtil.streamHttpGet(url, paramMap);

        // Object in = null;
        // CloseableHttpClient httpClient = HttpClients.createDefault();
        // try
        // {
        // HttpGet httpGet = new HttpGet(url);
        //
        // List<NameValuePair> params = new ArrayList<NameValuePair>();
        // // 传递参数
        // params.add(new BasicNameValuePair("access_token", access_token));
        // params.add(new BasicNameValuePair("media_id", media_id));
        //
        // // 设置参数
        // String str = EntityUtils.toString(new UrlEncodedFormEntity(params));
        // httpGet.setURI(new URI(httpGet.getURI().toString() + "?" + str));
        //
        // logger.debug("发起请求的页面地址 " + httpGet.getRequestLine());
        // // 发起请求 并返回请求的响应
        // CloseableHttpResponse response = httpClient.execute(httpGet);
        // try
        // {
        // // 获取响应对象
        // HttpEntity resEntity = response.getEntity();
        // // 下载失败，返回json
        // if ("text/plain".equals(resEntity.getContentType()))
        // {
        // in = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
        // }
        // else
        // {
        // if (resEntity != null)
        // {
        // in = resEntity.getContent();
        // }
        // }
        // // 销毁
        // EntityUtils.consume(resEntity);
        // }
        // finally
        // {
        // response.close();
        // }
        // }
        // finally
        // {
        // httpClient.close();
        // }
        // return in;
    }

    /**
     * 获取access token
     * 
     * @param appid
     *        第三方用户唯一凭证
     * @param secret
     *        第三方用户唯一凭证密钥，即appsecret
     * @return AccessToken 正常情况下，微信会返回下述JSON数据包： {"access_token":"ACCESS_TOKEN","expires_in":7200}
     *         错误时微信会返回错误码等信息，JSON数据包: {"errcode":40013,"errmsg":"invalid appid"}
     */
    public static AccessToken getWeixAccessToken(String appid, String secret)
    {
        String grant_type = "client_credential";
        if (StringUtils.isBlank(appid))
        {
            appid = ProfileManager.getByKey("maowu_webapp_wx.wx_appId");
        }
        if (StringUtils.isBlank(secret))
        {
            secret = ProfileManager.getByKey("maowu_webapp_wx.wx_appSecret");
        }

        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_token_url",
                "https://api.weixin.qq.com/cgi-bin/token");
        Map<String, String> map = new HashMap<String, String>();
        map.put("grant_type", grant_type);
        map.put("appid", appid);
        map.put("secret", secret);
        String json = HttpInvokeUtil.httpGet(url, map);
        LogUtil.debug(log, "getWeixAccessToken currentTime:[%s]",
                DateUtil.getCurrentDateAsString(DateUtil.Format_Date + " " + DateUtil.Format_Time));
        return JsonConverter.parse(json, AccessToken.class);
    }

    /**
     * 创建分组<br/>
     * 一个公众账号，最多支持创建500个分组
     * 
     * @param accessToken
     *        调用接口凭证
     * @param name
     *        分组名字（30个字符以内）
     * @return 返回说明 <br/>
     *         Groups
     * @throws Exception
     */
    public static Groups createGroups(String accessToken, String name)
    {
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(name))
        {
            throw new RuntimeException("参数不能为空");
        }
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_group_create_url",
                "https://api.weixin.qq.com/cgi-bin/groups/create");
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        Map<String, Map<String, String>> param = new HashMap<String, Map<String, String>>();
        param.put("group", map);
        String jsonStr = JsonConverter.format(param);

        HttpPost httpPost = new HttpPost(url + "?access_token=" + accessToken);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
        String json = HttpInvokeUtil.httpPost(httpPost);
        Groups group = JsonConverter.parse(json, Groups.class);

        return group;
    }

    /**
     * 查询所有分组
     * 
     * @param accessToken
     *        调用接口凭证
     * @return Groups[]
     */
    public static Groups[] getGroups(String accessToken)
    {
        if (StringUtils.isBlank(accessToken))
        {
            throw new RuntimeException("参数不能为空");
        }
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_group_select_url",
                "https://api.weixin.qq.com/cgi-bin/groups/get");
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        String json = HttpInvokeUtil.httpGet(url, map);
        JsonNode node = JsonConverter.parse(json, JsonNode.class);
        if (node.path("groups").isContainerNode())
        {
            return JsonConverter.parse(node.path("groups"), Groups[].class);
        }
        else
        {
            return JsonConverter.parse(node, Groups[].class);
        }
    }

    public static Map<String, Groups> getGroupMap(String accessToken)
    {
        Map<String, Groups> map = new HashMap<String, Groups>();
        Groups[] groups = getGroups(accessToken);
        for (Groups group : groups)
        {
            map.put(group.getId(), group);
        }
        return map;
    }

    /**
     * 查询用户所在分组
     * 
     * @param accessToken
     *        调用接口凭证
     * @param openid
     *        用户的OpenID
     * @return
     */
    public static Groups getGroupsByOpenId(String accessToken, String openId)
    {
        try
        {
            if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openId))
            {
                throw new RuntimeException("参数不能为空");
            }
            String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_group_select_user_url",
                    "https://api.weixin.qq.com/cgi-bin/groups/getid");
            Map<String, String> param = new HashMap<String, String>(10);
            param.put("openid", openId);
            String jsonStr = JsonConverter.format(param);

            log.debug("getGroupsByOpenId--" + url + "?access_token=" + accessToken);
            HttpPost httpPost = new HttpPost(url + "?access_token=" + accessToken);
            httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
            String json = HttpInvokeUtil.httpPost(httpPost);
            log.debug("getGroupsByOpenId json--" + json);
            Groups group = JsonConverter.parse(json, Groups.class);
            // groupId与id是同一个东西，微信中查询用户所在分组只返回groupId,其他接口使用id...
            group.setId(group.getGroupId());
            return group;
        }
        catch (Throwable e)
        {
            log.error("==>Groups err", e);
        }
        return null;
    }

    /**
     * 修改分组名
     * 
     * @param accessToken
     *        调用接口凭证
     * @param id
     *        分组id，由微信分配
     * @param name
     *        分组名字（30个字符以内）
     * @return 返回说明 <br/>
     *         正常时的返回：<br/>
     *         errcode = 0, errmsg = ok<br/>
     *         错误时（该示例为AppID无效错误）：<br/>
     *         errcode=40013,errmsg=invalid appid
     */
    public static Groups updateGroups(String accessToken, String id, String name)
    {
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(id) || StringUtils.isBlank(name))
        {
            throw new RuntimeException("参数不能为空");
        }
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_group_update_groupName_url",
                "https://api.weixin.qq.com/cgi-bin/groups/update");
        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);
        map.put("id", id);
        map.put("name", name);
        String json = HttpInvokeUtil.httpPost(url, map);
        return JsonConverter.parse(json, Groups.class);
    }

    /**
     * 移动用户分组
     * 
     * @param accessToken
     *        调用接口凭证
     * @param openid
     *        用户唯一标识符
     * @param to_groupid
     *        分组id
     * @return 返回说明 <br/>
     *         正常时的返回：<br/>
     *         errcode = 0, errmsg = ok<br/>
     *         错误时（该示例为AppID无效错误）：<br/>
     *         errcode=40013,errmsg=invalid appid
     * @author:
     */
    public static Groups updateUserGroups(String accessToken, String openid, String to_groupid)
    {
        if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(openid) || StringUtils.isBlank(to_groupid))
        {
            throw new RuntimeException("参数不能为空");
        }
        String url = ProfileManager.getStringByKey("maowu_webapp_wx.wxcgi_group_update_userGroup_url",
                "https://api.weixin.qq.com/cgi-bin/groups/members/update");
        Map<String, String> map = new HashMap<String, String>();
        map.put("openid", openid);
        map.put("to_groupid", to_groupid);
        String jsonStr = JsonConverter.format(map);
        HttpPost httpPost = new HttpPost(url + "?access_token=" + accessToken);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
        String json = HttpInvokeUtil.httpPost(httpPost);
        return JsonConverter.parse(json, Groups.class);
    }

    // 发货通知告诉微信后台该订单的发货状态
    // deliverStatus 1成功,0失败,JsonNode包含错误码和错误描述,controller必须判断并提示用户
    public static JsonNode delivernotify(String accessToken, Map<String, String> paramMap)
    {
        String url = getUrlAndToken(accessToken, "wxcgi_delivernotify", "https://api.weixin.qq.com/pay/delivernotify");
        log.debug("delivernotify url:" + url);
        String jsonStr = JsonConverter.format(paramMap);
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));

        String json = HttpInvokeUtil.httpPost(httpPost);

        return JsonConverter.parse(json, JsonNode.class);
    }

    // 订单查询,查询微信后台
    public static Triple<String, String, WxPayOrder> qryOrder(String accessToken, Map<String, String> paramMap)
    {
        if (StringUtil.isEmpty(accessToken))
        {
            return null;
        }
        String url = getUrlAndToken(accessToken, "wxcgi_orderqry", "https://api.weixin.qq.com/pay/orderquery");
        HttpPost httpPost = new HttpPost(url);
        String jsonStr = JsonConverter.format(paramMap);
        httpPost.setEntity(new StringEntity(jsonStr, "UTF-8"));
        String json = HttpInvokeUtil.httpPost(httpPost);
        if (StringUtil.isEmpty(json))
        {
            return null;
        }
        JsonNode node = JsonConverter.parse(json, JsonNode.class);
        WxPayOrder order = new WxPayOrder();
        if ("0".equals(node.path("errcode").asText()))
        {
            order = JsonConverter.parse(node.path("order_info"), WxPayOrder.class);
        }
        return Triple.makeTriple(node.path("errcode").asText(), node.path("errmsg").asText(), order);
    }

    // 处理完投诉反馈给微信
    public static JsonNode updateComplaints(String accessToken, String openid, String feedbackid)
    {
        String url = getUrlAndToken(accessToken, "wxcgi_uppayfeedback", "");
        Map<String, String> paramMap = new HashMap<>(20);
        paramMap.put("openid", openid);
        paramMap.put("feedbackid", feedbackid);

        String json = HttpInvokeUtil.httpGet(url, paramMap);
        return JsonConverter.parse(json, JsonNode.class);
    }

    public static String getUrlAndToken(String accessToken, String key, String defaultStr)
    {
        String url = ProfileManager.getStringByKey("maowu_webapp_wx." + key, defaultStr);
        if (!StringUtil.isEmpty(accessToken))
        {
            url += "?access_token=" + accessToken;
        }
        return url;
    }

    // 新增用户投诉|确认处理完投诉
    public static WxComplaints parseWxComplaints(InputStream in)
    {
        return XStreamUtil.readXml2Bean(in, WxComplaints.class);
    }

    // 微信告警通知
    public static WxAlarmNotify parseWxAlarmNotify(InputStream in)
    {
        return XStreamUtil.readXml2Bean(in, WxAlarmNotify.class);
    }

    public static void main(String[] args)
    {
        // String json =
        // "{\"groups\": [{\"id\": 0,\"name\": \"未分组\",\"count\": 72596},{\"id\": 1,\"name\": \"黑名单\",\"count\": 36},{\"id\": 2,\"name\": \"星标组\",\"count\": 8},{\"id\": 104,\"name\": \"华东媒\",\"count\": 4},{\"id\": 106,\"name\": \"★不测试组★\",\"count\": 1}]}";
        //
        // JsonNode node = JsonConverter.parse(json, JsonNode.class);
        //
        // Groups[] g = JsonConverter.parse(node.path("groups"), Groups[].class);
        //
        // System.out.println(g[0].getName());
        // System.out.println(g[1].getName());
        //
        // String json1 =
        // "{\"total\":23000,\"count\":10000,\"data\":{\"openid\":[\"OPENID1\",\"OPENID2\",\"OPENID10000\"]},\"next_openid\":\"NEXT_OPENID1\"}";
        // // String json1 =
        // //
        // "{\"total\":23000,\"count\":10000,\"openid\":[\"OPENID1\",\"OPENID2\",\"OPENID10000\"],\"next_openid\":\"NEXT_OPENID1\"}";
        // JsonNode node1 = JsonConverter.parse(json1, JsonNode.class);
        // FollowersList f = JsonConverter.parse(node1, FollowersList.class);
        // // FollowersList f = JsonConverter.parse(json1, FollowersList.class);
        // f.setData(JsonConverter.parse(node1.path("data").path("openid"), String[].class));
        // String[] list = f.getData();
        // for (String str : list)
        // {
        // System.out.println(str);
        // }

        // 菜单示列
        // Menu m = new Menu();
        // m.setName("搜索");
        // m.setType("view");
        // m.setUrl("http://www.soso.com/");
        //
        // Menu m1 = new Menu();
        // m1.setName("视频");
        // m1.setType("view");
        // m1.setUrl("http://v.qq.com/");
        //
        // Menu m2 = new Menu();
        // m2.setName("赞一下我们");
        // m2.setType("click");
        // m2.setUrl("V1001_GOOD");
        //
        // Menu m3 = new Menu();
        // m3.setName("今日歌曲");
        // m3.setType("click");
        // m3.setUrl("V1001_TODAY_MUSIC");
        //
        // Menu m4 = new Menu();
        // m4.setName("歌手简介");
        // m4.setType("click");
        // m4.setUrl("V1001_TODAY_SINGER");
        //
        // Menu m5 = new Menu();
        // m5.setName("菜单");
        // m5.setSub_button(new Menu[] { m2, m3, m4 });
        //
        // Menu[] m6 = new Menu[] { m, m1, m5 };
        // Map map = new HashMap<String, Menu[]>();
        // map.put("button", m6);
        // // System.out.println(JsonConverter.format(map));
        //
        // String menuJson =
        // "{\"menu\":{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\",\"sub_button\":[]},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\",\"sub_button\":[]},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\",\"sub_button\":[]},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\",\"sub_button\":[]}]}]}}";
        // JsonNode menuNode = JsonConverter.parse(menuJson, JsonNode.class);
        // Menu[] menuArry = JsonConverter.parse(menuNode.path("menu").path("button"), Menu[].class);
        // for (Menu mm : menuArry)
        // {
        // System.out.println(mm);
        // }

        // 消息回复转换xml
//        TextMessage t = new TextMessage();
//        t.setToUserName("toUser");
//        t.setFromUserName("fromUser");
//        t.setCreateTime(1234565);
//        t.setContent("content");
//        t.setMsgType("text");
//        // System.out.println(XStreamUtil.toXML(t, true));
//
//        Article a1 = new Article();
//        a1.setDescription("description");
//        a1.setPicUrl("picUrl");
//        a1.setTitle("title");
//        a1.setUrl("url");
//
//        Article a2 = new Article();
//        a2.setDescription("description2");
//        a2.setPicUrl("picUrl2");
//        a2.setTitle("title2");
//        a2.setUrl("url2");
//
//        List<Article> list = new ArrayList<Article>();
//        list.add(a1);
//        list.add(a2);
//        // Map<String, Object> m = new HashMap<String, Object>();
//        // m.put("Articles", list);
//        NewsMessage n = new NewsMessage();
//        n.setArticleCount(2);
//
//        Articles articles = new Articles();
//        articles.setArticles(list);
//        n.setArticles(articles);
//        n.setCreateTime(123234);
//        n.setFromUserName("fromUserName");
//        n.setMsgType("news");
//        n.setToUserName("toUserName");

        // XStreamUtil.setXstream(XStreamUtil.createXstream(true));
        // XStream xstream = new XStream();
        // xstream.alias("xml", n.getClass());
        // xstream.alias("item", new Article().getClass());
        // System.out.println(xstream.toXML(n));
        // XStream xstream1 = XStreamUtil.createXstream(true);
        // xstream1.alias("xml", n.getClass());
        // xstream1.alias("item", new Article().getClass());
       // System.out.println(XStreamUtil.toXML(n, true));
        // XStreamUtil.getXstream().alias("xml", n.getClass());
        // XStreamUtil.getXstream().alias("item", a1.getClass());
    	 String secret64 = "MjhkYmE2MmFiMmM4M2Y3ZmVkYjNiNWQ5ZWRhYmEzMGI.";
    	 
        try {
        	String sss = new String(Base64Ext.decode(secret64),"UTF-8");
        	System.out.println(sss);
			String s = getAccessToken("wx36c0752699ec541c",sss);
			String json = getMaterials(s);
			System.out.println(json);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

    }
}
