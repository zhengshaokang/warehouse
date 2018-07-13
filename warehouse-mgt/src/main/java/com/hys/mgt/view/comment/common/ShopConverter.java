package com.hys.mgt.view.comment.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.vo.ShopVo;
import com.hys.model.comment.Shop;

public class ShopConverter {

	public static Shop convert2Do(ShopVo shopVo){
    	  if (LogicUtil.isNull(shopVo)){
              return null;
          }
    	  Shop shop = new Shop();
    	  shop.setId(shopVo.getId());
    	  shop.setCreateTime(shopVo.getCreateTime());
    	  shop.setUserId(shopVo.getUserId());
    	  shop.setDepartment(shopVo.getDepartment());
    	  shop.setName(shopVo.getName()!=null?shopVo.getName().trim():"");
    	  shop.setPlatform(shopVo.getPlatform());
    	  shop.setStatus(shopVo.getStatus());
    	  shop.setUrl(shopVo.getUrl());
          return shop;
    }

    
    public static ShopVo convert2Vo(Shop shop){
        if (LogicUtil.isNull(shop)){
        	return null;
        }
        ShopVo shopVo = new ShopVo();
        shopVo.setId(shop.getId());
        shopVo.setCreateTime(shop.getCreateTime());
        shopVo.setUserId(shop.getUserId());
        shopVo.setDepartment(shop.getDepartment());
        shopVo.setName(shop.getName());
        shopVo.setPlatform(shop.getPlatform());
        shopVo.setStatus(shop.getStatus());
        shopVo.setUrl(shop.getUrl());
        return shopVo;
    }

}
