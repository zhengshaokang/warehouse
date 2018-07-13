package com.hys.dal.select.conenum;

/**
 * @author coonor
 *
 */
public enum EnumSex {
	nan("男",1), 
	Nv("女",2);
	
	private String name;
	private Integer status;
	
	private EnumSex(String name,Integer status) {
		this.status = status;
		this.name = name;
	}

	public String getName(int status) {
		for (EnumSex es : EnumSex.values()) {
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
