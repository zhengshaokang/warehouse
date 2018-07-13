package com.hys.mgt.view.user.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.utils.Agent;
import com.hys.mgt.view.common.utils.IPUtils;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.user.vo.SysUserVo;
import com.hys.mgt.view.user.component.ILoginViewComp;

@Controller
@RequestMapping(value ="/")
// 登陆不需要模块，避免出现两个login
public class LoginController
{

    @Autowired
    private ILoginViewComp loginViewComp;
    
    @Autowired
    private Agent agent;
    
    @RequestMapping(value = "/")
    public String login1(Model model)
    {
    	return "redirect:/login";
    }
    
    @RequestMapping(value = "/login")
    public String login(Model model)
    {
        HttpSession session = SessionUtils.getSession();
        SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
        Integer systmeType = (Integer) session.getAttribute("systmeType");
        if (LogicUtil.isNotNull(sysAdminVo)){
        	if(systmeType == 0) { //评价系统
        		
        		return "redirect:/index_c";
        	} else { //如果是仓库系统
        		if("M".equals(agent.getAgent())) {
            		return "redirect:/product/list";
            	} else {
            		 return "redirect:/index";
            	}
        	}
        }else {
        	model.addAttribute("loginerror", null);
        	model.addAttribute("agent", agent.getAgent());
            return "login";
        }
    }
    
    @RequestMapping(value = "index")
    public String index(Model model)
    {
    	HttpSession session = SessionUtils.getSession();
    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
        if (LogicUtil.isNotNull(sysAdminVo))
        {
            model.addAttribute(sysAdminVo);
        }
        return "index";
    }
    
    @RequestMapping(value = "index_c")
    public String index_c(Model model)
    {
    	HttpSession session = SessionUtils.getSession();
    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
        if (LogicUtil.isNotNull(sysAdminVo))
        {
            model.addAttribute(sysAdminVo);
        }
        return "index_c";
    }

    @RequestMapping(value = "loginSubmit")
    public String loginSubmit(Model model, SysUserVo sysAdminVo,HttpServletRequest request)
    {
    	String userIp = IPUtils.getIpAddr(request);
    	sysAdminVo.setLoginIp(userIp);
    	
    	Integer systmeType = sysAdminVo.getSystemType();
    	HttpSession session = SessionUtils.getSession();
        String error = loginViewComp.login(model, sysAdminVo);
        if (LogicUtil.isNotNull(error))
        {
            model.addAttribute("loginerror", error);
            return "login";
        }
        if(systmeType == 2) {
        	session.setAttribute("systmeType", 0);
        	return "redirect:/index_c";
        } else {
        	session.setAttribute("systmeType", 1);
        	if("M".equals(agent.getAgent())) {
        		return "redirect:/product/list";
        	} else {
        		 return "redirect:/index";
        	}
        }
        
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletResponse response)
    {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("sysadmin", null);
        session.setAttribute("systmeType", null);
        return "redirect:/login";
    }
}
