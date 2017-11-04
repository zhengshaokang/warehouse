package com.hys.dal.select;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.dal.component.user.ISysUserDalComp;
import com.hys.model.user.SysUser;

@Component
public class Users {
	@Autowired
	private ISysUserDalComp sysUserDalComp;
	
	public  Map<String,String> getOptions(){
		Map<String,String> map = new LinkedHashMap<String,String>();
		List<SysUser> list = sysUserDalComp.querySysUsers();
		for (SysUser u : list) {
			map.put(u.getId(), u.getRealname());
		}
		return map;
	}
}
 