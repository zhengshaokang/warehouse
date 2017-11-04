package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumProductAttr;



public class ProductAttrs {
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		for (EnumProductAttr es : EnumProductAttr.values()) {
			map.put(es.getValue(), es.getName());
		}
		return map;
	}
}
