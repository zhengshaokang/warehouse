package com.hys.dal.component.user.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.user.ISysUserDalComp;
import com.hys.dal.db.user.ISysUserDao;
import com.hys.model.user.SysUser;

@Component
public class SysUserDalCompImpl implements ISysUserDalComp {
    
	@Autowired
	private ISysUserDao userDao;
	@Override
    @Transactional
    public String addSysUser(SysUser SysUser)
    {
		userDao.addSysUser(SysUser);
        return SysUser.getId();
    }

    @Override
    @Transactional
    public boolean updateSysUser(SysUser SysUser)
    {
        return JdbcUtil.isSuccess(userDao.updateSysUser(SysUser));
    }

    @Override
    public SysUser querySysUser(SysUser user)
    {
        return userDao.querySysUser(user);
    }
    @Override
    public PageData<SysUser> pageQuerySysUsers(PageParam<SysUser> page)
    {
        List<SysUser> users = userDao.pageQuerySysUsers(page);
        return new PageData<SysUser>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), users);
    }

	@Override
	public SysUser querySysUser4Login(SysUser SysUser) {
		return userDao.querySysUser(SysUser);
	}

	@Override
	public PageData<SysUser> pageQuerySysCensorUsers(PageParam<SysUser> page) {
        List<SysUser> users = userDao.querySysCensorUser(page);
        return new PageData<SysUser>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), users);
	}

	@Override
	public SysUser querySysUserLogin(SysUser sysuser) {
		 return userDao.querySysUserLogin(sysuser);
	}
	
	@Override
	public SysUser prosceniumLogin(String username, Integer idType) {
		return userDao.prosceniumLogin(username, idType);
	}

	@Override
	public List<SysUser> querySysUsers() {
		return userDao.querySysUsers();
	}

	@Override
	public SysUser querySysUserById(Integer id) {
		return userDao.querySysUserById(id);
	};

}
