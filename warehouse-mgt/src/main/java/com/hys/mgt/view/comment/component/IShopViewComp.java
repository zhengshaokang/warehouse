package com.hys.mgt.view.comment.component;


import com.hys.commons.page.PageData;
import com.hys.mgt.view.comment.vo.ShopVo;
import com.hys.mgt.view.common.vo.ResultPrompt;

public interface IShopViewComp {
	
    public ResultPrompt addShop(ShopVo shopVo);
    public ResultPrompt updateShop(ShopVo shopVo);
    public ShopVo queryShopById(Integer id,Integer userId);
    public PageData<ShopVo> pageQueryShops(ShopVo shopVo);
    public ResultPrompt deleteShop(Integer id,Integer userId);
  
}
