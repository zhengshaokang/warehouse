package com.hys.service.comment;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.comment.Shop;

public interface IShopService {
	
    public boolean addShop(Shop shop);
    public boolean updateShop(Shop shop);
    public Shop queryShopById(Integer id,Integer userId);
    public PageData<Shop> pageQueryShops(PageParam<Shop> page);
    public Shop validateShopName(String name,Integer platform,Integer userId);
    public boolean deleteShop(Integer id,Integer userId);
    public List<Shop> queryShopByUserId(Integer userId);
  
}
