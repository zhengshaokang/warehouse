package com.hys.dal.component.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.user.ISysRoleDalComp;
import com.hys.dal.db.user.ISysAuthDao;
import com.hys.dal.db.user.ISysRoleDao;
import com.hys.dal.db.user.ISysUserDao;
import com.hys.model.user.SysAuth;
import com.hys.model.user.SysRole;

@Component
public class SysRoleDalCompImpl implements ISysRoleDalComp{
	    @Autowired
	    private ISysRoleDao sysRoleDao;
	    
	    @Autowired
	    private ISysUserDao sysUserDao;
	    
	    @Autowired
	    private ISysAuthDao sysAuthDao;
	    
	    @Override
	    public String addSysRole(SysRole sysRole)
	    {
	        sysRoleDao.addSysRole(sysRole);
	        return sysRole.getId();
	    }
	    @Override
	    public boolean updateSysRole(SysRole sysRole)
	    {
	        return JdbcUtil.isSuccess(sysRoleDao.updateSysRole(sysRole));
	    }

	    @Transactional
	    @Override
	    public boolean updateUserSysRole(String userId, List<String> addRoleIds, List<String> delRoleIds)
	    {
	        if (LogicUtil.isNotNullAndEmpty(delRoleIds))
	        {
	            for (String roleId : delRoleIds)
	            {
	            	sysUserDao.deleteSysRoleUser(roleId, userId);
	            }
	        }
	        if (LogicUtil.isNotNullAndEmpty(addRoleIds))
	        {
	            for (String roleId : addRoleIds)
	            {
	            	sysUserDao.addSysRoleUser(roleId, userId);
	            }
	        }
	        return true;
	    }

	    @Transactional
	    @Override
	    public boolean updateSysRoleAuth(String roleId, List<String> addAuthIds, List<String> delAuthIds)
	    {

	        if (LogicUtil.isNotNullAndEmpty(delAuthIds))
	        {
	            for (String authId : delAuthIds)
	            {
	                sysRoleDao.deleteSysRoleAuth(roleId, authId);
	            }
	        }
	        if (LogicUtil.isNotNullAndEmpty(addAuthIds))
	        {
	            for (String authId : addAuthIds)
	            {
	                sysRoleDao.addSysRoleAuth(roleId, authId);
	            }
	        }
	        return true;
	    }

	    @Override
	    public PageData<SysRole> querySysRoles(PageParam<SysRole> page)
	    {
	        if (LogicUtil.isNull(page))
	        {
	            return null;
	        }
	        // 设置角色名称查询变量
	        if (LogicUtil.isNotNull(page.getP()))
	        {
	            String name = page.getP().getRoleName();
	            if (LogicUtil.isNotNull(name))
	            {
	                page.getP().setRoleName("'%" + name + "%'");
	            }
	        }
	        List<SysRole> data = sysRoleDao.querySysRoles(page);
	        return new PageData<SysRole>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), data);
	    }
	    @Override
	    public List<SysRole> queryRolesByUserId(String userId)
	    {
	        return sysRoleDao.queryRolesByUserId(userId);
	    }

	    @Override
	    public List<SysRole> queryRolesByAuthId(String authId)
	    {
	        return sysRoleDao.queryRolesByAuthId(authId);
	    }

	    @Override
	    public List<SysAuth> queryAuthsByRoleId(String roleId)
	    {
	        return sysAuthDao.queryAuthsByRoleId(roleId);
	    }

	    @Override
	    public Map<String, List<SysRole>> queryRolesByUserIds(List<String> userIds)
	    {
	        if (LogicUtil.isNotNullAndEmpty(userIds))
	        {
	            Map<String, List<SysRole>> urMap = new HashMap<String, List<SysRole>>();
	            for (String userId : userIds)
	            {
	                List<SysRole> list = sysRoleDao.queryRolesByUserId(userId);
	                if (LogicUtil.isNotNullAndEmpty(list))
	                {
	                    urMap.put(userId, list);
	                }
	            }
	            return urMap;
	        }
	        return null;
	    }
}
