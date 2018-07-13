package com.hys.model.comment;

import java.io.Serializable;

public class Shop implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer userId;// 管理用户id
	private String name;//店铺名称
	private Integer department;//类目
	private String url;//店铺链接
	private Integer platform;//平台:1天猫或淘宝，2、京东，3、其他
	private Integer status;//状态 1有效 0冻结
	private String createTime;//创建时间
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDepartment() {
		return department;
	}
	public void setDepartment(Integer department) {
		this.department = department;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPlatform() {
		return platform;
	}
	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Shop [id=" + id + ", userId=" + userId + ", name=" + name
				+ ", department=" + department + ", url=" + url + ", platform="
				+ platform + ", status=" + status + ", createTime="
				+ createTime + "]";
	}
	

}
