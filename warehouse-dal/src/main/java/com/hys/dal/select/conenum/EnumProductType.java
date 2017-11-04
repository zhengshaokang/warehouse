package com.hys.dal.select.conenum;
/**
 * 商品类型枚举
 * @author coonor
 *
 */
public enum EnumProductType {
	SOLID("固体",1),
	LIQUID("液体",2),
	OTHER("其他",99);
	
	private String name;
	private Integer value;
	private EnumProductType(String name, Integer value) {
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
