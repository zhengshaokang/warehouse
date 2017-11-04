package com.hys.mgt.view.common.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	/**
	 * 根据路径 key获得
	 * @param path
	 * @param key
	 * @return
	 */
	public static String getValue(String path, String key){
		Properties p = new Properties();
		String value = null;
		try{
			InputStream is =  PropertiesUtils.class.getClassLoader().getResourceAsStream(path);
			p.load(is);
			value = p.getProperty(key);
		}catch(Exception e){
			e.printStackTrace();
		}
		return value;
	}
}
