package com.hys.mgt.view.user.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.component.IRoleViewComp;
import com.hys.mgt.view.user.vo.SysRoleVo;
import com.hys.model.user.SysRole;



	/*
	 * @author: chenjunxiu
	 * @date 2014年7月29日 下午2:02:40
	 */
@Controller
@RequestMapping(value = "/role/")
public class RoleController
{

    @Autowired
    private IRoleViewComp roleViewComp;

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ResponseBody
    public PageData<SysRole> list(SysRoleVo vo, Integer pageNo)
    {
        if (null == pageNo)
        {
            pageNo = 1;
        }
        return roleViewComp.querySysRoles(vo, pageNo);
    }

    @RequestMapping(value = "rolelist", method = RequestMethod.GET)
    @ResponseBody
    public PageData<SysRole> list(SysRoleVo vo)
    {
        vo.setPageSize(Integer.MAX_VALUE);
        return roleViewComp.querySysRoles(vo, 1);
    }

    @RequestMapping(value = "toList", method = RequestMethod.GET)
    public String list(SysRoleVo vo, Model model)
    {
        vo.setPageSize(Integer.MAX_VALUE);
        PageData<SysRole> param = roleViewComp.querySysRoles(vo, 1);
        model.addAttribute("roleListParamList", param);

        return "role/role_list";
    }

    @RequestMapping(value = "roleAdd", method = RequestMethod.GET)
    public String roleAdd(Model model)
    {
        roleViewComp.queryAll(model);
        return "role/role_add";
    }

    @RequestMapping(value = "roleAdd", method = RequestMethod.POST)
    @ResponseBody
    public ResultPrompt roleAdd(SysRoleVo vo, HttpServletRequest request)
    {
        // 新增的时候状态为正常
        vo.setRoleStatus(1);
        String id = roleViewComp.roleAdd(vo);
        vo.setId(id);
        String[] addAuthId = request.getParameterValues("addAuthId");

        ResultPrompt resultPrompt = roleViewComp.roleUpdate(vo, addAuthId, null);

        return resultPrompt;
    }

    @RequestMapping(value = "roleUpdate",method = RequestMethod.GET)
    public String roleUpdate(String roleId, Model model)
    {
        SysRoleVo vo = roleViewComp.queryRoleById(roleId);
        roleViewComp.queryAll(model);
        model.addAttribute("roleupdateVo", vo);

        return "role/role_update";
    }

    @RequestMapping(value = "roleUpdateSubmit", method = RequestMethod.POST)
    @ResponseBody
    public ResultPrompt roleUpdate(SysRoleVo vo, HttpServletRequest request)
    {
        String[] addAuthId = request.getParameterValues("addAuthId");
        String[] delAuthId = request.getParameterValues("delAuthId");
        ResultPrompt resultPrompt = roleViewComp.roleUpdate(vo, addAuthId, delAuthId);
        return resultPrompt;
    }

    @RequestMapping(value = "getRoleByUserId", method = RequestMethod.GET)
    @ResponseBody
    public List<SysRole> queryRolesByUserId(String userId)
    {
        return roleViewComp.queryRolesByUserId(userId);
    }

    @RequestMapping(value = "roleDetail", method = RequestMethod.GET)
    public String roleDetail(String roleId, Model model)
    {
        SysRoleVo vo = roleViewComp.queryRoleById(roleId);
        roleViewComp.queryAll(model);
        model.addAttribute("roleupdateVo", vo);
        return "role/role_detail";
    }

    @RequestMapping(value = "roledelete")
    @ResponseBody
    public ResultPrompt roleDelete(String roleId, Model model)
    {
        ResultPrompt resultPrompt = roleViewComp.roleDelete(roleId);
        return resultPrompt;
    }
}