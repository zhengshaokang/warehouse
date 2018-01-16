package com.hys.mgt.view.product.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.product.vo.ProductUnitVo;
import com.hys.model.product.ProductUnit;

public class ProductUnitConverter {
	
	public static ProductUnit convert2Do(ProductUnitVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		ProductUnit d = new ProductUnit();
		d.setCreateTime(vo.getCreateTime() !=null ? vo.getCreateTime().trim():"");
		d.setCreator(vo.getCreator());
		d.setId(vo.getId());
		d.setName(vo.getName() != null ? vo.getName().trim():"");
		d.setRemark(vo.getRemark() !=null ?vo.getRemark().trim():"");
		d.setStatus(vo.getStatus());
		return d;
	}
	
	public static ProductUnitVo convert2Vo(ProductUnit d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		ProductUnitVo v = new ProductUnitVo();
		
		v.setCreateTime(d.getCreateTime() !=null ? d.getCreateTime().trim():"");
		v.setCreator(d.getCreator());
		v.setId(d.getId());
		v.setName(d.getName() !=null?d.getName().trim():"");
		v.setStatus(d.getStatus());
		v.setRemark(d.getRemark()!=null?d.getRemark().trim():"");
		return v;
	}
}
