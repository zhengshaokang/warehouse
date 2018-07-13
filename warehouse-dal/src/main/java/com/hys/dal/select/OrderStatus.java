package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumOrderStatus;

public class OrderStatus {
	
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "");
		for (EnumOrderStatus es : EnumOrderStatus.values()) {
			map.put(es.getValue().toString(), es.getName());
		}
		return map;
	}
	
}
