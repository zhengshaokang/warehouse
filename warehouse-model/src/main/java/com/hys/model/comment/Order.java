package com.hys.model.comment;

import java.io.Serializable;

public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer userId;// 管理用户id
	private Integer shopId;// 店铺id
	private Integer isJoin;// 是否参与活动，默认1参与,0不参与
	private Integer isPay;//  0未返现1已返现，默认0
	private String orderNo;// 订单号
	private Integer orderStatus;// 订单状态:10未支付,20已支付未发货,30已发货,40取消交易,50交易成功
	private String orderAmount;// 订单实际支付金额
	private String customerName;// 客户名称（旺旺）
	private String customerMobile;// 客户电话
	private String orderTime;// 下单时间
	private String remark;// 备注
	
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
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getIsJoin() {
		return isJoin;
	}
	public void setIsJoin(Integer isJoin) {
		this.isJoin = isJoin;
	}
	public Integer getIsPay() {
		return isPay;
	}
	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", shopId=" + shopId
				+ ", isJoin=" + isJoin + ", isPay=" + isPay + ", orderNo="
				+ orderNo + ", orderStatus=" + orderStatus + ", orderAmount="
				+ orderAmount + ", customerName=" + customerName
				+ ", customerMobile=" + customerMobile + ", orderTime="
				+ orderTime + ", remark=" + remark + "]";
	}
	
}
