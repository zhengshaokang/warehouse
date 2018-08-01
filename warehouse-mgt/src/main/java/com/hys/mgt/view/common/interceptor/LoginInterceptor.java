package com.hys.mgt.view.common.interceptor;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hys.commons.conf.ProfileManager;
import com.hys.commons.crypto.Base64Ext;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.user.component.ISysUserViewComp;
import com.hys.mgt.view.user.vo.SysUserVo;


public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	private ISysUserViewComp userViewComp;

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
        
        if(checkWxInteceptor(str).booleanValue()){ //微信请求
        	
        	 String wxUser =  (String)session.getAttribute("openId");
        	 if (null == wxUser){// 不存在用户身份
                 String appId = "" ;//"wx36c0752699ec541c";//ProfileManager.getStringByKey("maowu_webapp_wx.wx_appId", "");
                 String doMain = ProfileManager.getStringByKey("hys_webapp.wx_domain_url", "");
                 String param = request.getQueryString();
                 String originalUrl = request.getRequestURL().toString();
                 if(LogicUtil.isNotNullAndEmpty(param)) {
	                 //根据链接获取userId
	                 String[] params = param.split("=");
	 	             String activStr = new String(Base64Ext.decode(params[1]),"UTF-8");
	 	             String[] activArr= activStr.split("\\|");
	 	             //根据userId查询微信公众号appId的配置
	 	            SysUserVo user =  userViewComp.queryUserById(activArr[0]);
	 	            if(LogicUtil.isNotNull(user) && LogicUtil.isNotNullAndEmpty(user.getAppId())) {
	 	            	appId = user.getAppId();
	 	            	session.setAttribute("c_secret", user.getSecret());
	 	            	session.setAttribute("c_appid", user.getAppId());
	 	            }
	 	            originalUrl = request.getRequestURL().toString() + "?param=" + Base64Ext.encode(param.getBytes());
                 }
 	             
                 String redirectUrl = doMain + "initialWxOpenId?originalUrl=" + originalUrl;
                 String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
                         + "&response_type=code&scope=snsapi_userinfo&state=STATE&redirect_uri="
                         + URLEncoder.encode(redirectUrl, "UTF-8") + "#wechat_redirect";
                 response.sendRedirect(url);
                 return false;
             }  else {
                 return true;
             }
        }
        
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
    
    private Boolean checkWxInteceptor(String paramString) {
		List<String> localArrayList = new ArrayList<>();
		localArrayList.add("/openpicadd");
		localArrayList.add("/wxpicadd");
		localArrayList.add("/wxaddpicsubmit");
		return Boolean.valueOf(localArrayList.contains(paramString));
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
		localArrayList.add("/initialWxOpenId");
		localArrayList.add("/sendmsg");
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
