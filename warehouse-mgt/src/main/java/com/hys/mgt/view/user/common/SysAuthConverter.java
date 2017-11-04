package com.hys.mgt.view.user.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.user.vo.SysAuthVo;
import com.hys.model.user.SysAuth;

public class SysAuthConverter {

	public static SysAuth convert2Do(SysAuthVo userVo)
    {
    	  if (LogicUtil.isNull(userVo))
          {
              return null;
          }
    	  SysAuth user = new SysAuth();
    	  user.setAuthName(userVo.getAuthName());
    	  user.setAuthPic(userVo.getAuthPic());
    	  user.setAuthUrl(userVo.getAuthUrl());
    	  user.setRecordStatus(userVo.getRecordStatus());
    	  user.setParentId(userVo.getParentId());
    	  user.setId(userVo.getId());
    	  user.setSort(userVo.getSort());
    	  user.setAuthbutton(userVo.getAuthbutton());
          return user;
    }

    
    public static SysAuthVo convert2Vo(SysAuth user)
    {
        if (user == null)
        {
            user=new SysAuth();
        }
        SysAuthVo userVo = new SysAuthVo();
        userVo.setAuthName(user.getAuthName());
        userVo.setAuthPic(user.getAuthPic());
        userVo.setAuthUrl(user.getAuthUrl());
        userVo.setRecordStatus(user.getRecordStatus());
        userVo.setParentId(user.getParentId());
        userVo.setId(user.getId());
        userVo.setSort(user.getSort());
        userVo.setAuthbutton(user.getAuthbutton());
        return userVo;
    }

}
