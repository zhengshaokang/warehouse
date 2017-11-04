package com.hys.mgt.view.user.component.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.hys.commons.crypto.MD5Coding;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.user.common.SysUserConverter;
import com.hys.mgt.view.user.component.ILoginViewComp;
import com.hys.mgt.view.user.vo.SysUserVo;
import com.hys.model.user.SysAuth;
import com.hys.model.user.SysRole;
import com.hys.model.user.SysUser;
import com.hys.service.user.component.ISyAuthService;
import com.hys.service.user.component.ISysRoleService;

/**
 * 登录view实现
 */
@Service
public class LoginViewCompImpl implements ILoginViewComp

{
    @Autowired
    private ISysRoleService sysRoleService;
    @Autowired
    private ISyAuthService sysAuthService;
    
    @Override
    public String login(Model model, SysUserVo sysAdminVo)
    {
        if (LogicUtil.isNullOrEmpty(sysAdminVo.getLoginname()) || LogicUtil.isNullOrEmpty(sysAdminVo.getPassword())) {
            return "帐号或密码不能为空";
        }

        // 查询user，判断帐号密码
        System.out.println("密码："+MD5Coding.encode2HexStr(sysAdminVo.getPassword().getBytes()));
        sysAdminVo.setPassword(MD5Coding.encode2HexStr(sysAdminVo.getPassword().getBytes()));
        SysUser sysAdmin = sysAuthService.querySysUserByUsernamePwd(sysAdminVo.getLoginname(),
                sysAdminVo.getPassword());
        sysAdminVo =SysUserConverter.convert2Vo(sysAdmin);
        if (LogicUtil.isNull(sysAdminVo)) {
            return "帐号或密码错误";
        }
        // 根据user查询角色
        List<SysRole> roleIds = sysRoleService.queryRolesByUserId(sysAdminVo.getId());

        if (LogicUtil.isNullOrEmpty(roleIds)) {
            return "帐号不存在";
        }
        // 根据角色查询菜单
        List<SysAuth> auths = new ArrayList<>();
        for (SysRole role : roleIds) {
            auths.addAll(sysRoleService.queryAuthsByRoleId(role.getId()));
        }
        sysAdminVo.setAuths(auths);
        sysAdminVo.setRole(roleIds);

        HttpSession session = SessionUtils.getSession();
        session.setAttribute("sysadmin", sysAdminVo);
        // session.setMaxInactiveInterval(5);//测试拦截器

        List<SysAuth> authList = new ArrayList<SysAuth>();
        List<SysAuth> authListSub = new ArrayList<SysAuth>();
        if (LogicUtil.isNotNullAndEmpty(auths)) {
            for (SysAuth sysAuth : auths) {
                if ("0".equals(sysAuth.getParentId())){
                    authList.add(sysAuth);
                } else {
                    authListSub.add(sysAuth);
                }
            }
        }

        session.setAttribute("authListIndex", authList);
        session.setAttribute("authListSubIndex", authListSub);
        return null;
    }
}
