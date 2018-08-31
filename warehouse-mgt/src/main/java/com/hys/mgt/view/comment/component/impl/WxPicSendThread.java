package com.hys.mgt.view.comment.component.impl;

import java.util.Map;

import com.hys.commons.otherapi.wxapi.WeiXinApiUtil;

public class WxPicSendThread implements Runnable {

	private String openid;
	private String accessToken;
	private String msg;
	private String templateId;
	private String templateUrl;
	private Map<String,Object> miniprogram;
	private Map<String,Object> data;



	public WxPicSendThread(String openid, String accessToken, String msg,
			String templateId, String templateUrl,
			Map<String, Object> miniprogram, Map<String, Object> data) {
		super();
		this.openid = openid;
		this.accessToken = accessToken;
		this.msg = msg;
		this.templateId = templateId;
		this.templateUrl = templateUrl;
		this.miniprogram = miniprogram;
		this.data = data;
	}


	@Override
	public void run() {
		//System.out.println(data.toString());
		//WeiXinApiUtil.responseCustomText("oqfNhwiNujE2FErngqdAAAMs-fuc", "线程直接发送审核消息测试", "13_Fgd4CO5SFkdtzk5fWEGJKE0mgjmCzU7MXaXqDwJQssMpb-Afzh_ikA73DZurnp7B3UhFYtaCAM8G9yrupWr2w4Z2ViHCQLE6G7J_3tjfkuWjC13tRq9ckH0W9MMddYNWJe7f1Z0E7JEAesTNCGQfAJAUYI");
		//发送模板消息
		WeiXinApiUtil.sendTemplateMessage(accessToken, openid,templateId, templateUrl, miniprogram, data);
	}


	public String getOpenid() {
		return openid;
	}


	public void setOpenid(String openid) {
		this.openid = openid;
	}


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getTemplateId() {
		return templateId;
	}


	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}


	public String getTemplateUrl() {
		return templateUrl;
	}


	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}


	public Map<String, Object> getMiniprogram() {
		return miniprogram;
	}


	public void setMiniprogram(Map<String, Object> miniprogram) {
		this.miniprogram = miniprogram;
	}


	public Map<String, Object> getData() {
		return data;
	}


	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "WxPicSendThread [openid=" + openid + ", accessToken="
				+ accessToken + ", msg=" + msg + ", templateId=" + templateId
				+ ", templateUrl=" + templateUrl + ", miniprogram="
				+ miniprogram + ", data=" + data + "]";
	}



}
