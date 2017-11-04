package com.hys.mgt.view.user.component.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.mgt.view.user.component.ISysRoleViewComp;
import com.hys.model.user.SysAuth;
import com.hys.model.user.SysRole;
import com.hys.model.user.SysUser;
import com.hys.service.user.component.ISysRoleService;

/**
 * @author: 
 */

@Component
public class SysRoleViewCompImpl implements ISysRoleViewComp
{

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public String addSysRole(SysRole sysRole)
    {
        return sysRoleService.addSysRole(sysRole);
    }

    @Override
    public boolean updateSysRole(SysRole sysRole)
    {
        return sysRoleService.updateSysRole(sysRole);
    }

    @Override
    public boolean updateUserSysRole(String userId, List<String> addRoleIds, List<String> delRoleIds)
    {
        return sysRoleService.updateUserSysRole(userId, addRoleIds, delRoleIds);
    }

    @Override
    public boolean updateSysRoleAuth(String roleId, List<String> addAuthIds, List<String> delAuthIds)
    {
        return sysRoleService.updateSysRoleAuth(roleId, addAuthIds, delAuthIds);
    }

    @Override
    public PageData<SysRole> querySysRoles(PageParam< SysRole> page)
    {
        return sysRoleService.querySysRoles(page);
    }

    @Override
    public List<SysUser> queryUsersByRoleId(String roleId)
    {
        return null;// roleService.queryAuthsByUserId(roleId);
    }

    @Override
    public List<SysRole> queryRolesByUserId(String userId)
    {
        return sysRoleService.queryRolesByUserId(userId);
    }

    @Override
    public List<SysRole> queryRolesByAuthId(String authId)
    {
        return sysRoleService.queryRolesByAuthId(authId);
    }

    @Override
    public List<SysAuth> queryAuthsByRoleId(String roleId)
    {
        return sysRoleService.queryAuthsByRoleId(roleId);
    }

}
