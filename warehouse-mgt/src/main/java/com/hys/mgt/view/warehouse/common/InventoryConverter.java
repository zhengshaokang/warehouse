package com.hys.mgt.view.warehouse.common;


import java.util.UUID;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.warehouse.vo.InventoryVo;
import com.hys.model.warehouse.Inventory;


public class InventoryConverter {
	public static Inventory convert2Do(InventoryVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		Inventory d = new Inventory();
		d.setId(vo.getId());
		d.setBatchNo(vo.getBatchNo()!=null?vo.getBatchNo().trim():"");
		d.setWarehouseCode(vo.getWarehouseCode()!=null?vo.getWarehouseCode().trim():"");
		d.setQty(vo.getQty());
		d.setMaturityDate(vo.getMaturityDate()!=null?vo.getMaturityDate().trim():"");
		d.setInWarehouseDate(vo.getWarehouseCode()!=null?vo.getWarehouseCode().trim():"");
		d.setProductId(vo.getProductId());
		d.setCreateTime(vo.getCreateTime());
		d.setCreator(vo.getCreator());
		return d;
	}
	
	
	public static InventoryVo convert2Vo(Inventory vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		InventoryVo d = new InventoryVo();
		d.setId(vo.getId());
		d.setBatchNo(vo.getBatchNo()!=null?vo.getBatchNo().trim():"");
		d.setWarehouseCode(vo.getWarehouseCode()!=null?vo.getWarehouseCode().trim():"");
		d.setQty(vo.getQty());
		d.setMaturityDate(vo.getMaturityDate()!=null?vo.getMaturityDate().trim():"");
		d.setInWarehouseDate(vo.getWarehouseCode()!=null?vo.getWarehouseCode().trim():"");
		d.setProductId(vo.getProductId());
		d.setCreateTime(vo.getCreateTime());
		d.setCreator(vo.getCreator());
		return d;
	}
	
	public static void main(String[] args) {
		UUID u = UUID.randomUUID();
		System.out.println(u.toString().toUpperCase());
		UUID u1 = UUID.randomUUID();
		System.out.println(u1.toString().toUpperCase());
		UUID u2 = UUID.randomUUID();
		System.out.println(u2.toString().toUpperCase());
	}
}
