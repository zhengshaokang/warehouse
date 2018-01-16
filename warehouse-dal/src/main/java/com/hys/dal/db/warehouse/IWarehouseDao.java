package com.hys.dal.db.warehouse;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.warehouse.Warehouse;

public interface IWarehouseDao {
	public int addWarehouse(Warehouse Warehouse);
	public int updateWarehouse(Warehouse Warehouse);
	public int deleteWarehouse(Integer id);
	public Warehouse queryWarehouseById(Integer id);
	public Warehouse queryWarehouseByName(@Param("name") String name,@Param("creator") Integer creator);
	public Warehouse queryWarehouseByCode(String code);
	public List<Warehouse> queryWarehouses();
	public List<Warehouse> queryWarehousesByCreator(@Param("creator") Integer creator);
	public List<Warehouse> pageQueryWarehouse(PageParam<Warehouse> page);
}
