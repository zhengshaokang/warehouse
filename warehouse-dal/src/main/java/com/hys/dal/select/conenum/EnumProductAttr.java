package com.hys.dal.select.conenum;

public enum EnumProductAttr {
	COLOR("颜色","颜色"), 
	CUBAGE("体积","体积"),
	CAPACITY("容量","容量"), 
	WEIGHT("重量","重量"),
	SIZE("尺码","尺码"), 
	OTHER("其他","其他");
	
	private String name;
	private String value;
	
	private EnumProductAttr(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
