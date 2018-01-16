package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.dal.component.warehouse.IWarehouseDalComp;
import com.hys.model.warehouse.Warehouse;

@Component
public class Warehouses {
	@Autowired
	private IWarehouseDalComp warehouseDalComp;
	
	
	public  Map<String,String> getWarehouses(){
		List<Warehouse> ws = warehouseDalComp.queryWarehouses();
		Map<String,String> map = new LinkedHashMap<String,String>();
		if(null !=ws) { 
			for (Warehouse w : ws) {
				map.put(w.getCode(), w.getName());
			}
		}
		return map;
	}

	public  Map<String,String> getWarehouses(Integer creator){
		List<Warehouse> ws = warehouseDalComp.queryWarehousesByCreator(creator);
		Map<String,String> map = new LinkedHashMap<String,String>();
		if(null !=ws) { 
			for (Warehouse w : ws) {
				map.put(w.getCode(), w.getName());
			}
		}
		return map;
	}
}
