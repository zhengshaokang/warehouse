package com.hys.mgt.view.product.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.product.vo.SupplierVo;
import com.hys.model.purchase.Supplier;

public class SupplierConverter {
	
	public static Supplier convert2Do(SupplierVo vo) {
		if(LogicUtil.isNull(vo) ) {
			return null;
		}
		Supplier d = new Supplier();
		d.setCreateTime(vo.getCreateTime()!=null?vo.getCreateTime().trim():"");
		d.setCreator(vo.getCreator());
		d.setId(vo.getId());
		d.setName(vo.getName()!=null?vo.getName().trim():"");
		d.setRemark(vo.getRemark()!=null?vo.getRemark().trim():"");
		d.setAddress(vo.getAddress()!=null?vo.getAddress().trim():"");
		d.setContacts(vo.getContacts()!=null?vo.getContacts().trim():"");
		d.setTelephone(vo.getTelephone()!=null?vo.getTelephone().trim():"");
		return d;
	}
	
	public static SupplierVo convert2Vo(Supplier d) {
		if(LogicUtil.isNull(d) ) {
			return null;
		}
		SupplierVo v = new SupplierVo();
		
		v.setCreateTime(d.getCreateTime()!=null?d.getCreateTime().trim():"");
		v.setCreator(d.getCreator());
		v.setId(d.getId());
		v.setName(d.getName()!=null?d.getName().trim():"");
		v.setRemark(d.getRemark()!=null?d.getRemark().trim():"");
		v.setAddress(d.getAddress()!=null?d.getAddress().trim():"");
		v.setContacts(d.getContacts()!=null?d.getContacts().trim():"");
		v.setTelephone(d.getTelephone()!=null?d.getTelephone().trim():"");
		return v;
	}
}
