package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.dal.component.purchase.ISupplierDalComp;
import com.hys.model.purchase.Supplier;

@Component
public class Suppliers {
	@Autowired
	private ISupplierDalComp supplierDalComp;
	
	
	public  Map<String,String> getSuppliers(){
		List<Supplier> suppliers = supplierDalComp.querySuppliers();
		Map<String,String> map = new LinkedHashMap<String,String>();
		if(null !=suppliers) { 
			for (Supplier supplier : suppliers) {
				map.put(supplier.getId().toString(), supplier.getName());
			}
		}
		return map;
	}
	
	public  Map<String,String> getSuppliers(Integer creator){
		List<Supplier> suppliers = supplierDalComp.querySuppliersByCreator(creator);
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "");
		if(null !=suppliers) { 
			for (Supplier supplier : suppliers) {
				map.put(supplier.getId().toString(), supplier.getName());
			}
		}
		return map;
	}
}
