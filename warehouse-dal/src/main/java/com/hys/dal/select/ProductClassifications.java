package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.dal.component.product.IProductClassificationDalComp;
import com.hys.model.product.ProductClassification;

@Component
public class ProductClassifications {
	
	@Autowired
	private  IProductClassificationDalComp productClassificationDalComp;

	public  Map<String,String> getClassificationsOne(){
		
		List<ProductClassification> lsit = productClassificationDalComp.queryProductClassificationOne();
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "");
		if(null !=lsit) {
			for (ProductClassification productClassification : lsit) {
				map.put(productClassification.getId().toString(), productClassification.getName());
			}
		}
		
		return map;
	}
	
	public  Map<String,String> getClassificationsOne(Integer creator){
		
		List<ProductClassification> lsit = productClassificationDalComp.queryProductClassificationOneByCreator(creator);
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "");
		if(null !=lsit) {
			for (ProductClassification productClassification : lsit) {
				map.put(productClassification.getId().toString(), productClassification.getName());
			}
		}
		
		return map;
	}
	
	public  Map<String,String> getClassifications(){
		
		List<ProductClassification> lsit = productClassificationDalComp.queryProductClassifications();
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "");
		if(null !=lsit) {
			for (ProductClassification productClassification : lsit) {
				map.put(productClassification.getId().toString(), productClassification.getName());
			}
		}
		
		return map;
	}
	
	public  Map<String,String> getClassifications(Integer creator){
		
		List<ProductClassification> lsit = productClassificationDalComp.queryProductClassificationsByCreator(creator);
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "");
		if(null !=lsit) {
			for (ProductClassification productClassification : lsit) {
				map.put(productClassification.getId().toString(), productClassification.getName());
			}
		}
		
		return map;
	}
	
	
	public  Map<String,String> getClassificationsSub(Integer parentId){
		
		List<ProductClassification> lsit = productClassificationDalComp.queryProductClassificationByParentId(parentId);
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "");
		if(null !=map) {
			for (ProductClassification productClassification : lsit) {
				map.put(productClassification.getId().toString(), productClassification.getName());
			}
		}
		
		return map;
	}
	
	
	public  Map<String,String> getClassificationsSub(Integer parentId,Integer creator){
		
		List<ProductClassification> lsit = productClassificationDalComp.queryProductClassificationByParentIdAndCreator(parentId, creator);
		
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "");
		if(null !=map) {
			for (ProductClassification productClassification : lsit) {
				map.put(productClassification.getId().toString(), productClassification.getName());
			}
		}
		
		return map;
	}
	
	
}
