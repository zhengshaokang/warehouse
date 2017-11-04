package com.hys.dal.select.conenum;

/**
 * 商品状态枚举
 * @author coonor
 *
 */
public enum EnumProudctStatus {
	ONLINE_SHELF("上架",0), //上架
	OFFLINE_SEHLF("下架",1);//下架
	
	private String name;
	private Integer status;
	
	private EnumProudctStatus(String name,Integer status) {
		this.status = status;
		this.name = name;
	}

	public String getName(int status) {
		for (EnumProudctStatus es : EnumProudctStatus.values()) {
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
