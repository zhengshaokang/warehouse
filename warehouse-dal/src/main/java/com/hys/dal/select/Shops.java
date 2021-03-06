package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.dal.component.comment.IShopDalComp;
import com.hys.model.comment.Shop;

@Component
public class Shops {
	@Autowired
	private IShopDalComp shopDalComp;
	
	
	public Map<String,String> getOptions(Integer userId){
		List<Shop> list = shopDalComp.queryShopByUserId(userId);
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("-1", "请选择");
		if(null !=list) {
			for (Shop b : list) {
				map.put(b.getId().toString(), b.getName());
			}
		}
		return map;
	}
	
	public LinkedHashMap<Integer,String> getOptions(Integer userId,Integer platform){
		List<Shop> list = shopDalComp.queryShopByUserIdAndPlatfrom(userId,platform);
		LinkedHashMap<Integer,String> map = new LinkedHashMap<Integer,String>();
		if(null !=list) {
			for (Shop b : list) {
				map.put(b.getId(), b.getName());
			}
		}
		return map;
	}
	
}
