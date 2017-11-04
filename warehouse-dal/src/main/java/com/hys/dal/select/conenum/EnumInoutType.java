package com.hys.dal.select.conenum;

/**
 * 出入库类型
 * @author coonor
 *
 */
public enum EnumInoutType {
	IN("入库",0), 
	OUT("出库",1), 
	CHECK("盘点",2);

	private String name;
	private Integer value;
	private EnumInoutType(String name, Integer value) {
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
