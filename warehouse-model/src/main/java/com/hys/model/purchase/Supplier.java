package com.hys.model.purchase;

import java.io.Serializable;
/**
 * 供应商
 * @author coonor
 *
 */
public class Supplier implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;//供应商
	private String address;//联系地址
	private String telephone;//；联系电话
	private String contacts;//联系人
	private String remark;//备注
	private String createTime;//创建时间
	private Integer creator;//创建人
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
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
		return "Supplier [id=" + id + ", name=" + name + ", address=" + address
				+ ", telephone=" + telephone + ", contacts=" + contacts
				+ ", remark=" + remark + ", createTime=" + createTime
				+ ", creator=" + creator + "]";
	}
	
}
