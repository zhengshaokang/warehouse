package com.hys.mgt.view.comment.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hys.commons.page.PageData;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.OrderPayStatus;
import com.hys.dal.select.OrderStatus;
import com.hys.dal.select.Platforms;
import com.hys.dal.select.Shops;
import com.hys.dal.select.Users;
import com.hys.dal.select.YesNos;
import com.hys.dal.select.conenum.EnumOrderPayStatus;
import com.hys.mgt.view.comment.component.IOrderViewComp;
import com.hys.mgt.view.comment.vo.OrderVo;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/comment/")
public class OrderController {
	   @Autowired
	    private IOrderViewComp orderViewComp;
	   
	   @Autowired 
	   private Shops shops;
	   @Autowired
	   private Users users;

	    // 查询用户列表
	    @RequestMapping(value = "order-list")
	    public String list(OrderVo vo, ModelMap modelMap, HttpServletRequest request){
	    	
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	modelMap.put("userId", Integer.parseInt(sysAdminVo.getId()));
	    	if(LogicUtil.isNull(vo.getUserId())) {
	    		vo.setUserId(Integer.parseInt(sysAdminVo.getId()));
	    	}
	        PageData<OrderVo> pageParam = orderViewComp.pageQueryOrders(vo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("orderParam", vo);// 查询时传入的参数
	        modelMap.put("shops", shops.getOptions(Integer.parseInt(sysAdminVo.getId())));
	        modelMap.put("payStatus", OrderPayStatus.getOptions());
	        modelMap.put("yesno", YesNos.getOptions());
	        modelMap.put("orderStatus", OrderStatus.getOptions());
	        modelMap.put("users", users.getOptions());
	        
	        return "comment/order_list";
	    }
	    
	    @RequestMapping(value = "order-add")
	    public String orderAdd(ModelMap modelMap, HttpServletRequest request){
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	modelMap.put("shops", shops.getOptions(Integer.parseInt(sysAdminVo.getId())));
    	    modelMap.put("yesno", YesNos.getOptions());
	        modelMap.put("orderStatus", OrderStatus.getOptions());
	        modelMap.put("payStatus", OrderPayStatus.getOptions());
	        return "comment/order_add";
	    }
	    
	    
	    @RequestMapping(value = "order-add-submit")
	    @ResponseBody
	    public ResultPrompt orderAddSubmit(OrderVo orderVo, HttpServletRequest request){
	        
    		HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		    orderVo.setUserId(Integer.parseInt(sysAdminVo.getId()));
		    orderVo.setIsPay(EnumOrderPayStatus.NOCHECK.getValue());
		    ResultPrompt resultPrompt = orderViewComp.addOrder(orderVo);
		    
	        return resultPrompt;
	    }
	    
	    
	    @RequestMapping(value = "order-update")
	    public String orderUpdate(Integer orderId, ModelMap modelMap, HttpServletRequest request){
	    	HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	        OrderVo order = orderViewComp.queryOrderById(orderId, Integer.parseInt(sysAdminVo.getId()));
	        modelMap.put("order", order);
	        modelMap.put("shops", shops.getOptions(Integer.parseInt(sysAdminVo.getId())));
    	    modelMap.put("yesno", YesNos.getOptions());
	        modelMap.put("orderStatus", OrderStatus.getOptions());
	        modelMap.put("payStatus", OrderPayStatus.getOptions());
	        return "comment/order_update";
	    }
	    
	    @RequestMapping(value = "order-update-submit")
	    @ResponseBody
	    public ResultPrompt orderUpdateSubmit(OrderVo shopVo, HttpServletRequest request){
	        
		    ResultPrompt resultPrompt = orderViewComp.updateOrder(shopVo);
		    
	        return resultPrompt;
	    }
	    
	    @RequestMapping(value = "order-delete")
	    @ResponseBody
	    public ResultPrompt orderDelete(Integer orderId, HttpServletRequest request){
	        
    		HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		    ResultPrompt resultPrompt = orderViewComp.deleteOrder(orderId,Integer.parseInt(sysAdminVo.getId()));
		    
	        return resultPrompt;
	    }
	    
	    @RequestMapping(value = "order-update-isjoin")
	    @ResponseBody
	    public ResultPrompt orderUpdateIsjonin(Integer orderId,Integer isJoin, HttpServletRequest request){
	        
    		HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		    
		    if(isJoin ==null) {
		    	ResultPrompt rp = new ResultPrompt();
		    	rp.setStatusCode("300");
				rp.setMessage("操作失败！");
				rp.setNavTabId("comment/order-list"); // 要刷新的tab页id
		    	return rp;
		    }
		    OrderVo order = orderViewComp.queryOrderById(orderId, Integer.parseInt(sysAdminVo.getId()));
		    if(isJoin == 1) {
		    	order.setIsJoin(0);
		    } else {
		    	order.setIsJoin(1);
		    }
		    
		    ResultPrompt resultPrompt = orderViewComp.orderUpdateIsjonin(order);
		    
	        return resultPrompt;
	    }
	    
	    @RequestMapping(value = "order-batch-add")
	    public String orderBatchAdd(ModelMap modelMap){
	    	
	    	
	    	 modelMap.put("platforms", Platforms.getOptions());
	    	 return "comment/order-batch-add";
	    }
	    
	    @ResponseBody
	    @RequestMapping("get-shops")
	    public LinkedHashMap<Integer,String> getShops(Integer platform,HttpServletRequest request) {
	    	
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	
	    	LinkedHashMap<Integer,String> rp =  shops.getOptions(Integer.parseInt(sysAdminVo.getId()),platform);
	        return rp;
	    }
	    
	    @ResponseBody
	    @RequestMapping("order-upload")
	    public ResultPrompt uploadOrder(MultipartFile orderfile,HttpServletRequest request) {
	    	
	    	Integer shopId = Integer.parseInt(request.getParameter("shopId"));
	    	Integer platform = Integer.parseInt(request.getParameter("platform"));
	    	
	    	HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	ResultPrompt rp = orderViewComp.uploadOrder(orderfile,Integer.parseInt(sysAdminVo.getId()),shopId,platform);
	    	
	        return rp;
	    }
	    
}
