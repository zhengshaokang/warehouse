package com.hys.mgt.view.warehouse.vo;

import java.io.Serializable;

import com.hys.mgt.view.common.constants.WebConstants;
/**
 * 仓库表
 * @author coonor
 *
 */
public class WarehouseVo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name; //仓库名称
	private String code; //仓库编码
	private String address; //仓库地址
	private String remark; //备注
	private String createTime; //创建时间
	private Integer creator; //创建人
	
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
		return "WarehouseVo [id=" + id + ", name=" + name + ", code=" + code
				+ ", address=" + address + ", remark=" + remark
				+ ", createTime=" + createTime + ", creator=" + creator
				+ ", pageNum=" + pageNum + ", numPerPage=" + numPerPage + "]";
	}
	
}
