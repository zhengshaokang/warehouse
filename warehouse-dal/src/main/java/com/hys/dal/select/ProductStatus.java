package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumProudctStatus;


public class ProductStatus {
	public static Map<Integer,String> getStatus(){
		Map<Integer,String> map = new LinkedHashMap<Integer,String>();
		for (EnumProudctStatus es : EnumProudctStatus.values()) {
			map.put(es.getStatus(), es.getName());
		}
		return map;
	}
	
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		for (EnumProudctStatus es : EnumProudctStatus.values()) {
			map.put(es.getStatus().toString(), es.getName());
		}
		return map;
	}
	
	public static String getStatusName(Integer status) {
		for (EnumProudctStatus es : EnumProudctStatus.values()) {
			if(es.getStatus() == status) {
				return es.getName();
			}
		}
		return null;
	} 
}
