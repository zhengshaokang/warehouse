package com.hys.mgt.view.product.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.product.vo.ProductVo;
import com.hys.model.product.Product;


public class ProductConverter {
	public static Product convert2Do(ProductVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		Product d = new Product();
		d.setCreateTime(vo.getCreateTime()!=null?vo.getCreateTime().trim():"");
		d.setCreator(vo.getCreator());
		d.setId(vo.getId());
		d.setMaturityDate(vo.getMaturityDate()!=null?vo.getMaturityDate().trim():"");
		d.setName(vo.getName()!=null?vo.getName().trim():"");
		d.setPicUrl(vo.getPicUrl()!=null?vo.getPicUrl().trim():"");
		d.setSku(vo.getSku()!=null?vo.getSku().trim():"");
		d.setStatus(vo.getStatus());
		d.setSysRemark(vo.getSysRemark()!=null?vo.getSysRemark().trim():"");
		d.setBrandId(vo.getBrandId());
		d.setClassificationId1(vo.getClassificationId1());
		d.setClassificationId2(vo.getClassificationId2());
		d.setSpecificationId1(vo.getSpecificationId1());
		d.setSpecificationId2(vo.getSpecificationId2());
		d.setCode(vo.getCode()!=null?vo.getCode().trim():"");
		d.setRemark(vo.getRemark()!=null?vo.getRemark().trim():"");
		d.setSupplierId(vo.getSupplierId());
		d.setWarning(vo.getWarning());
		d.setUnitId(vo.getUnitId());
		return d;
	}
	
	public static ProductVo convert2Vo(Product d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		ProductVo v = new ProductVo();
		
		v.setCreateTime(d.getCreateTime()!=null?d.getCreateTime().trim():"");
		v.setCreator(d.getCreator());
		v.setId(d.getId());
		v.setMaturityDate(d.getMaturityDate());
		v.setName(d.getName()!=null?d.getName().trim():"");
		v.setPicUrl(d.getPicUrl()!=null?d.getPicUrl().trim():"");
		v.setSku(d.getSku()!=null?d.getSku().trim():"");
		v.setStatus(d.getStatus());
		v.setSysRemark(d.getSysRemark()!=null?d.getSysRemark().trim():"");
		v.setBrandId(d.getBrandId());
		v.setClassificationId1(d.getClassificationId1());
		v.setClassificationId2(d.getClassificationId2());
		v.setSpecificationId1(d.getSpecificationId1());
		v.setSpecificationId2(d.getSpecificationId2());
		v.setCode(d.getCode()!=null?d.getCode().trim():"");
		v.setRemark(d.getRemark()!=null?d.getRemark().trim():"");
		v.setSupplierId(d.getSupplierId());
		v.setWarning(d.getWarning());
		v.setUnitId(d.getUnitId());
		if(LogicUtil.isNotNullAndEmpty(d.getPicUrl())) {
			String type = d.getPicUrl().substring(d.getPicUrl().indexOf(".")+1,d.getPicUrl().length());
			v.setPicType(type);
		} else {
			v.setPicType("jpg");
		}
		
		return v;
	}
}
