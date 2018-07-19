package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.Map;

import com.hys.dal.select.conenum.EnumInoutRecordType;

public class InoutRecordTypes {
	public static Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("", "请选择");
		for (EnumInoutRecordType es : EnumInoutRecordType.values()) {
			map.put(es.getValue().toString(), es.getName());
		}
		return map;
	}
	
	public static Map<String,String> getInOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put(EnumInoutRecordType.PRODUCTIN.getValue().toString(), EnumInoutRecordType.PRODUCTIN.getName());
		map.put(EnumInoutRecordType.RETURNORDERIN.getValue().toString(), EnumInoutRecordType.RETURNORDERIN.getName());
		map.put(EnumInoutRecordType.OTHERIN.getValue().toString(), EnumInoutRecordType.OTHERIN.getName());
		return map;
	}
	public static Map<String,String> getOutOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put(EnumInoutRecordType.ORDEROUT.getValue().toString(), EnumInoutRecordType.ORDEROUT.getName());
		map.put(EnumInoutRecordType.PRODUCTOUT.getValue().toString(), EnumInoutRecordType.PRODUCTOUT.getName());
		map.put(EnumInoutRecordType.ALLOCATIONOUT.getValue().toString(), EnumInoutRecordType.ALLOCATIONOUT.getName());
		map.put(EnumInoutRecordType.GIVETOUT.getValue().toString(), EnumInoutRecordType.GIVETOUT.getName());
		map.put(EnumInoutRecordType.OTHEROUT.getValue().toString(), EnumInoutRecordType.OTHEROUT.getName());
		return map;
	}
}
