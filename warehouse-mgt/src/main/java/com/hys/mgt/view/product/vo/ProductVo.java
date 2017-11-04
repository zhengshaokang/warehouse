package com.hys.mgt.view.product.vo;

import java.io.Serializable;
import java.util.List;

import com.hys.mgt.view.common.constants.WebConstants;

/**
 * 商品
 * @author coonor
 *
 */
public class ProductVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;//商品名称
	private String sku;//商品唯一编码
	private String description;  //商品描述
	private String productionDate; //生产日期
	private String maturityDate;//到期日期
	private Integer effectiveDay;//有效天数
	private Double price;//价格
	private Integer inventoryTotal;//总库存
	private Integer inventoryAvailable;//可用库存
	private Integer inventoryCheck;//盘点库存
	private String checkDate; //盘点日期
	private Integer unit;//单位   1瓶、2盒、3支、4片、5包
	private String picUrl;//图片地址
	private Integer packType;//包装类型 10 玻璃瓶，20塑料瓶，30纸盒，40塑料纸
	private Integer type;//商品类型 1 固体，2液体
	private Integer status;//商品状态  0上架，1下架
	private Integer creator;//创建人
	private String createTime;//创建时间
	private Integer updator;//更新人
	private String updateTime;//更新时间
	private String sysRemark;//系统备注
	
	private int pageNum = 1;
    private int numPerPage = WebConstants.PAGESIZE;
	
	private List<ProductAttributeVo> productAttribute;
	
	
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
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public Integer getEffectiveDay() {
		return effectiveDay;
	}
	public void setEffectiveDay(Integer effectiveDay) {
		this.effectiveDay = effectiveDay;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Integer getPackType() {
		return packType;
	}
	public void setPackType(Integer packType) {
		this.packType = packType;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getUpdator() {
		return updator;
	}
	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getSysRemark() {
		return sysRemark;
	}
	public void setSysRemark(String sysRemark) {
		this.sysRemark = sysRemark;
	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public List<ProductAttributeVo> getProductAttribute() {
		return productAttribute;
	}
	public void setProductAttribute(List<ProductAttributeVo> productAttribute) {
		this.productAttribute = productAttribute;
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
	public Integer getInventoryTotal() {
		return inventoryTotal;
	}
	public void setInventoryTotal(Integer inventoryTotal) {
		this.inventoryTotal = inventoryTotal;
	}
	public Integer getInventoryAvailable() {
		return inventoryAvailable;
	}
	public void setInventoryAvailable(Integer inventoryAvailable) {
		this.inventoryAvailable = inventoryAvailable;
	}
	public Integer getInventoryCheck() {
		return inventoryCheck;
	}
	public void setInventoryCheck(Integer inventoryCheck) {
		this.inventoryCheck = inventoryCheck;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	@Override
	public String toString() {
		return "ProductVo [id=" + id + ", name=" + name + ", sku=" + sku
				+ ", description=" + description + ", productionDate="
				+ productionDate + ", maturityDate=" + maturityDate
				+ ", effectiveDay=" + effectiveDay + ", price=" + price
				+ ", inventoryTotal=" + inventoryTotal
				+ ", inventoryAvailable=" + inventoryAvailable
				+ ", inventoryCheck=" + inventoryCheck + ", checkDate="
				+ checkDate + ", unit=" + unit + ", picUrl=" + picUrl
				+ ", packType=" + packType + ", type=" + type + ", status="
				+ status + ", creator=" + creator + ", createTime="
				+ createTime + ", updator=" + updator + ", updateTime="
				+ updateTime + ", sysRemark=" + sysRemark + ", pageNum="
				+ pageNum + ", numPerPage=" + numPerPage
				+ ", productAttribute=" + productAttribute + "]";
	}
}
