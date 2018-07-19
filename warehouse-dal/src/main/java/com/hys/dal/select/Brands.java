package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.dal.component.product.IBrandDalComp;
import com.hys.model.product.Brand;

@Component
public class Brands {
	@Autowired
	private IBrandDalComp brandDalComp;
	
	public Map<String,String> getOptions(){
		
		List<Brand> list = brandDalComp.queryBrands();
		Map<String,String> map = new LinkedHashMap<String,String>();
		if(null !=list) {
			for (Brand b : list) {
				map.put(b.getId().toString(), b.getName());
			}
		}
		return map;
	}
	
	public Map<String,String> getOptions(Integer creator){
		List<Brand> list = brandDalComp.queryBrandsByCreator(creator);
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		if(null !=list) {
			for (Brand b : list) {
				map.put(b.getId().toString(), b.getName());
			}
		}
		return map;
	}
	
}
