package com.hys.dal.select.conenum;

/**
 * 商品单位枚举
 * @author coonor
 *
 */
public enum EnumProductUnit {
	BOTTLE("瓶",1), 
	BOX("盒",2), 
	BRANCH("支",3),
	FILM("片",4),
	BAG("包",5),
	OTHER("其他",99);

	private String name;
	private Integer value;
	private EnumProductUnit(String name, Integer value) {
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
