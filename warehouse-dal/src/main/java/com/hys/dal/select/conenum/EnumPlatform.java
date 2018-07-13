package com.hys.dal.select.conenum;

/**
 * @author coonor
 *
 */
public enum EnumPlatform {
	//1天猫或淘宝，2、京东，3、其他
	TIANMAO("天猫或淘宝",10), 
	JINGDONG("京东",20),
	OTHER("其他",99);
	
	private String name;
	private Integer value;
	
	private EnumPlatform(String name,Integer value) {
		this.value = value;
		this.name = name;
	}

	public String getName(int value) {
		for (EnumPlatform es : EnumPlatform.values()) {
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
