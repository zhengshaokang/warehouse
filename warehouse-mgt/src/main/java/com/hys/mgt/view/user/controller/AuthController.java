package com.hys.mgt.view.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.dal.select.Status;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.component.IAuthViewComp;
import com.hys.mgt.view.user.vo.SysAuthVo;
import com.hys.model.user.SysAuth;

@Controller
@RequestMapping("/auth/")
public class AuthController
{
	private final static Logger log = LogProxy.getLogger(AuthController.class);
    @Autowired
    private IAuthViewComp authViewComp;
    
    @RequestMapping(value = "allList", method = RequestMethod.GET)
    @ResponseBody
    public List<SysAuth> allList()
    {
        return authViewComp.queryAll();
    }
    
    @RequestMapping(value ="list")
    public String  authList(SysAuthVo vo, ModelMap modelMap, HttpServletRequest request)
    {
    	PageData<SysAuthVo> pageParam=authViewComp.pageQuerySysAuth(vo);
        modelMap.put("pageParam", pageParam);
        modelMap.put("authParam", vo);// 查询时传入的参数
        modelMap.put("status", Status.getOptions());
        return "auth/auth_list";
    }
    
    @RequestMapping(value="/authAdd")
    public String authAdd()
    {
    	 return "auth/auth_add";
    }
    
    @ResponseBody
    @RequestMapping(value="authAddSubmit", method=RequestMethod.POST)
    public ResultPrompt authAddSubmit(SysAuthVo vo, HttpServletRequest reqest)
    {
        ResultPrompt resultPrompt =authViewComp.addAuth(vo);
        return resultPrompt;
    }
    
    @RequestMapping(value = "authUpdate")
    public String userUpdate(String id, ModelMap modelMap)
    {
    	authViewComp.QuerySysAuth(id,modelMap);
        return "auth/auth_update";
    }

    @ResponseBody
    @RequestMapping(value = "authUpdateSubmit", method = RequestMethod.POST)
    public ResultPrompt userUpdateSubmit(SysAuthVo vo, HttpServletRequest request)
    {
        ResultPrompt resultPrompt = authViewComp.updateAuth(vo);
        return resultPrompt;
    }
  
    @ResponseBody
    @RequestMapping(value = "authDelete")
    public ResultPrompt authDelete(String id)
    {
        ResultPrompt resultPrompt = authViewComp.authDel(id);
        return resultPrompt;
    }


}
