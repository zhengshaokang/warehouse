package com.hys.mgt.view.comment.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.conf.ProfileManager;
import com.hys.commons.page.PageData;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.Users;
import com.hys.mgt.view.comment.component.IActivViewComp;
import com.hys.mgt.view.comment.vo.ActivVo;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/comment/")
public class ActivController {
	   @Autowired
	    private IActivViewComp activViewComp;
	   @Autowired
	   private Users users;

	    @RequestMapping(value = "activ-list")
	    public String list(ActivVo activVo, ModelMap modelMap, HttpServletRequest request){
	    	
	    	HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	modelMap.put("userId", Integer.parseInt(sysAdminVo.getId()));
	    	if(LogicUtil.isNull(activVo.getUserId())) {
	    		activVo.setUserId(Integer.parseInt(sysAdminVo.getId()));
	    	}
	    	
	        PageData<ActivVo> pageParam = activViewComp.pageQueryActivs(activVo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("activParam", activVo);// 查询时传入的参数
	        String doMain = ProfileManager.getStringByKey("hys_webapp.wx_domain_url", "");
	        modelMap.put("wxDoMain", doMain);
	        modelMap.put("users", users.getOptions());
	        return "comment/activ_list";
	    }
	    
	    
	    @RequestMapping(value = "activ-add")
	    public String activAdd(ModelMap modelMap, HttpServletRequest request){
	        
	        
	        return "comment/activ_add";
	    }
	    
	    @RequestMapping(value = "activ-update")
	    public String activUpdate(Integer activId, ModelMap modelMap, HttpServletRequest request){
	    	HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	        ActivVo activ = activViewComp.queryActivById(activId, Integer.parseInt(sysAdminVo.getId()));
	        modelMap.put("activ", activ);
	        return "comment/activ_update";
	    }
	    
	    @RequestMapping(value = "activ-add-submit")
	    @ResponseBody
	    public ResultPrompt activAddSubmit(ActivVo activVo, HttpServletRequest request){
	        
    		HttpSession session = SessionUtils.getSession();
		    SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
		    activVo.setCreateTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
		    activVo.setUserId(Integer.parseInt(sysAdminVo.getId()));
		    
		    ResultPrompt resultPrompt = activViewComp.addActiv(activVo);
		    
	        return resultPrompt;
	    }
	    
	    @RequestMapping(value = "activ-update-submit")
	    @ResponseBody
	    public ResultPrompt activUpdateSubmit(ActivVo activVo, HttpServletRequest request){
	        
		    ResultPrompt resultPrompt = activViewComp.updateActiv(activVo);
		    
	        return resultPrompt;
	    }
	    
}
