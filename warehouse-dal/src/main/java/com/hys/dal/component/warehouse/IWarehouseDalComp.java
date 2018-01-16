package com.hys.dal.component.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.warehouse.Warehouse;

public interface IWarehouseDalComp {
	public Integer addWarehouse(Warehouse warehouse);
	public boolean updateWarehouse(Warehouse warehouse);
	public boolean deleteWarehouse(Integer id);
	public Warehouse queryWarehouseById(Integer id);
	public Warehouse queryWarehouseByName(String name,Integer creator);
	public Warehouse queryWarehouseByCode(String code);
	public List<Warehouse> queryWarehouses();
	public List<Warehouse> queryWarehousesByCreator(Integer creator);
	public PageData<Warehouse> pageQueryWarehouse(PageParam<Warehouse> page);
}
