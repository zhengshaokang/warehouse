package com.hys.mgt.view.comment.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.crypto.Base64Ext;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.component.IActivViewComp;
import com.hys.mgt.view.comment.component.IWxPicViewComp;
import com.hys.mgt.view.comment.vo.ActivVo;
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
		@Autowired
		private IActivViewComp activViewComp;

	    @RequestMapping(value = "wxpicadd")
	    public String list(String activ, ModelMap modelMap, HttpServletRequest request){
	    	try {
				String decodeStr = new String(Base64Ext.decode(activ),"UTF-8");
				String[] decodeArr = decodeStr.split("\\|");
				ActivVo ac = activViewComp.queryActivByCode(decodeArr[1], Integer.parseInt(decodeArr[0]));
				modelMap.put("activ", ac);
				modelMap.put("userId", decodeArr[0]);
				modelMap.put("error", 0);
			} catch (Exception e) {
				modelMap.put("error", 1);
				e.printStackTrace();
			}
	    	
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
