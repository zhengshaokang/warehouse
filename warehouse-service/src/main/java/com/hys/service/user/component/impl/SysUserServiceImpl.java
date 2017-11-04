package com.hys.service.user.component.impl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hys.commons.crypto.MD5Coding;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.user.ISysUserDalComp;
import com.hys.model.user.SysUser;
import com.hys.service.user.component.ISysUserService;

@Service
public class SysUserServiceImpl implements ISysUserService  {
	
	private final static Logger log = LogProxy.getLogger(SysUserServiceImpl.class);

    @Autowired 
    private ISysUserDalComp userComp;

    @Override
    public String addSysUser(SysUser user)
    {
        log.debug("parameter user is=>" + user);

        if (LogicUtil.isNull(user))
        {
            return null;
        }
        //加密
        user.setPassword(MD5Coding.encode2HexStr(user.getPassword().getBytes()));
        return userComp.addSysUser(user);
    }

    @Override
    public boolean updateSysUser(SysUser user)
    {
        log.debug("parameter user is=>" + user);

        if (LogicUtil.isNull(user))
        {
            return false;
        }

        return userComp.updateSysUser(user);
    }

    @Override
    public SysUser querySysUserById(String SysUserId)
    {
        log.debug("parameter userId is=>" + SysUserId);

        if (LogicUtil.isNullOrEmpty(SysUserId))
        {
            return null;
        }

        SysUser user = new SysUser();
        user.setId(SysUserId);
        return userComp.querySysUser(user);
    }

   
    @Override
    public SysUser querySysUserByEmail(String email)
    {
        log.debug("parameter email is=>" + email);

        if (LogicUtil.isNullOrEmpty(email))
        {
            return null;
        }
        SysUser user = new SysUser();
        user.setEmail(email);
        return userComp.querySysUser(user);
    }

    @Override
    public SysUser querySysUserByPhone(String phone)
    {
        log.debug("parameter phone is=>" + phone);

        if (LogicUtil.isNullOrEmpty(phone))
        {
            return null;
        }

        SysUser user = new SysUser();
        user.setMobile(phone);
        return userComp.querySysUser(user);
    }

    @Override
    public PageData<SysUser> pageQuerySysUsers(PageParam<SysUser> page)
    {
        log.debug("parameter page is=>" + page);

        if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP()))
        {
            return null;
        }

        return userComp.pageQuerySysUsers(page);
    }

	@Override
	public SysUser querySysUser4Login(SysUser user) {
		log.debug("parameter user is=>" + user);
		SysUser user1=userComp.querySysUser(user);
        if(user1!=null)
        {
        	return user1;
        }
		return null;
	}

	@Override
	public SysUser querySysUserByName(String loginname) {
		   log.debug("parameter username is=>" + loginname);

	        if (LogicUtil.isNullOrEmpty(loginname))
	        {
	            return null;
	        }

	        SysUser user = new SysUser();
	        user.setLoginname(loginname);
	        return userComp.querySysUser(user);
	}

	@Override
	public PageData<SysUser> pageQuerySysCensorUsers(PageParam<SysUser> page) {
		log.debug("parameter page is=>" + page);

        if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP()))
        {
            return null;
        }

        return userComp.pageQuerySysCensorUsers(page);
	}

	@Override
	public SysUser prosceniumLogin(String loginname, Integer idType) {
		return userComp.prosceniumLogin(loginname, idType);
	}
	
}  