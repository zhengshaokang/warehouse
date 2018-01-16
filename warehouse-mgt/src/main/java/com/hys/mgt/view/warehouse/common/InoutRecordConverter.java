package com.hys.mgt.view.warehouse.common;


import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.warehouse.vo.InoutRecordVo;
import com.hys.model.warehouse.InoutRecord;


public class InoutRecordConverter {
	public static InoutRecord convert2Do(InoutRecordVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		InoutRecord d = new InoutRecord();
		d.setId(vo.getId());
		d.setAfterInventory(vo.getAfterInventory());
		d.setBeforeInventory(vo.getBeforeInventory());
		d.setChangeInventory(vo.getChangeInventory());
		d.setOperateTime(vo.getOperateTime());
		d.setOperateTimeEnd(vo.getOperateTimeEnd());
		d.setOperateTimeStart(vo.getOperateTimeStart());
		d.setOperator(vo.getOperator());
		d.setProductId(vo.getProductId());
		d.setRecordType(vo.getRecordType());
		d.setSku(vo.getSku());
		d.setName(vo.getName());
		d.setBacthNo(vo.getBacthNo());
		d.setWarehouseCode(vo.getWarehouseCode());
		d.setCode(vo.getCode());
		d.setRemark(vo.getRemark());
		d.setMaturityDate(vo.getMaturityDate());
		d.setCreateTime(vo.getCreateTime());
		d.setCreator(vo.getCreator());
		return d;
	}
	
	
	public static InoutRecordVo convert2Vo(InoutRecord d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		InoutRecordVo v = new InoutRecordVo();
		
		v.setId(d.getId());
		v.setAfterInventory(d.getAfterInventory());
		v.setBeforeInventory(d.getBeforeInventory());
		v.setChangeInventory(d.getChangeInventory());
		v.setOperateTime(d.getOperateTime());
		v.setOperateTimeEnd(d.getOperateTimeEnd());
		v.setOperateTimeStart(d.getOperateTimeStart());
		v.setOperator(d.getOperator());
		v.setProductId(d.getProductId());
		v.setRecordType(d.getRecordType());
		v.setSku(d.getSku());
		v.setName(d.getName());
		v.setBacthNo(d.getBacthNo());
		v.setWarehouseCode(d.getWarehouseCode());
		v.setCode(d.getCode());
		v.setRemark(d.getRemark());
		v.setMaturityDate(d.getMaturityDate());
		v.setCreateTime(d.getCreateTime());
		v.setCreator(d.getCreator());
		return v;
	}
}
