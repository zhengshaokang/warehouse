package com.hys.model.product;

import java.io.Serializable;

public class Brand implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;//
	private String name;//品牌名称
	private Integer status;//状态  0启用1删除
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
		return "Brand [id=" + id + ", name=" + name + ", status=" + status
				+ ", remark=" + remark + ", createTime=" + createTime
				+ ", creator=" + creator + "]";
	}
	
}
