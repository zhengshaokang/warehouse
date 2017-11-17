package com.hys.mgt.view.user.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hys.commons.util.LogicUtil;
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
        if (LogicUtil.isNotNull(sysAdminVo))
        {
            return "redirect:/index";
        }
        else
        {
        	model.addAttribute("loginerror", null);
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

    @RequestMapping(value = "loginSubmit")
    public String loginSubmit(Model model, SysUserVo sysAdminVo)
    {
        String error = loginViewComp.login(model, sysAdminVo);
        if (LogicUtil.isNotNull(error))
        {
            model.addAttribute("loginerror", error);
            return "login";
        }

        return "redirect:/index";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpServletResponse response)
    {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("sysadmin", null);

        return "redirect:/login";
    }

}
