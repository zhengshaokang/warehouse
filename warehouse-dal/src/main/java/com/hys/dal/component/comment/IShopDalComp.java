package com.hys.dal.component.comment;


import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.comment.Shop;

public interface IShopDalComp {
	
    public int addShop(Shop shop);
    public boolean updateShop(Shop shop);
    public boolean deleteShop(Integer id,Integer userId);
    public List<Shop> queryShopByUserId(Integer userId);
    public List<Shop> queryShopByUserIdAndPlatfrom(Integer userId,Integer platfrom);
    public Shop queryShopById(Integer id,Integer userId);
    public PageData<Shop> pageQueryShops(PageParam<Shop> page);
    public Shop validateShopName(String name,Integer platform,Integer userId);
  
}
