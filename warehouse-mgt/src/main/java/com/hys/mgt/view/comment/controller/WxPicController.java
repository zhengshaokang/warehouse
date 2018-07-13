package com.hys.mgt.view.comment.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hys.commons.page.PageData;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.Users;
import com.hys.mgt.view.comment.component.IWxPicViewComp;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/comment/")
public class WxPicController {
	   @Autowired
	   private IWxPicViewComp wxPicViewComp;
	   @Autowired
	   private Users users;

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
	        
	        modelMap.put("users", users.getOptions());
	        return "comment/wx_pic_list";
	    }
}
