package com.hys.model.comment;

import java.io.Serializable;

public class Activ implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;// 活动名称
	private String title;// 活动标题
	private String bgPath;// 背景图路径
	private String suBgPath;// 成功背景图路径
	private String workflowPath;//流程说明图路径
	private String qcardPath;//二维码路径
	private String description;// 活动介绍
	private String startTime;//活动开始时间
	private String endTime;//活动结束时间
	private String wxLink;//活动路径
	private String createTime;//创建时间
	private Integer userId;//创建人 
	private String code;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBgPath() {
		return bgPath;
	}
	public void setBgPath(String bgPath) {
		this.bgPath = bgPath;
	}
	public String getWorkflowPath() {
		return workflowPath;
	}
	public void setWorkflowPath(String workflowPath) {
		this.workflowPath = workflowPath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getWxLink() {
		return wxLink;
	}
	public void setWxLink(String wxLink) {
		this.wxLink = wxLink;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getSuBgPath() {
		return suBgPath;
	}
	public void setSuBgPath(String suBgPath) {
		this.suBgPath = suBgPath;
	}
	public String getQcardPath() {
		return qcardPath;
	}
	public void setQcardPath(String qcardPath) {
		this.qcardPath = qcardPath;
	}
	@Override
	public String toString() {
		return "Activ [id=" + id + ", name=" + name + ", title=" + title
				+ ", bgPath=" + bgPath + ", suBgPath=" + suBgPath
				+ ", workflowPath=" + workflowPath + ", qcardPath=" + qcardPath
				+ ", description=" + description + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", wxLink=" + wxLink
				+ ", createTime=" + createTime + ", userId=" + userId
				+ ", code=" + code + "]";
	}
}
