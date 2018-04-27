package com.hys.mgt.view.product.vo;

import java.io.Serializable;

public class ProductOutVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer productId;//商品id
	private String warehouseCode;//仓库编码
	private String inoutRecordType;//出库类型
	private String outWarehouseDate;//出库日期
	private Integer qty;//库存
	private String batchNo;//批次号
	private String remark;
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
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInoutRecordType() {
		return inoutRecordType;
	}
	public void setInoutRecordType(String inoutRecordType) {
		this.inoutRecordType = inoutRecordType;
	}
	public String getOutWarehouseDate() {
		return outWarehouseDate;
	}
	public void setOutWarehouseDate(String outWarehouseDate) {
		this.outWarehouseDate = outWarehouseDate;
	}
	@Override
	public String toString() {
		return "ProductOutVo [id=" + id + ", productId=" + productId
				+ ", warehouseCode=" + warehouseCode + ", inoutRecordType="
				+ inoutRecordType + ", outWarehouseDate=" + outWarehouseDate
				+ ", qty=" + qty + ", batchNo=" + batchNo + ", remark="
				+ remark + "]";
	}
}
