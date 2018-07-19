package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.dal.component.product.IProductSpecificationsDalComp;
import com.hys.model.product.ProductSpecifications;

@Component
public class ProductSpecificationss {
	
	@Autowired
	private  IProductSpecificationsDalComp productSpecificationsDalComp;

	public  Map<String,String> getSpecificationssOne(){
		
		List<ProductSpecifications> lsit = productSpecificationsDalComp.queryProductSpecificationsOne();
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		if(null !=map) {
			for (ProductSpecifications productSpecifications : lsit) {
				map.put(productSpecifications.getId().toString(), productSpecifications.getName());
			}
		}
		
		return map;
	}
	
	public  Map<String,String> getSpecificationssOne(Integer creator){
		
		List<ProductSpecifications> lsit = productSpecificationsDalComp.queryProductSpecificationsOneByCreator(creator);
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		if(null !=map) {
			for (ProductSpecifications productSpecifications : lsit) {
				map.put(productSpecifications.getId().toString(), productSpecifications.getName());
			}
		}
		
		return map;
	}
	
	public  Map<String,String> getSpecificationss(){
		
		List<ProductSpecifications> lsit = productSpecificationsDalComp.queryProductSpecificationss();
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		if(null !=map) {
			for (ProductSpecifications productSpecifications : lsit) {
				map.put(productSpecifications.getId().toString(), productSpecifications.getName());
			}
		}
		
		return map;
	}
	
	public  Map<String,String> getSpecificationss(Integer creator){
		
		List<ProductSpecifications> lsit = productSpecificationsDalComp.queryProductSpecificationsByCreator(creator);
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		if(null !=map) {
			for (ProductSpecifications productSpecifications : lsit) {
				map.put(productSpecifications.getId().toString(), productSpecifications.getName());
			}
		}
		
		return map;
	}
	
	public  Map<String,String> getSpecificationssSub(Integer parentId){
		
		List<ProductSpecifications> lsit = productSpecificationsDalComp.queryProductSpecificationsByParentId(parentId);
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		if(null !=lsit) {
			for (ProductSpecifications productSpecifications : lsit) {
				map.put(productSpecifications.getId().toString(), productSpecifications.getName());
			}
		}
		
		return map;
	}
	
public  Map<String,String> getSpecificationssSub(Integer parentId,Integer creator){
		
		List<ProductSpecifications> lsit = productSpecificationsDalComp.queryProductSpecificationsByParentIdAndCreator(parentId, creator);
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		if(null !=lsit) {
			for (ProductSpecifications productSpecifications : lsit) {
				map.put(productSpecifications.getId().toString(), productSpecifications.getName());
			}
		}
		
		return map;
	}
	
}
