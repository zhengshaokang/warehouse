package com.hys.mgt.view.comment.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.page.PageData;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.Platforms;
import com.hys.dal.select.Status;
import com.hys.dal.select.Users;
import com.hys.mgt.view.comment.component.IShopViewComp;
import com.hys.mgt.view.comment.vo.ShopVo;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/comment/")
public class ShopController {
	   @Autowired
	    private IShopViewComp shopViewComp;
	   @Autowired
	   private Users users;

	    @RequestMapping(value = "shop-list")
	    public String list(ShopVo shopVo, ModelMap modelMap, HttpServletRequest request){
	    	
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	modelMap.put("userId", Integer.parseInt(sysAdminVo.getId()));
	    	if(LogicUtil.isNull(shopVo.getUserId())) {
	    		shopVo.setUserId(Integer.parseInt(sysAdminVo.getId()));
	    	}
	    	
	        PageData<ShopVo> pageParam = shopViewComp.pageQueryShops(shopVo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("shopParam", shopVo);// 查询时传入的参数
	        
	        modelMap.put("platforms", Platforms.getOptions());
	        modelMap.put("status", Status.getOptions());
	        modelMap.put("users", users.getOptions());
	        return "comment/shop_list";
	    }
	    
	    
	    @RequestMapping(value = "shop-add")
	    public String shopAdd(ModelMap modelMap, HttpServletRequest request){
	        
	        modelMap.put("platforms", Platforms.getOptions());
	        
	        return "comment/shop_add";
	    }
	    
	    @RequestMapping(value = "shop-update")
	    public String shopUpdate(Integer shopId, ModelMap modelMap, HttpServletRequest request){
	    	HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	        modelMap.put("platforms", Platforms.getOptions());
	        ShopVo shop = shopViewComp.queryShopById(shopId, Integer.parseInt(sysAdminVo.getId()));
	        modelMap.put("shop", shop);
	        modelMap.put("status", Status.getOptions());
	        return "comment/shop_update";
	    }
	    
	    @RequestMapping(value = "shop-add-submit")
	    @ResponseBody
	    public ResultPrompt shopAddSubmit(ShopVo shopVo, HttpServletRequest request){
	        
    		HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		    shopVo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
		    shopVo.setUserId(Integer.parseInt(sysAdminVo.getId()));
		    shopVo.setStatus(0);
		    ResultPrompt resultPrompt = shopViewComp.addShop(shopVo);
		    
	        return resultPrompt;
	    }
	    
	    @RequestMapping(value = "shop-update-submit")
	    @ResponseBody
	    public ResultPrompt shopUpdateSubmit(ShopVo shopVo, HttpServletRequest request){
	        
		    ResultPrompt resultPrompt = shopViewComp.updateShop(shopVo);
		    
	        return resultPrompt;
	    }
	    
	    @RequestMapping(value = "shop-delete")
	    @ResponseBody
	    public ResultPrompt shopDelete(Integer shopId, HttpServletRequest request){
	        
    		HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		    ResultPrompt resultPrompt = shopViewComp.deleteShop(shopId,Integer.parseInt(sysAdminVo.getId()));
		    
	        return resultPrompt;
	    }
}
