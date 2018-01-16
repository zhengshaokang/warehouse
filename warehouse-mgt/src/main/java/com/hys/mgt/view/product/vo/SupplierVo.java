package com.hys.mgt.view.product.vo;

import java.io.Serializable;

import com.hys.mgt.view.common.constants.WebConstants;
/**
 * 供应商
 * @author coonor
 *
 */
public class SupplierVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;//供应商
	private String address;//联系地址
	private String telephone;//；联系电话
	private String contacts;//联系人
	private String remark;//备注
	private String createTime;//创建时间
	private Integer creator;//创建人
	
	private int pageNum = 1;
    private int numPerPage = WebConstants.PAGESIZE;
	
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
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	@Override
	public String toString() {
		return "SupplierVo [id=" + id + ", name=" + name + ", address="
				+ address + ", telephone=" + telephone + ", contacts="
				+ contacts + ", remark=" + remark + ", createTime="
				+ createTime + ", creator=" + creator + ", pageNum=" + pageNum
				+ ", numPerPage=" + numPerPage + "]";
	}
	
}
