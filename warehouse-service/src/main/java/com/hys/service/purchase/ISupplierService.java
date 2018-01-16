package com.hys.service.purchase;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.purchase.Supplier;

public interface ISupplierService {
	public boolean addSupplier(Supplier supplier);
	public boolean updateSupplier(Supplier supplier);
	public boolean deleteSupplier(Integer id,Integer creator);
	public Supplier querySupplierById(Integer id);
	public List<Supplier> querySuppliers();
	public PageData<Supplier> pageQuerySupplier(PageParam<Supplier> page);
	public Supplier querySupplierByName(String name,Integer creator);
}
