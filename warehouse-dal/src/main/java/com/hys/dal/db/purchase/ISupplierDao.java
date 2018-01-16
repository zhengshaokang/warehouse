package com.hys.dal.db.purchase;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.purchase.Supplier;

public interface ISupplierDao {
	public int addSupplier(Supplier Supplier);
	public int updateSupplier(Supplier Supplier);
	public int deleteSupplier(@Param("id") Integer id,@Param("creator") Integer creator);
	public Supplier querySupplierById(Integer id);
	public List<Supplier> querySuppliers();
	public List<Supplier> querySuppliersByCreator(@Param("creator") Integer creator);
	public List<Supplier> pageQuerySupplier(PageParam<Supplier> page);
	public Supplier querySupplierByName(@Param("name") String name,@Param("creator") Integer creator);
}
