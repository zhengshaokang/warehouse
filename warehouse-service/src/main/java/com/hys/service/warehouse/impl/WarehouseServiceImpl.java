package com.hys.service.warehouse.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.warehouse.IWarehouseDalComp;
import com.hys.model.warehouse.Warehouse;
import com.hys.service.warehouse.IWarehouseService;

@Service
public class WarehouseServiceImpl implements IWarehouseService {

	@Autowired
	private IWarehouseDalComp warehouseDalComp;
	
	@Override
	public boolean addWarehouse(Warehouse warehouse) {
		if(null == warehouse){
			return false;
		}
		Integer id = warehouseDalComp.addWarehouse(warehouse);
		if(id != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateWarehouse(Warehouse warehouse) {
		if(null == warehouse){
			return false;
		}
		return warehouseDalComp.updateWarehouse(warehouse);
	}

	@Override
	public boolean deleteWarehouse(Integer id) {
		if(id == null) {
			return false;
		}
		return warehouseDalComp.deleteWarehouse(id);
	}

	@Override
	public Warehouse queryWarehouseById(Integer id) {
		if(id == null) {
			return null;
		}
		return warehouseDalComp.queryWarehouseById(id);
	}

	@Override
	public List<Warehouse> queryWarehouses() {
		return warehouseDalComp.queryWarehouses();
	}

	@Override
	public PageData<Warehouse> pageQueryWarehouse(PageParam<Warehouse> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return warehouseDalComp.pageQueryWarehouse(page);
	}

	@Override
	public Warehouse queryWarehouseByName(String name,Integer creator) {
		if(name == null) {
			return null;
		}
		return warehouseDalComp.queryWarehouseByName(name,creator);
	}

	@Override
	public Warehouse queryWarehouseByCode(String code) {
		if(code == null) {
			return null;
		}
		return warehouseDalComp.queryWarehouseByCode(code);
	}

}
