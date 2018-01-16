package com.hys.mgt.view.warehouse.vo;

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
	private String code;//商品条码
	private String name;//商品名称  用于查询
	private String maturityDate; //到期日期
	private String warehouseCode;//仓库编码
	private Integer beforeInventory; //变更前库存
	private Integer afterInventory;  //变更后库存
	private Integer changeInventory;// 变更数量
	private Integer recordType;//  记录类型  10 采购入库  11 退件入库  20 销售出库  30 盘点 
	private Integer operator;// 操作人
	private String operateTime;//操作时间
	private String operateTimeStart; //用于查询
	private String operateTimeEnd;//用于查询
	private String bacthNo;//批次号
	private String remark;
	private String createTime;//创建时间
	private Integer creator;//创建人
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getBacthNo() {
		return bacthNo;
	}
	public void setBacthNo(String bacthNo) {
		this.bacthNo = bacthNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	@Override
	public String toString() {
		return "InoutRecordVo [id=" + id + ", productId=" + productId
				+ ", sku=" + sku + ", code=" + code + ", name=" + name
				+ ", maturityDate=" + maturityDate + ", warehouseCode="
				+ warehouseCode + ", beforeInventory=" + beforeInventory
				+ ", afterInventory=" + afterInventory + ", changeInventory="
				+ changeInventory + ", recordType=" + recordType
				+ ", operator=" + operator + ", operateTime=" + operateTime
				+ ", operateTimeStart=" + operateTimeStart
				+ ", operateTimeEnd=" + operateTimeEnd + ", bacthNo=" + bacthNo
				+ ", remark=" + remark + ", createTime=" + createTime
				+ ", creator=" + creator + ", pageNum=" + pageNum
				+ ", numPerPage=" + numPerPage + "]";
	}
}
