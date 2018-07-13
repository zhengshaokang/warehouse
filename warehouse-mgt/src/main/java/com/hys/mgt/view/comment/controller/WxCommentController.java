package com.hys.mgt.view.comment.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.component.IWxPicViewComp;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.common.utils.IPUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.component.ISysUserViewComp;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/")
public class WxCommentController {
	
		@Autowired
		private ISysUserViewComp sysUserViewComp;
		@Autowired
		private IWxPicViewComp wxPicViewComp;

	    @RequestMapping(value = "wxpicadd")
	    public String list(String userId, ModelMap modelMap, HttpServletRequest request){
	    	
	    	SysUserVo sysAdminVo = sysUserViewComp.queryUserById(userId);
	    	if(LogicUtil.isNotNull(sysAdminVo) && LogicUtil.isNotNullAndEmpty(sysAdminVo.getCommentUrl())){
	    		modelMap.put("templet", sysAdminVo.getCommentUrl());
	    	} else {
	    		modelMap.put("templet", "templet0.jpg");
	    	}
	    	modelMap.put("userId", userId);
	    	
	        return "comment/wx_pic_add";
	    }
	    
	    @ResponseBody
	    @RequestMapping("wxaddpicsubmit")
	    public ResultPrompt wxaddpicsubmit(WxPicVo wxPicVo,HttpServletRequest request) {
	    	
	    	wxPicVo.setUploadIp(IPUtils.getIpAddr(request));
	    	wxPicVo.setUploadTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
	    	ResultPrompt ResultPrompt = wxPicViewComp.addOrUpdateWxPic(wxPicVo);
	        return ResultPrompt;
	    }
}
