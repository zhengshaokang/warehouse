package com.hys.dal.component.purchase;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.purchase.Supplier;

public interface ISupplierDalComp {
	public Integer addSupplier(Supplier Supplier);
	public boolean updateSupplier(Supplier Supplier);
	public boolean deleteSupplier(Integer id,Integer creator);
	public Supplier querySupplierById(Integer id);
	public List<Supplier> querySuppliers();
	public List<Supplier> querySuppliersByCreator(Integer creator);
	public PageData<Supplier> pageQuerySupplier(PageParam<Supplier> page);
	public Supplier querySupplierByName(String name,Integer creator);
}
