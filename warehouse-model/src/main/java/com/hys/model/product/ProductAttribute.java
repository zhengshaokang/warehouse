package com.hys.model.product;

import java.io.Serializable;

/**
 * 商品属性
 * @author coonor
 *
 */
public class ProductAttribute implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer productId;//商品id 
	private String name;//属性名称
	private String value;//属性值
	private String remark;//备注
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "ProductAttribute [id=" + id + ", productId=" + productId
				+ ", name=" + name + ", value=" + value + ", remark=" + remark
				+ "]";
	}
	
}
