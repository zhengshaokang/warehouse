package com.hys.dal.select.conenum;

/**
 * @author coonor
 *
 */
public enum EnumOrderPayStatus {
	//订单状态:10未支付,20已支付未发货,30已发货,40取消交易,50交易成功
	NOCHECK("待审核",10), 
	YESPAY("已返现",20),
	NOPASS("不通过",30),
	PROHIBIT("禁止",40);
	
	private String name;
	private Integer value;
	
	private EnumOrderPayStatus(String name,Integer value) {
		this.value = value;
		this.name = name;
	}

	public String getName(int value) {
		for (EnumOrderPayStatus es : EnumOrderPayStatus.values()) {
			if(es.value == value) {
				return es.name;
			}
		}
		return null;
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
