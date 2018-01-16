package com.hys.mgt.view.product.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.product.vo.ProductSpecificationsVo;
import com.hys.model.product.ProductSpecifications;

public class ProductSpecificationsConverter {
	
	public static ProductSpecifications convert2Do(ProductSpecificationsVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		ProductSpecifications d = new ProductSpecifications();
		d.setCreateTime(vo.getCreateTime()!=null?vo.getCreateTime().trim():"");
		d.setCreator(vo.getCreator());
		d.setId(vo.getId());
		d.setName(vo.getName()!=null?vo.getName().trim():"");
		d.setRemark(vo.getRemark()!=null?vo.getRemark().trim():"");
		d.setStatus(vo.getStatus());
		d.setParentId(vo.getParentId());
		return d;
	}
	
	public static ProductSpecificationsVo convert2Vo(ProductSpecifications d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		ProductSpecificationsVo v = new ProductSpecificationsVo();
		
		v.setCreateTime(d.getCreateTime()!=null?d.getCreateTime().trim():"");
		v.setCreator(d.getCreator());
		v.setId(d.getId());
		v.setName(d.getName()!=null?d.getName().trim():"");
		v.setStatus(d.getStatus());
		v.setParentId(d.getParentId());
		v.setRemark(d.getRemark()!=null?d.getRemark().trim():"");
		return v;
	}
}
