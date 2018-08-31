package com.hys.mgt.view.comment.controller;

import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hys.commons.crypto.Base64Ext;
import com.hys.commons.json.JsonConverter;
import com.hys.commons.otherapi.wxapi.WeiXinApiUtil;
import com.hys.commons.otherapi.wxapi.bean.Menu;
import com.hys.commons.otherapi.wxapi.bean.Message;
import com.hys.commons.otherapi.wxapi.bean.resp.Article;
import com.hys.commons.otherapi.wxapi.bean.resp.TextMessage;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.constants.WebConstants;
import com.hys.mgt.view.user.component.ISysUserViewComp;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/")
public class WxMsgInfo {
	
	
	private static String token = "weixin";
	
	@Autowired
	private ISysUserViewComp userViewComp;
	
	@RequestMapping(value = "/sendmsg", method = RequestMethod.GET)
	public void sendmsgGet(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
 
 
 
//		Enumeration<String> pNames = request.getParameterNames();
//		while (pNames.hasMoreElements()) {
//			String name = (String) pNames.nextElement();
//			String value = request.getParameter(name);
//			// out.print(name + "=" + value);
// 
//			String log = "name =" + name + "     value =" + value;
//			System.out.println(log);
//		}
// 
		String signature = request.getParameter("signature");/// 微信加密签名
		String timestamp = request.getParameter("timestamp");/// 时间戳
		String nonce = request.getParameter("nonce"); /// 随机数
		String echostr = request.getParameter("echostr"); // 随机字符串
		
		String userId = request.getParameter("userId");/// 微信加密签名
		
		SysUserVo user =  userViewComp.queryUserById(userId);
		if(LogicUtil.isNotNull(user)) {
			String appId = user.getAppId();
	     	String secret64 = user.getSecret();
	     	if(LogicUtil.isNotNullAndEmpty(appId) && LogicUtil.isNotNullAndEmpty(secret64)) {
	     		String secret = new String(Base64Ext.decode(secret64),"UTF-8");
				String accessToken = WeiXinApiUtil.getAccessToken(appId,secret);
				if(LogicUtil.isNotNullAndEmpty(accessToken)) {
					session.setAttribute("c_appId", appId);
					session.setAttribute("c_secret", secret);
					session.setAttribute("c_accessToken", accessToken);
					
					//创建菜单
					createMenu(accessToken);
					
				}
	     	}
		}
		PrintWriter out = response.getWriter();
 
		if (checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
 
	}
	
	private static void createMenu(String accessToken){
		
		 // 菜单示列
        Menu m = new Menu();
        m.setName("附件门店");
        m.setType("click");
        m.setKey("V1001");

        Menu m1 = new Menu();
        m1.setName("包裹查询");
        m1.setType("click");
        m1.setKey("V1002");

        Menu m2 = new Menu();
        m2.setName("测试中");
        m2.setType("click");
        m2.setKey("V1003");

        Menu m3 = new Menu();
        m3.setName("敬请期待");
        m3.setType("click");
        m3.setKey("V1003_1");

        Menu m4 = new Menu();
        m4.setName("绑定身份");
        m4.setType("click");
        m4.setKey("V1003_2");

        Menu m5 = new Menu();
        m5.setName("测试中");
        m5.setSubButton(new Menu[] { m2, m3, m4 });

        Menu[] m6 = new Menu[] { m, m1, m5 };
        Map map = new HashMap<String, Menu[]>();
        map.put("button", m6);

        String jsonStr = JsonConverter.format(map);
		
		WeiXinApiUtil.createMenu(accessToken, jsonStr);
	}
	
	
	@RequestMapping(value = "/sendmsg", method = RequestMethod.POST)
	public void sendmsgPost(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		String userId = request.getParameter("userId");/// 
		
		SysUserVo user =  userViewComp.queryUserById(userId);
		if(LogicUtil.isNotNull(user)) {
			String appId = user.getAppId();
	     	String secret64 = user.getSecret();
	     	if(LogicUtil.isNotNullAndEmpty(appId) && LogicUtil.isNotNullAndEmpty(secret64)) {
	     		String secret = new String(Base64Ext.decode(secret64),"UTF-8");
				String accessToken = WeiXinApiUtil.getAccessToken(appId,secret);
				if(LogicUtil.isNotNullAndEmpty(accessToken)) {
					session.setAttribute("c_appId", appId);
					session.setAttribute("c_secret", secret);
					session.setAttribute("c_accessToken", accessToken);
					
//					//创建菜单
//					createMenu(accessToken);
					
				}
	     	}
		}
		
		try (InputStream in = request.getInputStream();) {
            Message message = WeiXinApiUtil.paseWXMessage(in);

            String msgType = message.getMsgType();

            switch (msgType) {
            case WebConstants.MSGTYPE_TEXT:
            	System.out.println("用户主动发送文本");
               // textSender.doSend(message, response);
                break;
            case WebConstants.MSGTYPE_LOCATION:
                //用户主动发送地理位置;
               // locationSender.doSend(message, response);
            	System.out.println("用户主动发送地理位置");
                break;
            case WebConstants.MSGTYPE_EVEN:
                if (WebConstants.EVEN_LOCATION.equals(message.getEvent())) {// 发送地理位置
                   // autoLocationSender.doSend(message, response);
                    System.out.println("发送地理位置");
                } else if (WebConstants.EVEN_CLICK.equals(message.getEvent())) {// 菜单事件
                    System.out.println("菜单事件");
                    String  accessToken  = (String) session.getAttribute("c_accessToken");
                    String muneKey = message.getEventKey();
                    System.out.println("key:"+muneKey);
                    if("V1002".equals(muneKey)) {
                    //被动发送信息
//                    TextMessage msg = new TextMessage();
//                    msg.setCreateTime(new Date().getTime());
//                    msg.setFromUserName(message.getToUserName());
//                    msg.setMsgType(WebConstants.MSGTYPE_TEXT);
//                    msg.setToUserName(message.getFromUserName());
//                    msg.setContent("我来了哈哈哈哈。。。");
//                    String content = WeiXinApiUtil.responseMsgXml(msg);
//                    System.out.println(content);
//                   // WeiXinApiUtil.responseCustomText(message.getFromUserName(), content, accessToken);
//                    
//                    ServletOutputStream sos = response.getOutputStream();
//                    sos.write(content.getBytes("UTF8"));
//                    response.setHeader("Content-Type", "text/html;charset=UTF8");
//                    response.setHeader("Connection", "close");
//                    response.setHeader("Content-Length", String.valueOf(content.getBytes("UTF8").length));
//                    sos.close();
                    //客服接口发消息
	                    WeiXinApiUtil.responseCustomText(message.getFromUserName(), "是我，别开枪", accessToken);
	                    //WeiXinApiUtil.responseCustomImag(message.getFromUserName(), "Hr77pr3DvzCdP6eHLNa9QM0Xm1zcv-QmKWZkveJVMPo", accessToken);
	                    
	                    Article a1 =new Article();
	                    a1.setDescription("说什么好嘞");
	                    a1.setPicurl("http://mmbiz.qpic.cn/mmbiz_jpg/l0waw4uknPbYncvibUfxjicyDa7iaZ1lsia0BXYltPiatnMkZURWILiaMdbF2ZZsgNdSjJrtpG95PeObYLkQRxtiah0kg/0?wx_fmt=jpeg");
	                    a1.setTitle("【晒图有礼】晒出你的美");
	                    a1.setUrl("http://mp.weixin.qq.com/s?__biz=MzI2MTE5OTI3Mw==&mid=100000042&idx=1&sn=48dd90b80b8a5fa963a8e6017dade2d8&chksm=6a5f45ec5d28ccfa33c0bfdf4371f05e07fab4b6147e4044dda60b5b90ea29f829c3a6aafcbc#rd");
	                    Article[] articles = new Article[1];
	                    articles[0] = a1;
	                    WeiXinApiUtil.responseCustomNews(accessToken, message.getFromUserName(), articles);
                    }
                } else if (WebConstants.EVEN_SUBSCRIBE.equals(message.getEvent())) {// 用户关注事件
                	System.out.println("用户关注事件");
                } else{
                    System.out.println("其他事件");
                }
                break;
            case WebConstants.MSGTYPE_IMAGE:
                // 用户发送图片
               System.out.println("用户发送图片"); 
                break;
            case WebConstants.MSGTYPE_VOICE:
                // 用户发送语音
                System.out.println("用户发送语音");
                break;
            default:
                break;
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
	}
	
	/**
	 * 校验签名
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[] { token, timestamp, nonce };
		// 将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将三个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		content = null;
		// 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}
 
	/**
	 * 将字节数组转换为十六进制字符串
	 * 
	 * @param byteArray
	 * @return
	 */
	private static String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
 
	/**
	 * 将字节转换为十六进制字符串
	 * 
	 * @param mByte
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];
 
		String s = new String(tempArr);
		return s;
	}
	
	public static void main(String[] args) {
		String appId = "wx36c0752699ec541c";
		String secret = "28dba62ab2c83f7fedb3b5d9edaba30b";
		//String accessToken = WeiXinApiUtil.getAccessToken(appId,secret);
		//System.out.println(accessToken);
		
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好,我是小q,请回复数字选择服务:\n");

		buffer.append("1 天气预报\n");

		buffer.append("2 公交查询\n");

		buffer.append("3 周边搜索\n");

		buffer.append("4 歌曲点播\n");

		buffer.append("5 经典游戏\n");
		
		String accessToken = "13_fAMRq4MfRygs0Eo-IgMiXzv-4D9mTWKLszZ97XNRd_9J5goHhtX7gQSQxyY_G1cKjXHGXcDDRR6Lo-J-68-YAzTKBal78PDa0a_pUr9AjNLjAuG5IWEnhoNYMSyeMPDVL8LjRxQ5X6aMnLuPTWDaACAORY";
		
		//WeiXinApiUtil.responseCustomText("oqfNhwiNujE2FErngqdAAAMs-fuc", "是我，别开枪", "13_Fgd4CO5SFkdtzk5fWEGJKE0mgjmCzU7MXaXqDwJQssMpb-Afzh_ikA73DZurnp7B3UhFYtaCAM8G9yrupWr2w4Z2ViHCQLE6G7J_3tjfkuWjC13tRq9ckH0W9MMddYNWJe7f1Z0E7JEAesTNCGQfAJAUYI");
		//WeiXinApiUtil.responseCustomText("oqfNhwgVdvMSo71yRmxfZRZiMe40", buffer.toString(), "13_MPM9rL9Tq9BEYvd4crwwttI4uteMzC7KMxHxj2DfDnvT2lWGBWwVXgtFSse78Q7vYu523PDE2zJu3NB9bXb8ryx7nNdMBa1-nFbfp6TK7zZS7AOkdJLbbK4OWKm3-SFoabXvWVy-B6ZpBYfFIREaABALUH");
	
		Article a1 =new Article();
        a1.setDescription("说什么好嘞");
        a1.setPicurl("http://mmbiz.qpic.cn/mmbiz_jpg/l0waw4uknPbYncvibUfxjicyDa7iaZ1lsia0BXYltPiatnMkZURWILiaMdbF2ZZsgNdSjJrtpG95PeObYLkQRxtiah0kg/0?wx_fmt=jpeg");
        a1.setTitle("【晒图有礼】晒出你的美");
        a1.setUrl("http://mp.weixin.qq.com/s?__biz=MzI2MTE5OTI3Mw==&mid=100000042&idx=1&sn=48dd90b80b8a5fa963a8e6017dade2d8&chksm=6a5f45ec5d28ccfa33c0bfdf4371f05e07fab4b6147e4044dda60b5b90ea29f829c3a6aafcbc#rd");
        Article[] articles = new Article[1];
        articles[0] = a1;
       // WeiXinApiUtil.responseCustomNews(accessToken, "oqfNhwgVdvMSo71yRmxfZRZiMe40", articles);
        
        String touser = "oqfNhwiNujE2FErngqdAAAMs-fuc";
        touser = "oqfNhwm37q9CdWD5cPJA9yl6yMfY";
        //touser = "oqfNhwgVdvMSo71yRmxfZRZiMe40";
        String templateId = "XYglxw6VJOZrIOc3kEGoj_II9tH5Ev80LosTW4CkCvI"; //审核失败模板
        
        String templateUrl = "http://wx.vva.com.cn/wxpicadd?activ=MXxBQ1RJVjIwMTgwNzE5MTU1MDQ0";
        Map<String,Object> miniprogram = new HashMap<String,Object>();
        miniprogram.put("appid", "");
        miniprogram.put("pagepath", "");
        
        Map<String,Object> data = new HashMap<String,Object>();
        Map<String,Object> first = new HashMap<String,Object>();
        first.put("value", "很遗憾，您参与的晒图有礼活动未通过审核！");
        first.put("color", "#FF0000");
        data.put("first", first);
        
        Map<String,Object> keyword1 = new HashMap<String,Object>();
        keyword1.put("value", "未按要求进行晒图，请按要求重新提交。");
        keyword1.put("color", "#000");
        data.put("keyword1", keyword1);
        
        Map<String,Object> keyword2 = new HashMap<String,Object>();
        keyword2.put("value", DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
        keyword2.put("color", "#000");
        data.put("keyword2", keyword2);
        
        Map<String,Object> remark = new HashMap<String,Object>();
        remark.put("value", "点击详情重新提交");
        remark.put("color", "#FF0000");
        data.put("remark", remark);
        
        
        //WeiXinApiUtil.sendTemplateMessage(accessToken, touser,templateId, templateUrl, miniprogram, data);
        
        
        templateId = "wj8zA-Mmk0HLD9Mt9nhbdQtP3IC8h23hZc1oGR1N6UY"; //成功模板
        
        String templateUrl1 = "";
        Map<String,Object> miniprogram1 = new HashMap<String,Object>();
        miniprogram1.put("appid", "");
        miniprogram1.put("pagepath", "");
        Map<String,Object> data1 = new HashMap<String,Object>();
        Map<String,Object> first1 = new HashMap<String,Object>();
        first1.put("value", "您参与的晒图有礼活动已经通过审核，请到支付账号中查看领取！");
        first1.put("color", "#FF0000");
        data1.put("first", first1);
        
        Map<String,Object> keyword11 = new HashMap<String,Object>();
        keyword11.put("value", "审核通过");
        keyword11.put("color", "#000");
        data1.put("keyword1", keyword11);
        
        Map<String,Object> keyword21 = new HashMap<String,Object>();
        keyword21.put("value", DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
        keyword21.put("color", "#000");
        data1.put("keyword2", keyword21);
        
        Map<String,Object> remark1 = new HashMap<String,Object>();
        remark1.put("value", "  谢谢您的参与！");
        remark1.put("color", "#FF0000");
        data1.put("remark", remark1);
	
        WeiXinApiUtil.sendTemplateMessage(accessToken, touser,templateId, templateUrl1, miniprogram1, data1);
	}
}
