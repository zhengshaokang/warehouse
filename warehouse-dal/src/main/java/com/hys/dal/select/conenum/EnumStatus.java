package com.hys.dal.select.conenum;

/**
 * 商品状态枚举
 * @author coonor
 *
 */
public enum EnumStatus {
	ONLINE_SHELF("启用",0), 
	OFFLINE_SEHLF("禁用",1);
	
	private String name;
	private Integer status;
	
	private EnumStatus(String name,Integer status) {
		this.status = status;
		this.name = name;
	}

	public String getName(int status) {
		for (EnumStatus es : EnumStatus.values()) {
			if(es.status == status) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
