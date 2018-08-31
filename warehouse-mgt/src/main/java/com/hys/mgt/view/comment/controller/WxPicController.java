package com.hys.mgt.view.comment.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.page.PageData;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.OrderPayStatus;
import com.hys.dal.select.Shops;
import com.hys.dal.select.Users;
import com.hys.dal.select.YesNos;
import com.hys.dal.select.conenum.EnumOrderPayStatus;
import com.hys.mgt.view.comment.component.IWxPicViewComp;
import com.hys.mgt.view.comment.vo.OrderVo;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/comment/")
public class WxPicController {
	   @Autowired
	   private IWxPicViewComp wxPicViewComp;
	   @Autowired
	   private Users users;
	   @Autowired 
	   private Shops shops;

	    @RequestMapping(value = "wxpic-list")
	    public String list(WxPicVo wxPicVo, ModelMap modelMap, HttpServletRequest request){
	    	
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	modelMap.put("userId", Integer.parseInt(sysAdminVo.getId()));
	    	if(LogicUtil.isNull(wxPicVo.getUserId())) {
	    		wxPicVo.setUserId(Integer.parseInt(sysAdminVo.getId()));
	    	}
	    	
	        PageData<WxPicVo> pageParam = wxPicViewComp.pageQueryWxPics(wxPicVo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("wxPicParam", wxPicVo);// 查询时传入的参数
	        modelMap.put("payStatus", OrderPayStatus.getOptions());
	        modelMap.put("users", users.getOptions());
	        modelMap.put("yesno", YesNos.getOptions());
	        modelMap.put("shops", shops.getOptions(Integer.parseInt(sysAdminVo.getId())));
	        return "comment/wx_pic_list";
	    }
	    
	    @ResponseBody
	    @RequestMapping(value = "validateOrder")
	    public OrderVo validateOrder(String orderNo, ModelMap modelMap, HttpServletRequest request){
	    	
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	
	    	return wxPicViewComp.validateOrder(orderNo, Integer.parseInt(sysAdminVo.getId()));
	    } 
	    
	    
	    
	    @RequestMapping(value = "openWxpicCheck")
	    public String openWxpicCheck(Integer wxPicId, ModelMap modelMap, HttpServletRequest request){
	    	
	        modelMap.put("wxPicId", wxPicId);
	        return "comment/wx_pic_censor";
	    }
	    
	    @ResponseBody
	    @RequestMapping(value = "wxpicCheck")
	    public ResultPrompt wxpicCheck(Integer wxPicId, String checkReason,Integer picSorStatus,Integer sendMassge,ModelMap modelMap, HttpServletRequest request){
	    	
	    	return wxPicViewComp.updateOrderPayStatus(wxPicId, picSorStatus ,checkReason,sendMassge);
	    } 
	    
//	    @ResponseBody
//	    @RequestMapping(value = "payordercomment")
//	    public ResultPrompt payordercomment(Integer wxPicId,ModelMap modelMap, HttpServletRequest request){
//	    	String reason = "审核通过";
//	    	return wxPicViewComp.updateOrderPayStatus(wxPicId, EnumOrderPayStatus.YESPAY.getValue(),reason);
//	    } 
//	    
//	    @ResponseBody
//	    @RequestMapping(value = "payorderpass")
//	    public ResultPrompt payorderpass(Integer wxPicId, String reason,ModelMap modelMap, HttpServletRequest request){
//	    	
//	    	return wxPicViewComp.updateOrderPayStatus(wxPicId, EnumOrderPayStatus.NOPASS.getValue(),reason);
//	    } 
	    
}
