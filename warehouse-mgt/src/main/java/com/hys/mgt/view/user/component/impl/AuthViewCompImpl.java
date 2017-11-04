package com.hys.mgt.view.user.component.impl;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.common.SysAuthConverter;
import com.hys.mgt.view.user.component.IAuthViewComp;
import com.hys.mgt.view.user.vo.SysAuthVo;
import com.hys.model.user.SysAuth;
import com.hys.service.user.component.ISyAuthService;



@Component
public class AuthViewCompImpl implements IAuthViewComp{
	 private final static Logger log = LogProxy.getLogger(SysUserViewCompImpl.class);
	
     @Autowired
	 private ISyAuthService sysAuthService;
	 
	@Override
	public List<SysAuth> queryAll() {
		return null;
	}

	@Override
	public PageData<SysAuthVo> pageQuerySysAuth(SysAuthVo vo) {
		   PageData<SysAuthVo> pageVo = null;
	        try
	        {
	        	SysAuth u = (SysAuth)SysAuthConverter.convert2Do(vo);  
	            PageParam<SysAuth> page = new PageParam<SysAuth>();
	            page.setP(u);
	            page.setPageNo(vo.getPageNum());
	            page.setPageSize(vo.getNumPerPage());
	            PageData<SysAuth> pd = sysAuthService.pageQuerySysAuth(page);
	            List<SysAuth> list = pd.getPageData();
	            List<SysAuthVo> listvo = new ArrayList<>();
	            if (LogicUtil.isNotNull(list))
	            {
	                for (SysAuth user : list)
	                {
	                    listvo.add((SysAuthVo) SysAuthConverter.convert2Vo(user));
	                }
	            }
	            pageVo = new PageData<SysAuthVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
	        }
	        catch (Exception e)
	        {
	            log.debug("pageQueryCensor exception=>" + e);
	        }
	        return pageVo;
	}

	@Override
	public ResultPrompt addAuth(SysAuthVo user) {
		 ResultPrompt resultPrompt = new ResultPrompt();
	        try
	        {
	            SysAuth u = (SysAuth) SysAuthConverter.convert2Do(user);
	            String id = sysAuthService.addSysAuth(u);
	            if (StringUtils.isNotBlank(id))
	            {
	                resultPrompt.setStatusCode("200");
	                resultPrompt.setMessage("操作成功！");
	                resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	                resultPrompt.setNavTabId("auth/list"); // 要刷新的tab页id
	                return resultPrompt;
	            }
	            else
	            {
	                resultPrompt.setStatusCode("300");
	                resultPrompt.setMessage("操作失败！");
	                resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	                resultPrompt.setNavTabId("auth/list"); // 要刷新的tab页id
	                return resultPrompt;
	            }
	        }
	        catch (Exception e)
	        {
	            log.debug("add user exceprtion=>" + e);
	            resultPrompt.setStatusCode("300");
	            resultPrompt.setMessage("操作失败！");
	            resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
	            resultPrompt.setNavTabId("userlist"); // 要刷新的tab页id
	            return resultPrompt;
	        }

	}

	@Override
	public ResultPrompt updateUser(SysAuthVo user) {
		  ResultPrompt resultPrompt = new ResultPrompt();
          try
          {
              SysAuth u = (SysAuth)SysAuthConverter.convert2Do(user);
              sysAuthService.updateSysAuth(u);
              resultPrompt.setStatusCode("200");
              resultPrompt.setMessage("操作成功！");
              resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
              resultPrompt.setNavTabId("auth/list"); // 要刷新的tab页id
              return resultPrompt;
          }
          catch (Exception e)
          {
              log.debug("add user exceprtion=>" + e);
              resultPrompt.setStatusCode("300");
              resultPrompt.setMessage("操作失败！");
              resultPrompt.setCallbackType("closeCurrent"); // 关闭当前窗口
              resultPrompt.setNavTabId("auth/list"); // 要刷新的tab页id
              return resultPrompt;
          }
	}

	@Override
	public SysAuthVo QuerySysAuth(String authId,ModelMap modelMap) {
		SysAuth user = sysAuthService.querySysAuth(authId);
		modelMap.put("authUpdatevo", user);
        return (SysAuthVo)SysAuthConverter.convert2Vo(user);
	}

	@Override
	public ResultPrompt authDel(String userId) {
		  ResultPrompt resultPrompt = new ResultPrompt();
	        try
	        {
	        	sysAuthService.deleteSysAuth(userId);
	            resultPrompt.setStatusCode("200");
	            resultPrompt.setMessage("操作成功！");
	            resultPrompt.setCallbackType("auth/list"); // 关闭当前窗口
	            resultPrompt.setNavTabId("userlist"); // 要刷新的tab页id
	            return resultPrompt;
	        }
	        catch (Exception e)
	        {
	            log.debug("add user exceprtion=>" + e);
	            resultPrompt.setStatusCode("300");
	            resultPrompt.setMessage("操作失败！");
	            resultPrompt.setCallbackType("auth/list"); // 关闭当前窗口
	            resultPrompt.setNavTabId("userlist"); // 要刷新的tab页id
	            return resultPrompt;
	        }
	}

}
