package com.hys.mgt.view.user.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.crypto.MD5Coding;
import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.constants.WebConstants;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.component.ISysUserViewComp;
import com.hys.mgt.view.user.vo.SysUserVo;

@Controller
@RequestMapping(value="/user/")
public class SysUserController {
	   @Autowired
	    private ISysUserViewComp userViewComp;

	    // 查询用户列表
	    @RequestMapping(value = "list")
	    public String list(SysUserVo vo, ModelMap modelMap, HttpServletRequest request)
	    {
	        PageData<SysUserVo> pageParam = userViewComp.pageQueryUsers(vo);
	        modelMap.put("pageParam", pageParam);
	        modelMap.put("userParam", vo);// 查询时传入的参数
	        return "user/user_list";
	    }
	    //修改用户审核状态
	    @RequestMapping(value = "userCensorUpdate")
	    public String userCensor(String userId,ModelMap modelMap)
	    {
	    	userViewComp.userUpdateOrAdd(userId, modelMap);
	        return "user/censor_add";
	    }
	    
	    @RequestMapping(value = "userAdd")
	    public String userAdd()
	    {
	        return "user/user_add";
	    }
         //添加用户
	    @ResponseBody
	    @RequestMapping(value = "userAddSubmit", method = RequestMethod.POST)
	    public ResultPrompt userAddSubmit(SysUserVo vo, HttpServletRequest reqest)
	    {
	        String roles = reqest.getParameter("roles");
	        vo.setPassword("12345678");
	        ResultPrompt resultPrompt = userViewComp.addUser(vo, roles);
	        return resultPrompt;
	    }

	    @RequestMapping(value = "userUpdate")
	    public String userUpdate(String userId, ModelMap modelMap)
	    {
	        userViewComp.userUpdateOrAdd(userId, modelMap);
	        return "user/user_update";
	    }

	    @ResponseBody
	    @RequestMapping(value = "userUpdateSubmit", method = RequestMethod.POST)
	    public ResultPrompt userUpdateSubmit(SysUserVo vo, HttpServletRequest request)
	    {
	        String[] addRoleIds = request.getParameterValues("addRoleId");
	        String[] delRoleIds = request.getParameterValues("delRoleId");
	        ResultPrompt resultPrompt = userViewComp.updateUser(vo, addRoleIds, delRoleIds);

	        return resultPrompt;
	    }

	    @ResponseBody
	    @RequestMapping(value = "userDelete")
	    public ResultPrompt userDel(String userId)
	    {
	        ResultPrompt resultPrompt = userViewComp.userDel(userId);
	        return resultPrompt;
	    }

	    @RequestMapping(value = "userresetpassword")
	    @ResponseBody
	    public ResultPrompt userResetPassWord(String userId)
	    {
	        SysUserVo vo = new SysUserVo();
	        vo.setId(userId);
	        vo.setPassword(WebConstants.RESET_PASSWORD);
	        ResultPrompt resultPrompt = userViewComp.userResetPassWord(vo);
	        return resultPrompt;
	    }

	    @RequestMapping(value = "updatepwdinit")
	    public String updatePwdInit()
	    {
	        return "sys/updatepwd";
	    }

	    @RequestMapping(value = "valipwd", method = RequestMethod.GET)
	    @ResponseBody
	    public boolean valipwd(String pwd)
	    {
	        HttpSession session = SessionUtils.getSession();
	        SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	        pwd = MD5Coding.encode2HexStr(pwd.getBytes());
	        return pwd.equals(sysAdminVo.getPassword());

	    }

	    @RequestMapping(value = "updatepwd")
	    @ResponseBody
	    public ResultPrompt uppwd(String pwd, SysUserVo vo, HttpServletResponse response)
	    {
	        ResultPrompt resultPrompt = userViewComp.uppwd(vo, pwd);
	        return resultPrompt;

	    }
}
