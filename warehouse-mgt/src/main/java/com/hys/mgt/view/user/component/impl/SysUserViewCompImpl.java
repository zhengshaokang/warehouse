package com.hys.mgt.view.user.component.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.hys.commons.crypto.MD5Coding;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.common.SysUserConverter;
import com.hys.mgt.view.user.component.ISysUserViewComp;
import com.hys.mgt.view.user.vo.SysUserVo;
import com.hys.model.user.SysRole;
import com.hys.model.user.SysUser;
import com.hys.service.user.component.ISysRoleService;
import com.hys.service.user.component.ISysUserService;


@Component
public class SysUserViewCompImpl implements ISysUserViewComp {

	 private final static Logger log = LogProxy.getLogger(SysUserViewCompImpl.class);

	    @Autowired
	    private ISysUserService userService;
	    @Autowired
	    private ISysRoleService roleService;
	    @Override
	    public ResultPrompt addUser(SysUserVo user,String addRoleId)
	    {
	        ResultPrompt resultPrompt = new ResultPrompt();
	        try
	        {
	            SysUser u = (SysUser) SysUserConverter.convert2Do(user);
	            String id = userService.addSysUser(u);
	            List<String> addRoleIds = new ArrayList<String>();
	            if (StringUtils.isNotBlank(addRoleId))
	            {
	                String[] strs = addRoleId.split(",");
	                for (String string : strs)
	                {
	                    addRoleIds.add(string);
	                }
	            }
	            roleService.updateUserSysRole(id, addRoleIds, null);
	            if (StringUtils.isNotBlank(id))
	            {
	                resultPrompt.setStatusCode("200");
	                resultPrompt.setMessage("操作成功！");
	                resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	                resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	                return resultPrompt;
	            }
	            else
	            {
	                resultPrompt.setStatusCode("300");
	                resultPrompt.setMessage("操作失败！");
	                resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	                resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	                return resultPrompt;
	            }
	        }
	        catch (Exception e)
	        {
	            log.debug("add user exceprtion=>" + e);
	            resultPrompt.setStatusCode("300");
	            resultPrompt.setMessage("操作失败！");
	            resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	            resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	            return resultPrompt;
	        }

	    }

	    @Override
	    public ResultPrompt updateUser(SysUserVo user, String[] addRoleIds, String[] delRoleIds)
	    {
	    	  ResultPrompt resultPrompt = new ResultPrompt();
	          try
	          {
	              SysUser u = (SysUser)SysUserConverter.convert2Do(user);
	              List<String> addRoleIdlist = Arrays.asList(addRoleIds);
	              List<String> delRoleIdlist = Arrays.asList(delRoleIds);
	              if (StringUtils.isNotBlank(u.getPassword()))
	              {
	                  u.setPassword(MD5Coding.encode2HexStr(u.getPassword().getBytes()));
	              }
	              userService.updateSysUser(u);
	              boolean bb = roleService.updateUserSysRole(user.getId(), addRoleIdlist, delRoleIdlist);
	              System.out.println(bb);
	              resultPrompt.setStatusCode("200");
	              resultPrompt.setMessage("操作成功！");
	              resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	              resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	              return resultPrompt;
	          }
	          catch (Exception e)
	          {
	              log.debug("add user exceprtion=>" + e);
	              resultPrompt.setStatusCode("300");
	              resultPrompt.setMessage("操作失败！");
	              resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	              resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	              return resultPrompt;
	          }
	    }


	    @Override
	    public SysUserVo queryUserById(String userId)
	    {
	        SysUser user = userService.querySysUserById(userId);
	        return (SysUserVo)SysUserConverter.convert2Vo(user);
	    }

	    @Override
	    public PageData<SysUserVo> pageQueryUsers(SysUserVo vo)
	    {
	        PageData<SysUserVo> pageVo = null;
	        try
	        {
	            SysUser u = (SysUser) SysUserConverter.convert2Do(vo);  
	            //u.setIdType(UserConstants.ID_TYPE_MGT);
	            PageParam<SysUser> page = new PageParam<SysUser>();
	            page.setP(u);
	            page.setPageNo(vo.getPageNum());
	            page.setPageSize(vo.getNumPerPage());
	            PageData<SysUser> pd = userService.pageQuerySysUsers(page);

	            List<SysUser> list = pd.getPageData();
	            List<SysUserVo> listvo = new ArrayList<>();
	            if (LogicUtil.isNotNull(list))
	            {
	                for (SysUser user : list)
	                {
	                    listvo.add((SysUserVo) SysUserConverter.convert2Vo(user));
	                }
	            }
	            pageVo = new PageData<SysUserVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
	        }
	        catch (Exception e)
	        {
	            log.debug("pageQueryUsers exception=>" + e);
	        }
	        return pageVo;
	    }

	    @Override
	    public ResultPrompt userDel(String userId)
	    {
	        ResultPrompt resultPrompt = new ResultPrompt();
	        try
	        {
	        	SysUser user = userService.querySysUserById(userId);
	        	user.setUserStatus(0);
	            userService.updateSysUser(user);
	            resultPrompt.setStatusCode("200");
	            resultPrompt.setMessage("操作成功！");
	            resultPrompt.setCallbackType(""); // 关闭当前窗口
	            resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	            return resultPrompt;
	        }
	        catch (Exception e)
	        {
	            log.debug("add user exceprtion=>" + e);
	            resultPrompt.setStatusCode("300");
	            resultPrompt.setMessage("操作失败！");
	            resultPrompt.setCallbackType(""); // 关闭当前窗口
	            resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	            return resultPrompt;
	        }
	    }
	    
