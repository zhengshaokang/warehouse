package com.hys.dal.component.warehouse.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.warehouse.IWarehouseDalComp;
import com.hys.dal.db.warehouse.IWarehouseDao;
import com.hys.model.warehouse.Warehouse;

@Component
public class WarehouseDalCompImpl implements IWarehouseDalComp{
	@Autowired
	private IWarehouseDao warehouseDao;

	@Override
	public Integer addWarehouse(Warehouse warehouse) {
		warehouseDao.addWarehouse(warehouse);
		return warehouse.getId();
	}

	@Override
	public List<Warehouse> queryWarehouses() {
		return warehouseDao.queryWarehouses();
	}

	@Override
	public PageData<Warehouse> pageQueryWarehouse(PageParam<Warehouse> page) {
		 List<Warehouse> warehouses = warehouseDao.pageQueryWarehouse(page);
         return new PageData<Warehouse>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), warehouses);
	}

	@Override
	public boolean updateWarehouse(Warehouse warehouse) {
		
		return JdbcUtil.isSuccess(warehouseDao.updateWarehouse(warehouse));
	}

	@Override
	public boolean deleteWarehouse(Integer id) {
		return JdbcUtil.isSuccess(warehouseDao.deleteWarehouse(id));
	}

	@Override
	public Warehouse queryWarehouseById(Integer id) {
		return warehouseDao.queryWarehouseById(id);
	}

	@Override
	public Warehouse queryWarehouseByName(String name,Integer creator) {
		return warehouseDao.queryWarehouseByName(name,creator);
	}

	@Override
	public Warehouse queryWarehouseByCode(String code) {
		return warehouseDao.queryWarehouseByCode(code);
	}

	@Override
	public List<Warehouse> queryWarehousesByCreator(Integer creator) {
		return warehouseDao.queryWarehousesByCreator(creator);
	}

}
