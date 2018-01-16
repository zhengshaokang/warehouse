package com.hys.mgt.view.product.common;


import java.util.UUID;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.product.vo.ProductInPriceVo;
import com.hys.mgt.view.warehouse.vo.InventoryVo;
import com.hys.model.product.ProductInPrice;
import com.hys.model.warehouse.Inventory;


public class ProductPriceConverter {
	public static ProductInPrice convert2Do(ProductInPriceVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		ProductInPrice d = new ProductInPrice();
		d.setId(vo.getId());
		d.setProductId(vo.getProductId());
		d.setInDate(vo.getInDate());
		d.setPrice(vo.getPrice());
		d.setInQty(vo.getInQty());
		d.setCreateTime(vo.getCreateTime());
		d.setCreator(vo.getCreator());
		return d;
	}
	
	
	public static ProductInPriceVo convert2Vo(ProductInPrice vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		ProductInPriceVo d = new ProductInPriceVo();
		d.setId(vo.getId());
		d.setProductId(vo.getProductId());
		d.setInDate(vo.getInDate());
		d.setPrice(vo.getPrice());
		d.setInQty(vo.getInQty());
		d.setCreateTime(vo.getCreateTime());
		d.setCreator(vo.getCreator());
		return d;
	}
}
