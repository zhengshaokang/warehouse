package com.hys.mgt.view.user.component.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.utils.BeanUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.component.IRoleViewComp;
import com.hys.mgt.view.user.vo.SysRoleVo;
import com.hys.model.user.SysAuth;
import com.hys.model.user.SysRole;
import com.hys.service.user.component.ISysRoleService;

@Component
public class RoleViewCompImpl implements IRoleViewComp
{
    @Autowired
    private ISysRoleService sysRoleService;
  
    @Override
    public void queryAll(Model model)
    {
        //List<SysAuth> auths = sysAuthService.querySysAuths();
    	List<SysAuth> auths =sysRoleService.querySysAuths();
        List<SysAuth> authList = new ArrayList<SysAuth>();
        List<SysAuth> authListSub = new ArrayList<SysAuth>();
        if (LogicUtil.isNotNullAndEmpty(auths))
        {
            for (SysAuth sysAuth : auths)
            {
                if ("0".equals(sysAuth.getParentId()))
                {
                    authList.add(sysAuth);
                }
                else
                {
                    authListSub.add(sysAuth);
                }
            }
        }
        model.addAttribute("roleAuthList", authList);
        model.addAttribute("roleAuthListSub", authListSub);
    }
    @Override
    public String roleAdd(SysRoleVo vo)
    {
        SysRole sysRole = BeanUtils.copyVoToModel(SysRole.class, vo);
        return sysRoleService.addSysRole(sysRole);
    }

    @Override
    public ResultPrompt roleUpdate(SysRoleVo vo, String[] addAuthId, String[] delAuthId)
    {
        List<String> addAuthIds = new ArrayList<String>();
        List<String> delAuthIds = new ArrayList<String>();
        if (LogicUtil.isNotNull(addAuthId))
        {
            addAuthIds = Arrays.asList(addAuthId);
        }
        if (LogicUtil.isNotNull(delAuthId))
        {
            delAuthIds = Arrays.asList(delAuthId);
        }
        ResultPrompt resultPrompt = new ResultPrompt();

        SysRole sysRole = new SysRole();
        sysRole.setId(vo.getId());
        sysRole.setRoleName(vo.getRoleName());
        sysRole.setRoleStatus(vo.getRoleStatus());
        sysRoleService.updateSysRole(sysRole);

        if (sysRoleService.updateSysRoleAuth(vo.getId(), addAuthIds, delAuthIds))
        {
            resultPrompt.setStatusCode("200");
            resultPrompt.setMessage("操作成功！");
            resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
            resultPrompt.setNavTabId("role/toList"); // 要刷新的tab页id
        }
        else
        {
            resultPrompt.setStatusCode("300");
            resultPrompt.setMessage("操作失败！");
            resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
            resultPrompt.setNavTabId("role/toList"); // 要刷新的tab页id
        }

        return resultPrompt;
    }

    @Override
    public PageData<SysRole> querySysRoles(SysRoleVo vo, int pageNo)
    {
        SysRole sysRole = BeanUtils.copyVoToModel(SysRole.class, vo);
        PageParam<SysRole> page = new PageParam<SysRole>();
        page.setP(sysRole);
        page.setPageNo(pageNo);
        page.setPageSize(vo.getPageSize());
        return sysRoleService.querySysRoles(page);
    }

    @Override
    public SysRoleVo queryRoleById(String id)
    {
        SysRole sysRole = new SysRole();
        sysRole.setId(id);
        PageParam<SysRole> page = new PageParam<SysRole>();
        page.setP(sysRole);
        page.setPageNo(1);
        PageData<SysRole> pd = sysRoleService.querySysRoles(page);

        if (pd.getDataTotal() > 0)
        {
            sysRole = pd.getPageData().get(0);
        }
        SysRoleVo vo = BeanUtils.copyModelToVo(SysRoleVo.class, sysRole);

        List<SysAuth> list = sysRoleService.queryAuthsByRoleId(vo.getId());
        vo.setAuths(list);
        return vo;
    }

    @Override
    public List<SysRole> queryRolesByUserId(String userId)
    {
        return sysRoleService.queryRolesByUserId(userId);
    }

    @Override
    public boolean updateUserSysRole(String userId, List<String> addRoleIds, List<String> delRoleIds)
    {

        return sysRoleService.updateUserSysRole(userId, addRoleIds, delRoleIds);
    }

    @Override
    public ResultPrompt roleDelete(String roleId)
    {
        SysRole sysRole = new SysRole();
        sysRole.setId(roleId);
        sysRole.setRoleStatus(1);
        boolean bb = sysRoleService.updateSysRole(sysRole);
        ResultPrompt resultPrompt = new ResultPrompt();
        if (bb)
        {
            resultPrompt.setStatusCode("200");
            resultPrompt.setMessage("操作成功！");
            resultPrompt.setCallbackType(""); // 关闭当前窗口
            resultPrompt.setNavTabId("role/toList"); // 要刷新的tab页id
        }
        else
        {
            resultPrompt.setStatusCode("300");
            resultPrompt.setMessage("操作失败！");
            resultPrompt.setCallbackType(""); // 关闭当前窗口
            resultPrompt.setNavTabId("role/toList"); // 要刷新的tab页id
        }
        return resultPrompt;
    }

}
