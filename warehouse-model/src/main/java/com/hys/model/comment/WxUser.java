package com.hys.model.comment;

import java.io.Serializable;

public class WxUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer userId;// 管理用户id
	private String nickname;//微信用户昵称
	private Integer sex;//微信用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	private String city;//城市
	private String province;//省
	private String country;//国家
	private String language;//微信用户的语言，简体中文为zh_CN
	private String headimgurl;//微信用户头像地址
	private String logintime;//微信用户关注时间
	private String openId;//微信对应公众号的id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getLogintime() {
		return logintime;
	}
	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	@Override
	public String toString() {
		return "WxUser [id=" + id + ", userId=" + userId + ", nickname="
				+ nickname + ", sex=" + sex + ", city=" + city + ", province="
				+ province + ", country=" + country + ", language=" + language
				+ ", headimgurl=" + headimgurl + ", logintime=" + logintime
				+ ", openId=" + openId + "]";
	}
}
