package com.hys.service.warehouse;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.warehouse.Warehouse;

public interface IWarehouseService {
	public boolean addWarehouse(Warehouse Warehouse);
	public boolean updateWarehouse(Warehouse Warehouse);
	public boolean deleteWarehouse(Integer id);
	public Warehouse queryWarehouseById(Integer id);
	public Warehouse queryWarehouseByName(String name,Integer creator);
	public Warehouse queryWarehouseByCode(String code);
	public List<Warehouse> queryWarehouses();
	public PageData<Warehouse> pageQueryWarehouse(PageParam<Warehouse> page);
}
