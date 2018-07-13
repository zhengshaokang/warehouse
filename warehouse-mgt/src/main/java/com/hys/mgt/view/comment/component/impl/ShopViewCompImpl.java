package com.hys.mgt.view.comment.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.common.ShopConverter;
import com.hys.mgt.view.comment.component.IShopViewComp;
import com.hys.mgt.view.comment.vo.ShopVo;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.model.comment.Shop;
import com.hys.service.comment.IShopService;

@Component
public class ShopViewCompImpl implements IShopViewComp {
	
	private final static Logger log = LogProxy.getLogger(ShopViewCompImpl.class);
	
	@Autowired
	private IShopService shopService;

	@Override
	public ResultPrompt addShop(ShopVo shopVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			//验证名称是否存在，不能重复
			Shop  pc = shopService.validateShopName(shopVo.getName(),shopVo.getPlatform(),shopVo.getUserId());
			if(null != pc) {
				 rp.setStatusCode("200");
				 rp.setMessage("该平台的店铺已经存在！");
				 //rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
				 return rp;
			}
			boolean b = shopService.addShop(ShopConverter.convert2Do(shopVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ShopVo queryShopById(Integer id, Integer userId) {
		
		return ShopConverter.convert2Vo(shopService.queryShopById(id, userId));
	}

	@Override
	public PageData<ShopVo> pageQueryShops(ShopVo shopVo) {
		PageData<ShopVo> pageVo = null;
        try
        {
        	Shop u = (Shop) ShopConverter.convert2Do(shopVo);  
            PageParam<Shop> page = new PageParam<Shop>();
            page.setP(u);
            page.setPageNo(shopVo.getPageNum());
            page.setPageSize(shopVo.getNumPerPage());
            PageData<Shop> pd = shopService.pageQueryShops(page);

            List<Shop> list = pd.getPageData();
            List<ShopVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list))
            {
                for (Shop user : list)
                {
                    listvo.add((ShopVo) ShopConverter.convert2Vo(user));
                }
            }
            pageVo = new PageData<ShopVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        }
        catch (Exception e)
        {
            log.debug("pageQueryUsers exception=>" + e);
        }
        return pageVo;
	}

	@Override
	public ResultPrompt updateShop(ShopVo shopVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = shopService.updateShop(ShopConverter.convert2Do(shopVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ResultPrompt deleteShop(Integer id,Integer userId) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = shopService.deleteShop(id, userId);
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	        rp.setNavTabId("comment/shop-list"); // 要刷新的tab页id
		}
		return rp;
	}


}
