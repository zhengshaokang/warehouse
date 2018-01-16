package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.dal.component.product.IProductUnitDalComp;
import com.hys.dal.select.conenum.EnumProductUnit;
import com.hys.model.product.ProductUnit;

@Component
public class ProductUnits {
	@Autowired
	private IProductUnitDalComp productUnitDalComp;
	
	public Map<String,String> getOptions(){
		
		List<ProductUnit> list = productUnitDalComp.queryProductUnits();
		Map<String,String> map = new LinkedHashMap<String,String>();
		if(null !=list) {
			for (ProductUnit productUnit : list) {
				map.put(productUnit.getId().toString(), productUnit.getName());
			}
		}
		return map;
	}
	
	public Map<String,String> getOptions(Integer creator){
		
		List<ProductUnit> list = productUnitDalComp.queryProductUnitsByCreator(creator);
		Map<String,String> map = new LinkedHashMap<String,String>();
		if(null !=list) {
			for (ProductUnit productUnit : list) {
				map.put(productUnit.getId().toString(), productUnit.getName());
			}
		}
		return map;
	}
	
	public static String getStatusName(Integer status) {
		for (EnumProductUnit es : EnumProductUnit.values()) {
			if(es.getValue() == status) {
				return es.getName();
			}
		}
		return null;
	} 
}
