package com.hys.dal.select.conenum;
/**
 * 商品包装类型枚举
 * @author coonor
 *
 */
public enum EnumProductPackType {
	POLIPING("玻璃瓶",10),
	SULIAOPING("塑料瓶",20),
	ZHIHE("纸盒",30), 
	SULIAODAI("塑料纸",40),
	OTHER("其他",99);
	
	private String name;
	private Integer value;
	
	private EnumProductPackType(String name,Integer value) {
		this.value = value;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getValues() {
		return value;
	}

	public void setValues(Integer value) {
		this.value = value;
	}
}
