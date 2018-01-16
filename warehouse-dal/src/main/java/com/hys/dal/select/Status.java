package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumStatus;


public class Status {
	
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		for (EnumStatus es : EnumStatus.values()) {
			map.put(es.getStatus().toString(), es.getName());
		}
		return map;
	}
	
}