	    @Override
	    public ResultPrompt userResetPassWord(SysUserVo user)
	    {
	        SysUser u = (SysUser)SysUserConverter.convert2Do(user);
	        if (StringUtils.isNotBlank(u.getPassword()))
	        {
	            u.setPassword(MD5Coding.encode2HexStr(u.getPassword().getBytes()));
	        }
	        boolean bb = userService.updateSysUser(u);
	        ResultPrompt resultPrompt = new ResultPrompt();
	        if (bb)
	        {
	            resultPrompt.setStatusCode("200");
	            resultPrompt.setMessage("操作成功！");
	            resultPrompt.setCallbackType(""); // 关闭当前窗口
	            resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	        }
	        else
	        {
	            resultPrompt.setStatusCode("300");
	            resultPrompt.setMessage("操作失败！");
	            resultPrompt.setCallbackType(""); // 关闭当前窗口
	            resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	        }

	        return resultPrompt;
	    }

		@Override
		public ResultPrompt uppwd(SysUserVo user, String pwd)
		 {
	        ResultPrompt resultPrompt = new ResultPrompt();
	        HttpSession session = SessionUtils.getSession();
	        SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	        pwd = MD5Coding.encode2HexStr(pwd.getBytes());
	        if (pwd.equals(sysAdminVo.getPassword()))
	        {
	            user.setId(sysAdminVo.getId());
	            user.setPassword(MD5Coding.encode2HexStr(user.getPassword().getBytes()));
	            SysUser u = (SysUser)SysUserConverter.convert2Do(user);
	            boolean bb = userService.updateSysUser(u);
	            if (bb)
	            {
	                session.setAttribute("sysadmin", null);
	                resultPrompt.setStatusCode("200");
	                resultPrompt.setMessage("修改成功！");
	                resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	                //HttpSession session = SessionUtils.getSession();
	                resultPrompt.setNavTabId(""); // 要刷新的tab页id
	            }
	            else
	            {
	                resultPrompt.setStatusCode("300");
	                resultPrompt.setMessage("修改失败！");
	                resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	                resultPrompt.setNavTabId(""); // 要刷新的tab页id
	            }
	        }
	        else
	        {
	            resultPrompt.setStatusCode("300");
	            resultPrompt.setMessage("原密码错误！");
	            resultPrompt.setCallbackType(""); // 关闭当前窗口
	            resultPrompt.setNavTabId(""); // 要刷新的tab页id
	        }

	        return resultPrompt;
	    }

	    @Override
	    public void userUpdateOrAdd(String userId, ModelMap modelMap)
	    {
	        SysUser user = userService.querySysUserById(userId);
	        List<SysRole> userSysRoles = roleService.queryRolesByUserId(userId);

	        PageParam<SysRole> page = new PageParam<SysRole>();
	        page.setP(new SysRole());
	        page.setPageNo(1);
	        page.setPageSize(Integer.MAX_VALUE);
	        PageData<SysRole> pd = roleService.querySysRoles(page);

	        modelMap.put("userUpdatevo", user);
	        modelMap.put("userUpdateUserSysRoles", userSysRoles);
	        modelMap.put("userUpdateSysRoles", pd.getPageData());

	    }

		@Override
		public PageData<SysUserVo> pageQueryCensorUsers(SysUserVo vo) {
			 PageData<SysUserVo> pageVo = null;
		        try
		        {
		            SysUser u = (SysUser) SysUserConverter.convert2Do(vo);  
		            //u.setIdType(UserConstants.ID_TYPE_MGT);
		            PageParam<SysUser> page = new PageParam<SysUser>();
		            page.setP(u);
		            page.setPageNo(vo.getPageNum());
		            page.setPageSize(vo.getNumPerPage());
		            PageData<SysUser> pd = userService.pageQuerySysCensorUsers(page);

		            List<SysUser> list = pd.getPageData();
		            List<SysUserVo> listvo = new ArrayList<>();
		            if (LogicUtil.isNotNull(list))
		            {
		                for (SysUser user : list)
		                {
		                    listvo.add((SysUserVo) SysUserConverter.convert2Vo(user));
		                }
		            }
		            pageVo = new PageData<SysUserVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
		        }
		        catch (Exception e)
		        {
		            log.debug("pageQueryUsers exception=>" + e);
		        }
		        return pageVo;
		}

		@Override 
		public ResultPrompt updateUserCensor(String userId,SysUserVo user) {
			 ResultPrompt resultPrompt = new ResultPrompt();
	          try
	          {
	              SysUser u = (SysUser)SysUserConverter.convert2Do(user);
	              u.setId(userId);
	              userService.updateSysUser(u);
	             // boolean bb = roleService.updateUserSysRole(user.getId(),);
	             // System.out.println(bb);
	              resultPrompt.setStatusCode("200");
	              resultPrompt.setMessage("操作成功！");
	              resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	              resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	              return resultPrompt;
	          }
	          catch (Exception e)
	          {
	              log.debug("add user exceprtion=>" + e);
	              resultPrompt.setStatusCode("300");
	              resultPrompt.setMessage("操作失败！");
	              resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	              resultPrompt.setNavTabId("user/list"); // 要刷新的tab页id
	              return resultPrompt;
	          }
		}

		
		
		
}
