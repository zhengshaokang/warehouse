package com.hys.service.purchase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.purchase.ISupplierDalComp;
import com.hys.model.purchase.Supplier;
import com.hys.service.purchase.ISupplierService;

@Service
public class SupplierServiceImpl implements ISupplierService {

	@Autowired
	private ISupplierDalComp supplierDalComp;
	
	@Override
	public boolean addSupplier(Supplier supplier) {
		if(null == supplier){
			return false;
		}
		Integer id = supplierDalComp.addSupplier(supplier);
		if(id != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateSupplier(Supplier Supplier) {
		if(null == Supplier){
			return false;
		}
		return supplierDalComp.updateSupplier(Supplier);
	}

	@Override
	public boolean deleteSupplier(Integer id,Integer creator) {
		if(id == null) {
			return false;
		}
		return supplierDalComp.deleteSupplier(id,creator);
	}

	@Override
	public Supplier querySupplierById(Integer id) {
		if(id == null) {
			return null;
		}
		return supplierDalComp.querySupplierById(id);
	}

	@Override
	public List<Supplier> querySuppliers() {
		return supplierDalComp.querySuppliers();
	}

	@Override
	public PageData<Supplier> pageQuerySupplier(
			PageParam<Supplier> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return supplierDalComp.pageQuerySupplier(page);
	}

	@Override
	public Supplier querySupplierByName(String name,Integer creator) {
		if (LogicUtil.isNull(name)){
            return null;
        }
		return supplierDalComp.querySupplierByName(name,creator);
	}

}
