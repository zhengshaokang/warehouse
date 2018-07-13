package com.hys.dal.component.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.comment.IShopDalComp;
import com.hys.dal.db.comment.IShopDao;
import com.hys.model.comment.Shop;

@Component
public class ShopDalCompImpl implements IShopDalComp {
	
	@Autowired
	private IShopDao shopDao;

	@Override
	public int addShop(Shop shop) {
		shopDao.addShop(shop);
		return shop.getId();
	}

	@Override
	public boolean updateShop(Shop shop) {
		return JdbcUtil.isSuccess(shopDao.updateShop(shop));
	}

	@Override
	public Shop queryShopById(Integer id, Integer userId) {
		return shopDao.queryShopById(id, userId);
	}

	@Override
	public PageData<Shop> pageQueryShops(PageParam<Shop> page) {
		List<Shop> shops = shopDao.pageQueryShops(page);
		return new PageData<Shop>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), shops);
	}

	@Override
	public Shop validateShopName(String name, Integer platform, Integer userId) {
		return shopDao.validateShopName(name, platform, userId);
	}

	@Override
	public boolean deleteShop(Integer id, Integer userId) {
		return JdbcUtil.isSuccess(shopDao.deleteShop(id, userId));
	}

	@Override
	public List<Shop> queryShopByUserId(Integer userId) {
		return shopDao.queryShopByUserId(userId);
	}

}
