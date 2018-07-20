package com.hys.mgt.view.comment.vo;

import java.io.Serializable;

import com.hys.mgt.view.common.constants.WebConstants;

public class WxPicVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer userId;// 管理用户id
	private String orderNo;//订单号
	private String uploadTime;//上传时间
	private String uploadIp;//上传IP
	private String picUrl;//图片地址
	private Integer payStatus;//审核状态
	private int pageNum = 1;
    private int numPerPage = WebConstants.PAGESIZE;
	
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
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getUploadIp() {
		return uploadIp;
	}
	public void setUploadIp(String uploadIp) {
		this.uploadIp = uploadIp;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	@Override
	public String toString() {
		return "WxPicVo [id=" + id + ", userId=" + userId + ", orderNo="
				+ orderNo + ", uploadTime=" + uploadTime + ", uploadIp="
				+ uploadIp + ", picUrl=" + picUrl + ", payStatus=" + payStatus
				+ ", pageNum=" + pageNum + ", numPerPage=" + numPerPage + "]";
	}
 
}
