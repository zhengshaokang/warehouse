package com.hys.service.user.component.impl;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.user.ISysAuthDalComp;
import com.hys.dal.component.user.ISysRoleDalComp;
import com.hys.model.user.SysAuth;
import com.hys.model.user.SysRole;
import com.hys.service.user.component.ISysRoleService;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

	 private final static Logger log = LogProxy.getLogger(SysRoleServiceImpl.class);
	 
	    @Autowired
	    private ISysRoleDalComp sysRoleDalComp;
	    @Autowired
	    private ISysAuthDalComp sysAuthDalComp;
	    @Override
	    public String addSysRole(SysRole sysRole)
	    {
	        log.debug("parameter sysRole is=" + sysRole);
	        if (LogicUtil.isNull(sysRole))
	        {
	            return null;
	        }
	        return sysRoleDalComp.addSysRole(sysRole);
	    }

	    @Override
	    public boolean updateSysRole(SysRole sysRole)
	    {
	        log.debug("parameter sysrole is=" + sysRole);
	        if (LogicUtil.isNull(sysRole))
	        {
	            return false;
	        }
	        return sysRoleDalComp.updateSysRole(sysRole);
	    }

	    @Override
	    public boolean updateUserSysRole(String userId, List<String> addRoleIds, List<String> delRoleIds)
	    {
	        log.debug("parameter userId is=" + userId + " addRoleIds is=" + addRoleIds + " delRoleIds is=" + delRoleIds);
	        if (LogicUtil.isNullOrEmpty(userId))
	        {
	            return false;
	        }

	        return sysRoleDalComp.updateUserSysRole(userId, addRoleIds, delRoleIds);
	    }

	    @Override
	    public boolean updateSysRoleAuth(String roleId, List<String> addAuthIds, List<String> delAuthIds)
	    {
	        log.debug("parameter roleId is=" + roleId + " addAuthIds is=" + addAuthIds + " delAuthIds is=" + delAuthIds);
	        if (LogicUtil.isNullOrEmpty(roleId))
	        {
	            return false;
	        }

	        return sysRoleDalComp.updateSysRoleAuth(roleId, addAuthIds, delAuthIds);
	    }

	    @Override
	    public PageData<SysRole> querySysRoles(PageParam<SysRole> page)
	    {
	        log.debug("parameter page is=" + page);
	        if (LogicUtil.isNull(page))
	        {
	            return null;
	        }

	        return sysRoleDalComp.querySysRoles(page);
	    }

	    @Override
	    public List<SysRole> queryRolesByUserId(String userId)
	    {
	        log.debug("parameter userId is=" +userId);
	        if (LogicUtil.isNullOrEmpty(userId))
	        {
	            return null;
	        }

	        return sysRoleDalComp.queryRolesByUserId(userId);
	    }

	    @Override
	    public List<SysRole> queryRolesByAuthId(String authId)
	    {
	        log.debug("parameter authId is=" + authId);
	        if (LogicUtil.isNullOrEmpty(authId))
	        {
	            return null;
	        }

	        return sysRoleDalComp.queryRolesByAuthId(authId);
	    }

	    @Override
	    public List<SysAuth> queryAuthsByRoleId(String roleId)
	    {
	        log.debug("parameter roleId is=" + roleId);
	        if (LogicUtil.isNullOrEmpty(roleId))
	        {
	            return null;
	        }

	        return sysRoleDalComp.queryAuthsByRoleId(roleId);
	    }

	    @Override
	    public Map<String, List<SysRole>> queryRolesByUserIds(List<String> UserIds)
	    {
	        log.debug("parameter UserIds is=" + UserIds);
	        if (LogicUtil.isNullOrEmpty(UserIds))
	        {
	            return null;
	        }

	        return sysRoleDalComp.queryRolesByUserIds(UserIds);
	    }

		
	    @Override
	    public List<SysAuth> querySysAuths()
	    {
	        log.debug("query all sys auths");
	        return sysAuthDalComp.querySysAuths();
	    }

}
