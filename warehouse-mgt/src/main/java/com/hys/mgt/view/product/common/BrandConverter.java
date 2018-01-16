package com.hys.mgt.view.product.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.product.vo.BrandVo;
import com.hys.model.product.Brand;

public class BrandConverter {
	
	public static Brand convert2Do(BrandVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		Brand d = new Brand();
		d.setCreateTime(vo.getCreateTime() !=null ? vo.getCreateTime().trim():"");
		d.setCreator(vo.getCreator());
		d.setId(vo.getId());
		d.setName(vo.getName() != null ? vo.getName().trim():"");
		d.setRemark(vo.getRemark() !=null ?vo.getRemark().trim():"");
		d.setStatus(vo.getStatus());
		return d;
	}
	
	public static BrandVo convert2Vo(Brand d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		BrandVo v = new BrandVo();
		
		v.setCreateTime(d.getCreateTime() !=null ? d.getCreateTime().trim():"");
		v.setCreator(d.getCreator());
		v.setId(d.getId());
		v.setName(d.getName() !=null?d.getName().trim():"");
		v.setStatus(d.getStatus());
		v.setRemark(d.getRemark()!=null?d.getRemark().trim():"");
		return v;
	}
}
