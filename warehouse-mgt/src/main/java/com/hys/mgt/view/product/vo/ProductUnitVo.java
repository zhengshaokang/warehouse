package com.hys.mgt.view.product.vo;

import java.io.Serializable;

import com.hys.mgt.view.common.constants.WebConstants;
/**
 * 商品单位
 * @author coonor
 *
 */
public class ProductUnitVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;//
	private String name;//单位名称
	private Integer status;//状态  0启用1删除
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
		return "ProductUnitVo [id=" + id + ", name=" + name + ", status="
				+ status + ", remark=" + remark + ", createTime=" + createTime
				+ ", creator=" + creator + ", pageNum=" + pageNum
				+ ", numPerPage=" + numPerPage + "]";
	}
	
}
