package com.hys.mgt.view.inout.vo;

import java.io.Serializable;

import com.hys.mgt.view.common.constants.WebConstants;

/**
 * 出入库记录
 * @author coonor
 *
 */
public class InoutRecordVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer productId; //商品id
	private String sku;//用于查询
	private String name;//商品名称  用于查询
	private String productionDate;//生产日期
	private Integer inoutType; //出入库类型   0 入库   1出库    3盘点
	private Integer beforeTotal;//变更前总库存
	private Integer afterTotal;//变更后总库存
	private Integer beforeInventory; //变更前库存
	private Integer afterInventory;  //变更后库存
	private Integer changeInventory;// 变更数量
	private Integer recordType;//  记录类型  10 质检入库  11 退件入库  20 订单出库  30 盘点 
	private Integer operator;// 操作人
	private String operateTime;//操作时间
	private String operateTimeStart; //用于查询
	private String operateTimeEnd;//用于查询
	
	private int pageNum = 1;
    private int numPerPage = WebConstants.PAGESIZE;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getInoutType() {
		return inoutType;
	}
	public void setInoutType(Integer inoutType) {
		this.inoutType = inoutType;
	}
	public Integer getBeforeTotal() {
		return beforeTotal;
	}
	public void setBeforeTotal(Integer beforeTotal) {
		this.beforeTotal = beforeTotal;
	}
	public Integer getAfterTotal() {
		return afterTotal;
	}
	public void setAfterTotal(Integer afterTotal) {
		this.afterTotal = afterTotal;
	}
	public Integer getBeforeInventory() {
		return beforeInventory;
	}
	public void setBeforeInventory(Integer beforeInventory) {
		this.beforeInventory = beforeInventory;
	}
	public Integer getAfterInventory() {
		return afterInventory;
	}
	public void setAfterInventory(Integer afterInventory) {
		this.afterInventory = afterInventory;
	}
	public Integer getChangeInventory() {
		return changeInventory;
	}
	public void setChangeInventory(Integer changeInventory) {
		this.changeInventory = changeInventory;
	}
	public Integer getRecordType() {
		return recordType;
	}
	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getOperateTimeStart() {
		return operateTimeStart;
	}
	public void setOperateTimeStart(String operateTimeStart) {
		this.operateTimeStart = operateTimeStart;
	}
	public String getOperateTimeEnd() {
		return operateTimeEnd;
	}
	public void setOperateTimeEnd(String operateTimeEnd) {
		this.operateTimeEnd = operateTimeEnd;
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
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	@Override
	public String toString() {
		return "InoutRecordVo [id=" + id + ", productId=" + productId
				+ ", sku=" + sku + ", name=" + name + ", productionDate="
				+ productionDate + ", inoutType=" + inoutType
				+ ", beforeTotal=" + beforeTotal + ", afterTotal=" + afterTotal
				+ ", beforeInventory=" + beforeInventory + ", afterInventory="
				+ afterInventory + ", changeInventory=" + changeInventory
				+ ", recordType=" + recordType + ", operator=" + operator
				+ ", operateTime=" + operateTime + ", operateTimeStart="
				+ operateTimeStart + ", operateTimeEnd=" + operateTimeEnd
				+ ", pageNum=" + pageNum + ", numPerPage=" + numPerPage + "]";
	}
	
}
