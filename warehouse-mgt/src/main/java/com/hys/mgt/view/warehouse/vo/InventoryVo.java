package com.hys.mgt.view.warehouse.vo;

import java.io.Serializable;
/**
 * 库存表
 * @author coonor
 *
 */
public class InventoryVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer productId;//商品id
	private String warehouseCode;//仓库编码
	private Integer qty;//库存
	private String maturityDate;//到期日期
	private String inWarehouseDate;//入库日期
	private String batchNo;//批次号
	private String createTime;//创建时间
	private Integer creator;//创建人
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
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public String getInWarehouseDate() {
		return inWarehouseDate;
	}
	public void setInWarehouseDate(String inWarehouseDate) {
		this.inWarehouseDate = inWarehouseDate;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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
		return "InventoryVo [id=" + id + ", productId=" + productId
				+ ", warehouseCode=" + warehouseCode + ", qty=" + qty
				+ ", maturityDate=" + maturityDate + ", inWarehouseDate="
				+ inWarehouseDate + ", batchNo=" + batchNo + ", createTime="
				+ createTime + ", creator=" + creator + "]";
	}
	
}
