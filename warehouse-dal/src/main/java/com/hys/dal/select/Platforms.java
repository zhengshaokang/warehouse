package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumPlatform;


public class Platforms {
	
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		for (EnumPlatform es : EnumPlatform.values()) {
			map.put(es.getValue().toString(), es.getName());
		}
		return map;
	}
	
}
