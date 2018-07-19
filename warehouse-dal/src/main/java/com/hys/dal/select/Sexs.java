package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumSex;

public class Sexs {
	
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		for (EnumSex es : EnumSex.values()) {
			map.put(es.getStatus().toString(), es.getName());
		}
		return map;
	}
	
}
