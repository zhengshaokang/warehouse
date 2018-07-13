package com.hys.dal.select.conenum;

/**
 * @author coonor
 *
 */
public enum EnumYesNo {
	YES("否",0), 
	NO("是",1);
	
	private String name;
	private Integer status;
	
	private EnumYesNo(String name,Integer status) {
		this.status = status;
		this.name = name;
	}

	public String getName(int status) {
		for (EnumYesNo es : EnumYesNo.values()) {
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
