package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumProductPackType;

public class ProductPackTypes {
	public static Map<Integer,String> getPackTypes(){
		Map<Integer,String> map = new LinkedHashMap<Integer,String>();
		for (EnumProductPackType es : EnumProductPackType.values()) {
			map.put(es.getValues(), es.getName());
		}
		return map;
	}
	
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		for (EnumProductPackType es : EnumProductPackType.values()) {
			map.put(es.getValues().toString(), es.getName());
		}
		return map;
	}
	
	public static String getPackTypesName(Integer status) {
		for (EnumProductPackType es : EnumProductPackType.values()) {
			if(es.getValues() == status) {
				return es.getName();
			}
		}
		return null;
	} 
}
