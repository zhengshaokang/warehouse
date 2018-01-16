package com.hys.mgt.view.product.vo;

import java.io.Serializable;
/**
 * 商品进价
 * @author coonor
 *
 */
public class ProductInPriceVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer productId;// 商品id
	private Double price;// 进货价格
	private String inDate;//进货日期
	private Integer inQty;//进货数量
	private String createTime;//创建时间
	private Integer creator;//创建人
	
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public Integer getInQty() {
		return inQty;
	}
	public void setInQty(Integer inQty) {
		this.inQty = inQty;
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
		return "ProductInPriceVo [id=" + id + ", productId=" + productId
				+ ", price=" + price + ", inDate=" + inDate + ", inQty="
				+ inQty + ", createTime=" + createTime + ", creator=" + creator
				+ "]";
	}
}
