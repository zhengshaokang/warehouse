package com.hys.dal.component.user.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.user.ISysAuthDalComp;
import com.hys.dal.db.user.ISysAuthDao;
import com.hys.model.user.SysAuth;

@Component
public class SysAuthDalCompImpl implements ISysAuthDalComp{
	@Autowired
    private ISysAuthDao authDao;

	@Transactional
    @Override
    public String addSysAuth(SysAuth sysAuth)
    {
    	authDao.addSysAuth(sysAuth);
        return sysAuth.getId();
    }
    
    @Transactional
    @Override
    public boolean deleteSysAuth(String authId)
    {
        return JdbcUtil.isSuccess(authDao.deleteSysAuth(authId));
    }
    
    @Transactional
    @Override
    public boolean updateSysAuth(SysAuth sysAuth)
    {
        return JdbcUtil.isSuccess(authDao.updateSysAuth(sysAuth));
    }

    @Override
    public SysAuth querySysAuth(String authId)
    {
        return authDao.querySysAuth(authId);
    }

    @Transactional
    @Override
    public List<SysAuth> querySysAuths()
    {
        return authDao.querySysAuths();
    }
    
    @Transactional
    @Override
    public List<SysAuth> queryAuthsByUserId(String userId)
    {
        return authDao.queryAuthsByUserId(userId);
    }
    
    @Transactional
	@Override
	public PageData<SysAuth> pageQuerySysAuth(PageParam<SysAuth> page) {
	        List<SysAuth> users = authDao.PageQuerySysAuth(page);
	        return new PageData<SysAuth>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), users);
	}


}
