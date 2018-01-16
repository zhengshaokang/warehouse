package com.hys.dal.component.purchase.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.purchase.ISupplierDalComp;
import com.hys.dal.db.purchase.ISupplierDao;
import com.hys.model.purchase.Supplier;

@Component
public class SupplierDalCompImpl implements ISupplierDalComp {
	
	@Autowired
	private ISupplierDao supplierDao;

	@Override
	public Integer addSupplier(Supplier supplier) {
		supplierDao.addSupplier(supplier);
		return supplier.getId();
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {
		return JdbcUtil.isSuccess(supplierDao.updateSupplier(supplier));
	}

	@Override
	public boolean deleteSupplier(Integer id,Integer creator) {
		return JdbcUtil.isSuccess(supplierDao.deleteSupplier(id,creator));
	}

	@Override
	public Supplier querySupplierById(Integer id) {
		return supplierDao.querySupplierById(id);
	}


	@Override
	public List<Supplier> querySuppliers() {
		return supplierDao.querySuppliers();
	}
	
	@Override
	public List<Supplier> querySuppliersByCreator(Integer creator) {
		return supplierDao.querySuppliersByCreator(creator);
	}



	@Override
	public PageData<Supplier> pageQuerySupplier(PageParam<Supplier> page) {
		 List<Supplier> pcs = supplierDao.pageQuerySupplier(page);
         return new PageData<Supplier>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pcs);
	}

	@Override
	public Supplier querySupplierByName(String name,Integer creator) {
		return supplierDao.querySupplierByName(name,creator);
	}

}
