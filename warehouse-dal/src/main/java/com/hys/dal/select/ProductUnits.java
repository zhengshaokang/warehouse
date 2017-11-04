package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumProductUnit;


public class ProductUnits {
	public static Map<Integer,String> getStatus(){
		Map<Integer,String> map = new LinkedHashMap<Integer,String>();
		for (EnumProductUnit es : EnumProductUnit.values()) {
			map.put(es.getValue(), es.getName());
		}
		return map;
	}
	
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		for (EnumProductUnit es : EnumProductUnit.values()) {
			map.put(es.getValue().toString(), es.getName());
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
