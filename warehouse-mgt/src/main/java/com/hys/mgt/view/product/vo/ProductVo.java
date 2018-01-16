package com.hys.mgt.view.product.vo;

import java.io.Serializable;

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
	private String sku;//商品内部编码
	private String code;//商品国际条码
	private Integer brandId;  //商品品牌
	private String maturityDate;//到期日期
	private Integer specificationId1;//商品规格1
	private Integer specificationId2;//商品规格2
	private Integer classificationId1;//商品分类1
	private Integer classificationId2;//商品分类2
	private Integer unitId;//单位
	private Integer warning;//临期警示值
	private String picUrl;//图片地址
	private Integer status;//商品状态  0上架，1下架
	private Integer creator;//创建人
	private String createTime;//创建时间
	private String sysRemark;//系统备注
	private String remark;//备注
	private Integer supplierId;//供应商
	private Integer updator;
	
	private String picType; //图片格式
	
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
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getMaturityDate() {
		return maturityDate;
	}
	public void setMaturityDate(String maturityDate) {
		this.maturityDate = maturityDate;
	}
	public Integer getSpecificationId1() {
		return specificationId1;
	}
	public void setSpecificationId1(Integer specificationId1) {
		this.specificationId1 = specificationId1;
	}
	public Integer getSpecificationId2() {
		return specificationId2;
	}
	public void setSpecificationId2(Integer specificationId2) {
		this.specificationId2 = specificationId2;
	}
	public Integer getClassificationId1() {
		return classificationId1;
	}
	public void setClassificationId1(Integer classificationId1) {
		this.classificationId1 = classificationId1;
	}
	public Integer getClassificationId2() {
		return classificationId2;
	}
	public void setClassificationId2(Integer classificationId2) {
		this.classificationId2 = classificationId2;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public Integer getWarning() {
		return warning;
	}
	public void setWarning(Integer warning) {
		this.warning = warning;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
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
	public String getSysRemark() {
		return sysRemark;
	}
	public void setSysRemark(String sysRemark) {
		this.sysRemark = sysRemark;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public Integer getUpdator() {
		return updator;
	}
	public void setUpdator(Integer updator) {
		this.updator = updator;
	}
	@Override
	public String toString() {
		return "ProductVo [id=" + id + ", name=" + name + ", sku=" + sku
				+ ", code=" + code + ", brandId=" + brandId + ", maturityDate="
				+ maturityDate + ", specificationId1=" + specificationId1
				+ ", specificationId2=" + specificationId2
				+ ", classificationId1=" + classificationId1
				+ ", classificationId2=" + classificationId2 + ", unitId="
				+ unitId + ", warning=" + warning + ", picUrl=" + picUrl
				+ ", status=" + status + ", creator=" + creator
				+ ", createTime=" + createTime + ", sysRemark=" + sysRemark
				+ ", remark=" + remark + ", supplierId=" + supplierId
				+ ", picType=" + picType + ", pageNum=" + pageNum
				+ ", numPerPage=" + numPerPage + "]";
	}
	
}
