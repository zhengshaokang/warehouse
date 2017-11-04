package com.hys.service.user.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.user.ISysAuthDalComp;
import com.hys.dal.component.user.ISysUserDalComp;
import com.hys.model.user.SysAuth;
import com.hys.model.user.SysUser;
import com.hys.service.common.RecursionNode;
import com.hys.service.user.component.ISyAuthService;


@Service
public class SyAuthServiceImpl implements ISyAuthService{
        
	 private final Logger log = LogProxy.getLogger(SyAuthServiceImpl.class);

	    @Autowired
	    private ISysAuthDalComp sysAuthDalComp;
	    @Autowired
	    private ISysUserDalComp sysUserDalComp;
	    
	    @Override
	    public String addSysAuth(SysAuth sysAuth)
	    {//添加权限
	        log.debug("parameter sysAuth is=" + sysAuth);
	        if (LogicUtil.isNull(sysAuth))
	        {
	            return null;
	        }

	        return sysAuthDalComp.addSysAuth(sysAuth);
	    }

	    @Override
	    public boolean deleteSysAuth(String authId)
	    {//删除权限
	        log.debug("parameter authId is=" + authId);
	        if (LogicUtil.isNullOrEmpty(authId))
	        {
	            return false;
	        }

	        return sysAuthDalComp.deleteSysAuth(authId);
	    }

	    @Override
	    public boolean updateSysAuth(SysAuth sysAuth)
	    {//用户权限
	        log.debug("parameter sysAuth is=" + sysAuth);
	        if (LogicUtil.isNull(sysAuth))
	        {
	            return false;
	        }

	        return sysAuthDalComp.updateSysAuth(sysAuth);
	    }

	    @Override
	    public SysAuth querySysAuth(String authId)
	    {
	        log.debug("parameter authId is=" + authId);
	        if (LogicUtil.isNull(authId))
	        {
	            return null;
	        }

	        return sysAuthDalComp.querySysAuth(authId);
	    }

	    @Override
	    public List<SysAuth> querySysAuths()
	    {
	        log.debug("query all sys auths");
	        return sysAuthDalComp.querySysAuths();
	    }

	    @Override
	    public List<SysAuth> queryAuthsByUserId(String userId)
	    {
	        List<SysAuth> auths = sysAuthDalComp.queryAuthsByUserId(userId);
	        if (LogicUtil.isNullOrEmpty(auths))
	        {
	            return null;
	        }

	        // 转换树形结构
	        List<RecursionNode<SysAuth>> nodes = new ArrayList<RecursionNode<SysAuth>>();
	        this.addNode("0", auths, nodes);// 从父节点ID为0的节点开始组装属性结构
	        auths.clear();
	        this.convert(nodes, auths);

	        return auths;
	    }

	    private void addNode(String parentId, List<SysAuth> list, List<RecursionNode<SysAuth>> nodes)
	    {
	        for (SysAuth auth : list)
	        {
	            if (parentId.equals(auth.getParentId()))
	            {
	                RecursionNode<SysAuth> node = new RecursionNode<SysAuth>();
	                node.setItem(auth);
	                node.setNodes(new ArrayList<RecursionNode<SysAuth>>());
	                nodes.add(node);

	                this.addNode(auth.getId(), list, node.getNodes());
	            }
	        }
	    }

	    private void convert(List<RecursionNode<SysAuth>> nodes, List<SysAuth> items)
	    {
	        for (RecursionNode<SysAuth> node : nodes)
	        {
	            items.add(node.getItem());
	            this.convert(node.getNodes(), items);
	        }
	    }

		@Override
		public SysUser querySysUserByUsernamePwd(String loginname,
				String password) {
			  log.debug("parameter username is=> {0} ,password is=>{1}", loginname, password);
		        if (LogicUtil.isNullOrEmpty(loginname) || LogicUtil.isNullOrEmpty(password))
		        {
		            return null;
		        }

		        SysUser sysUser = new SysUser();
		        sysUser.setLoginname(loginname);
		        sysUser.setPassword(password);
		        return sysUserDalComp.querySysUserLogin(sysUser);
		}

		@Override
		public PageData<SysAuth> pageQuerySysAuth(PageParam<SysAuth> page) {
			    log.debug("parameter page is=>" + page);

		        if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP()))
		        {
		            return null;
		        }

		        return sysAuthDalComp.pageQuerySysAuth(page);
		}

		
}
