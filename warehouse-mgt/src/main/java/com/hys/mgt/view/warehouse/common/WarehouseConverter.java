package com.hys.mgt.view.warehouse.common;


import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.warehouse.vo.WarehouseVo;
import com.hys.model.warehouse.Warehouse;


public class WarehouseConverter {
	public static Warehouse convert2Do(WarehouseVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		Warehouse d = new Warehouse();
		d.setId(vo.getId());
		d.setName(vo.getName()!=null?vo.getName().trim():"");
		d.setAddress(vo.getAddress()!=null?vo.getAddress().trim():"");
		d.setCode(vo.getCode()!=null?vo.getCode().trim():"");
		d.setRemark(vo.getRemark()!=null?vo.getRemark().trim():"");
		d.setCreateTime(vo.getCreateTime()!=null?vo.getCreateTime().trim():"");
		d.setCreator(vo.getCreator());
		return d;
	}
	
	
	public static WarehouseVo convert2Vo(Warehouse vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		WarehouseVo d = new WarehouseVo();
		d.setId(vo.getId());
		d.setName(vo.getName()!=null?vo.getName().trim():"");
		d.setAddress(vo.getAddress()!=null?vo.getAddress().trim():"");
		d.setCode(vo.getCode()!=null?vo.getCode().trim():"");
		d.setRemark(vo.getRemark()!=null?vo.getRemark().trim():"");
		d.setCreateTime(vo.getCreateTime()!=null?vo.getCreateTime().trim():"");
		d.setCreator(vo.getCreator());
		return d;
	}
}
