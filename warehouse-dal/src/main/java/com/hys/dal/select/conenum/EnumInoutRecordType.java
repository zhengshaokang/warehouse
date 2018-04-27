package com.hys.dal.select.conenum;

/**
 *记录类型  10 质检入库  11 退件入库  20 订单出库  30 盘点
 * @author coonor
 *
 */
public enum EnumInoutRecordType {
	PRODUCTIN("采购入库",10), 
	RETURNORDERIN("退货入库",11), 
	OTHERIN("其他入库",12), 
	ORDEROUT("销售出库",20),
	PRODUCTOUT("废品出库",21),
	ALLOCATIONOUT("调拨出库",22),
	GIVETOUT("赠送出库",23),
	OTHEROUT("其他出库",24),
	CHECK("盘点",30);

	private String name;
	private Integer value;
	private EnumInoutRecordType(String name, Integer value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
}
