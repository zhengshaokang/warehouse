package com.hys.mgt.view.inout.common;


import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.inout.vo.InoutRecordVo;
import com.hys.model.inout.InoutRecord;


public class InoutRecordConverter {
	public static InoutRecord convert2Do(InoutRecordVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		InoutRecord d = new InoutRecord();
		d.setId(vo.getId());
		d.setAfterInventory(vo.getAfterInventory());
		d.setAfterTotal(vo.getAfterTotal());
		d.setBeforeInventory(vo.getBeforeInventory());
		d.setBeforeTotal(vo.getBeforeTotal());
		d.setChangeInventory(vo.getChangeInventory());
		d.setInoutType(vo.getInoutType());
		d.setOperateTime(vo.getOperateTime());
		d.setOperateTimeEnd(vo.getOperateTimeEnd());
		d.setOperateTimeStart(vo.getOperateTimeStart());
		d.setOperator(vo.getOperator());
		d.setProductId(vo.getProductId());
		d.setRecordType(vo.getRecordType());
		d.setSku(vo.getSku());
		d.setName(vo.getName());
		return d;
	}
	
	
	public static InoutRecordVo convert2Vo(InoutRecord d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		InoutRecordVo v = new InoutRecordVo();
		
		v.setId(d.getId());
		v.setAfterInventory(d.getAfterInventory());
		v.setAfterTotal(d.getAfterTotal());
		v.setBeforeInventory(d.getBeforeInventory());
		v.setBeforeTotal(d.getBeforeTotal());
		v.setChangeInventory(d.getChangeInventory());
		v.setInoutType(d.getInoutType());
		v.setOperateTime(d.getOperateTime());
		v.setOperateTimeEnd(d.getOperateTimeEnd());
		v.setOperateTimeStart(d.getOperateTimeStart());
		v.setOperator(d.getOperator());
		v.setProductId(d.getProductId());
		v.setRecordType(d.getRecordType());
		v.setSku(d.getSku());
		v.setName(d.getName());
		return v;
	}
}
