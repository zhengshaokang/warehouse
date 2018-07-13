package com.hys.mgt.view.user.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.utils.TrimUtils;
import com.hys.mgt.view.user.vo.SysUserVo;
import com.hys.model.user.SysUser;


public class SysUserConverter
{
    
    public static SysUser convert2Do(SysUserVo userVo)
    {
    	  if (LogicUtil.isNull(userVo))
          {
              return null;
          }
        SysUser user = new SysUser();
        user.setId(TrimUtils.getTrim(userVo.getId()));
        user.setLoginname(TrimUtils.getTrim(userVo.getLoginname()));
        user.setPassword(TrimUtils.getTrim(userVo.getPassword()));
        user.setRealname(TrimUtils.getTrim(userVo.getRealname()));
        user.setMobile(TrimUtils.getTrim(userVo.getMobile()));
        user.setPassword(userVo.getPassword());
        user.setBirthday(userVo.getBirthday());
        
        user.setVipLevel(userVo.getVipLevel());
        user.setVipDate(userVo.getVipDate());
        user.setIsComment(userVo.getIsComment());
        user.setIsWarhouse(userVo.getIsWarhouse());
        user.setCommentUrl(userVo.getCommentUrl());
        user.setLoginIp(userVo.getLoginIp());
        user.setLoginTime(userVo.getLoginTime());
        
        return user;
    }

    
    public static SysUserVo convert2Vo(SysUser sysuser)
    {
        if (sysuser == null)
        {
            return null;
        }
        SysUserVo userVo = new SysUserVo();
        userVo.setId(sysuser.getId());
        userVo.setLoginname(sysuser.getLoginname());
        userVo.setPassword(sysuser.getPassword());
        userVo.setRealname(sysuser.getRealname());
        userVo.setMobile(sysuser.getMobile());
        userVo.setEmail(sysuser.getEmail());
        userVo.setAge(sysuser.getAge());
        userVo.setBirthday(sysuser.getBirthday());
        userVo.setUserStatus(sysuser.getUserStatus());
        userVo.setCreateDatetime(sysuser.getCreateDatetime());
        userVo.setCreateUserId(sysuser.getCreateUserId());
        userVo.setUpdateDatetime(sysuser.getUpdateDatetime());
        userVo.setUpdateUserId(sysuser.getUpdateUserId());
        userVo.setVipLevel(sysuser.getVipLevel());
        userVo.setVipDate(sysuser.getVipDate());
        userVo.setIsComment(sysuser.getIsComment());
        userVo.setIsWarhouse(sysuser.getIsWarhouse());
        userVo.setCommentUrl(sysuser.getCommentUrl());
        userVo.setLoginIp(sysuser.getLoginIp());
        userVo.setLoginTime(sysuser.getLoginTime());
        
        
        return userVo;
    }

}
