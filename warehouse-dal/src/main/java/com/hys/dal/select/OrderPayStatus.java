package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumOrderPayStatus;

public class OrderPayStatus {
	
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		for (EnumOrderPayStatus es : EnumOrderPayStatus.values()) {
			map.put(es.getValue().toString(), es.getName());
		}
		return map;
	}
	
}
