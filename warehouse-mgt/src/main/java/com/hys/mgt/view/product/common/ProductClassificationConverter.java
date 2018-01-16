package com.hys.mgt.view.product.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.product.vo.ProductClassificationVo;
import com.hys.model.product.ProductClassification;

public class ProductClassificationConverter {
	
	public static ProductClassification convert2Do(ProductClassificationVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		ProductClassification d = new ProductClassification();
		d.setCreateTime(vo.getCreateTime()!=null?vo.getCreateTime().trim():"");
		d.setCreator(vo.getCreator());
		d.setId(vo.getId());
		d.setName(vo.getName()!=null?vo.getName().trim():"");
		d.setRemark(vo.getRemark()!=null?vo.getRemark().trim():"");
		d.setStatus(vo.getStatus());
		d.setParentId(vo.getParentId());
		return d;
	}
	
	public static ProductClassificationVo convert2Vo(ProductClassification d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		ProductClassificationVo v = new ProductClassificationVo();
		
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
