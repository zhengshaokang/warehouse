package com.hys.mgt.view.product.common;

import java.util.ArrayList;
import java.util.List;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.product.vo.ProductAttributeVo;
import com.hys.mgt.view.product.vo.ProductVo;
import com.hys.model.product.Product;
import com.hys.model.product.ProductAttribute;


public class ProductConverter {
	public static Product convert2Do(ProductVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		Product d = new Product();
		d.setInventoryAvailable(vo.getInventoryAvailable());
		d.setInventoryCheck(vo.getInventoryCheck());
		d.setInventoryTotal(vo.getInventoryTotal());
		d.setCheckDate(vo.getCheckDate());
		d.setCreateTime(vo.getCreateTime());
		d.setCreator(vo.getCreator());
		d.setDescription(vo.getDescription());
		d.setEffectiveDay(vo.getEffectiveDay());
		d.setId(vo.getId());
		d.setMaturityDate(vo.getMaturityDate());
		d.setName(vo.getName());
		d.setPackType(vo.getPackType());
		d.setPicUrl(vo.getPicUrl());
		d.setPrice(vo.getPrice());
		d.setProductionDate(vo.getProductionDate());
		d.setSku(vo.getSku());
		d.setStatus(vo.getStatus());
		d.setSysRemark(vo.getSysRemark());
		d.setType(vo.getType());
		d.setUnit(vo.getUnit());
		d.setUpdateTime(vo.getUpdateTime());
		d.setUpdator(vo.getUpdator());
		
		List<ProductAttributeVo> listvo = vo.getProductAttribute();
		List<ProductAttribute> list = new ArrayList<ProductAttribute>();
		if(LogicUtil.isNotNullAndEmpty(listvo)) {
			for (ProductAttributeVo productAttributeVo : listvo) {
				list.add(convert2Do(productAttributeVo));
			}
		}
		d.setProductAttribute(list);
		
		return d;
	}
	
	public static ProductAttribute convert2Do(ProductAttributeVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		ProductAttribute d = new ProductAttribute();
		d.setId(vo.getId());
		d.setName(vo.getName());
		d.setProductId(vo.getProductId());
		d.setRemark(vo.getRemark());
		d.setValue(vo.getValue());
		return d;
	}
	
	public static ProductVo convert2Vo(Product d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		ProductVo v = new ProductVo();
		
		v.setInventoryAvailable(d.getInventoryAvailable());
		v.setInventoryCheck(d.getInventoryCheck());
		v.setInventoryTotal(d.getInventoryTotal());
		v.setCheckDate(d.getCheckDate());
		v.setCreateTime(d.getCreateTime());
		v.setCreator(d.getCreator());
		v.setDescription(d.getDescription());
		v.setEffectiveDay(d.getEffectiveDay());
		v.setId(d.getId());
		v.setMaturityDate(d.getMaturityDate());
		v.setName(d.getName());
		v.setPackType(d.getPackType());
		v.setPicUrl(d.getPicUrl());
		v.setPrice(d.getPrice());
		v.setProductionDate(d.getProductionDate());
		v.setSku(d.getSku());
		v.setStatus(d.getStatus());
		v.setSysRemark(d.getSysRemark());
		v.setType(d.getType());
		v.setUnit(d.getUnit());
		v.setUpdateTime(d.getUpdateTime());
		v.setUpdator(d.getUpdator());
		
		List<ProductAttribute> listdo = d.getProductAttribute();
		List<ProductAttributeVo> list = new ArrayList<ProductAttributeVo>();
		if(LogicUtil.isNotNullAndEmpty(listdo)) {
			for (ProductAttribute productAttribute : listdo) {
				list.add(convert2Vo(productAttribute));
			}
		}
		v.setProductAttribute(list);
		
		return v;
	}
	public static ProductAttributeVo convert2Vo(ProductAttribute d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		ProductAttributeVo v = new ProductAttributeVo();
		v.setId(d.getId());
		v.setName(d.getName());
		v.setProductId(d.getProductId());
		v.setRemark(d.getRemark());
		v.setValue(d.getValue());
		
		return v;
	}
}
