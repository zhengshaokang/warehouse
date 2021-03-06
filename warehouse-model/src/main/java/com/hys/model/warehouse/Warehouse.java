package com.hys.model.warehouse;

import java.io.Serializable;
/**
 * 仓库表
 * @author coonor
 *
 */
public class Warehouse implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name; //仓库名称
	private String code; //仓库编码
	private String address; //仓库地址
	private String remark; //备注
	private String createTime; //创建时间
	private Integer creator; //创建人
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", name=" + name + ", code=" + code
				+ ", address=" + address + ", remark=" + remark
				+ ", createTime=" + createTime + ", creator=" + creator + "]";
	}
	
}
