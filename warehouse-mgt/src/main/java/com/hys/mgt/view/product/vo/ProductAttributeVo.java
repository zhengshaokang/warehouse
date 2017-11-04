package com.hys.mgt.view.product.vo;

import java.io.Serializable;

import com.hys.mgt.view.common.constants.WebConstants;

/**
 * 商品属性
 * @author coonor
 *
 */
public class ProductAttributeVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer productId;//商品id 
	private String name;//属性名称
	private String value;//属性值
	private String remark;//备注
	
	private int pageNum = 1;
    private int numPerPage = WebConstants.PAGESIZE;
	
	
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
		return "ProductAttribute [id=" + id + ", productId=" + productId
				+ ", name=" + name + ", value=" + value + ", remark=" + remark
				+ "]";
	}
	
}
