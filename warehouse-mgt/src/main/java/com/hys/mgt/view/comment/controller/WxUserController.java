package com.hys.mgt.view.comment.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hys.commons.page.PageData;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.Sexs;
import com.hys.dal.select.Users;
import com.hys.mgt.view.comment.component.IWxUserViewComp;
import com.hys.mgt.view.comment.vo.WxUserVo;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/comment/")
public class WxUserController {
	   @Autowired
	   private IWxUserViewComp wxUserViewComp;
	   @Autowired
	   private Users users;

	    @RequestMapping(value = "wxuser-list")
	    public String list(WxUserVo wxUserVo, ModelMap modelMap, HttpServletRequest request){
	    	
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	modelMap.put("userId", Integer.parseInt(sysAdminVo.getId()));
	    	if(LogicUtil.isNull(wxUserVo.getUserId())) {
	    		wxUserVo.setUserId(Integer.parseInt(sysAdminVo.getId()));
	    	}
	    	
	        PageData<WxUserVo> pageParam = wxUserViewComp.pageQueryWxUsers(wxUserVo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("wxUserParam", wxUserVo);// 查询时传入的参数
	        
	        modelMap.put("users", users.getOptions());
	        
	        modelMap.put("sexs", Sexs.getOptions());
	        
	        return "comment/wx_user_list";
	    }
}
