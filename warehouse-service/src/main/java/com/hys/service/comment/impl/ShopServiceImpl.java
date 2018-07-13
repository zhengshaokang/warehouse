package com.hys.service.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.comment.IShopDalComp;
import com.hys.model.comment.Shop;
import com.hys.service.comment.IShopService;

@Service
public class ShopServiceImpl implements IShopService {
	
	@Autowired
	private IShopDalComp shopDalComp;

	@Override
	public boolean addShop(Shop shop) {
		if(LogicUtil.isNull(shop)) {
			return false;
		}
		Integer id = shopDalComp.addShop(shop);
		if(id != null){
			return true;
		}
		return false ;
	}

	@Override
	public boolean updateShop(Shop shop) {
		if(LogicUtil.isNull(shop)) {
			return false;
		}
		return shopDalComp.updateShop(shop);
	}

	@Override
	public Shop queryShopById(Integer id, Integer userId) {
		
		if(LogicUtil.isNull(id) || LogicUtil.isNull(userId)) {
			return null;
		}
		
		return shopDalComp.queryShopById(id, userId);
	}

	@Override
	public PageData<Shop> pageQueryShops(PageParam<Shop> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return shopDalComp.pageQueryShops(page);
	}

	@Override
	public Shop validateShopName(String name, Integer platform, Integer userId) {
		
		if(LogicUtil.isNullOrEmpty(name) || LogicUtil.isNull(userId) || LogicUtil.isNull(platform)) {
			return null;
		}
		
		return shopDalComp.validateShopName(name,platform, userId);
	}

	@Override
	public boolean deleteShop(Integer id, Integer userId) {
		if(LogicUtil.isNull(id) || LogicUtil.isNull(userId)) {
			return false;
		} 
		return shopDalComp.deleteShop(id, userId);
	}

	@Override
	public List<Shop> queryShopByUserId(Integer userId) {
		if(LogicUtil.isNull(userId)) {
			return null;
		} 
		return shopDalComp.queryShopByUserId(userId);
	}

}
