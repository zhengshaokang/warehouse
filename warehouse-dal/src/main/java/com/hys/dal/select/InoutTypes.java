package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumInoutType;


public class InoutTypes {
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "请选择");
		for (EnumInoutType es : EnumInoutType.values()) {
			map.put(es.getValue().toString(), es.getName());
		}
		return map;
	}
}
