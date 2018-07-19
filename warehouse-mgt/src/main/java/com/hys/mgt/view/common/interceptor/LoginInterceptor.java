package com.hys.mgt.view.common.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hys.mgt.view.user.vo.SysUserVo;


public class LoginInterceptor implements HandlerInterceptor
{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        String jsessionid = request.getParameter("jsessionid");
        if (jsessionid != null)
        {
            return true;
        }
        
        String str = request.getRequestURI().substring(
        		request.getContextPath().length());
		if (checkInteceptor(str).booleanValue()) {
			return true;
		}
		if (str.equals("/")) {
			return true;
		}
        HttpSession session = request.getSession();
        SysUserVo sysUserVo = (SysUserVo) session.getAttribute("sysadmin");
        if (sysUserVo != null)
        {
            return true;
        }
        
        String contextPath=request.getContextPath();
        String url = request.getRequestURI();
    	response.sendRedirect(contextPath+"/login");
       // request.getRequestDispatcher("/login").forward(request, response); 
        return false;
    }
    
    private Boolean checkInteceptor(String paramString) {
		List<String> localArrayList = new ArrayList<>();
		localArrayList.add("/login");
		localArrayList.add("/kaptcha");
		localArrayList.add("/loginSubmit");
		localArrayList.add("/registerForm");
		localArrayList.add("/register");
		localArrayList.add("/logout");
		localArrayList.add("/denied");
		localArrayList.add("/timeout");
		localArrayList.add("/fileupload/uploadpic");
		localArrayList.add("/wxpicadd");
		localArrayList.add("/wxaddpicsubmit");
		return Boolean.valueOf(localArrayList.contains(paramString));
	}

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception
    {

    }

}
