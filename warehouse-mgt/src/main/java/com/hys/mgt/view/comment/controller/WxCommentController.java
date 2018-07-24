package com.hys.mgt.view.comment.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hys.commons.crypto.Base64Ext;
import com.hys.commons.http.HttpInvokeUtil;
import com.hys.commons.json.JsonConverter;
import com.hys.commons.otherapi.wxapi.bean.Oauth2AccessToken;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.conenum.EnumOrderPayStatus;
import com.hys.mgt.view.comment.component.IActivViewComp;
import com.hys.mgt.view.comment.component.IWxPicViewComp;
import com.hys.mgt.view.comment.component.IWxUserViewComp;
import com.hys.mgt.view.comment.vo.ActivVo;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.comment.vo.WxUserVo;
import com.hys.mgt.view.common.utils.IPUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;

@Controller
@RequestMapping(value="/")
public class WxCommentController {
	
		@Autowired
		private IWxPicViewComp wxPicViewComp;
		@Autowired
		private IActivViewComp activViewComp;
		@Autowired
		private IWxUserViewComp wxUserViewComp ;

	    @RequestMapping(value = "wxpicadd")
	    public String list(String activ,String code, ModelMap modelMap, HttpServletRequest request){
	    	try {
	    		String decodeStr = new String(Base64Ext.decode(activ),"UTF-8");
				String[] decodeArr = decodeStr.split("\\|");
	    		
	    		HttpSession session = request.getSession();
	    		String openId = (String) session.getAttribute("openId");
	    		String token = (String) session.getAttribute("token");
	    		
                WxUserVo wxuser = getWxUserinfo(openId,token);
                
                if(LogicUtil.isNotNull(wxuser)) {
                	 wxuser.setUserId(Integer.parseInt(decodeArr[0]));
                	 wxuser.setOpenId(openId);
                     wxUserViewComp.addWxUser(wxuser);
                }
	    	
				ActivVo ac = activViewComp.queryActivByCode(decodeArr[1], Integer.parseInt(decodeArr[0]));
				modelMap.put("activ", ac);
				modelMap.put("userId", decodeArr[0]);
				modelMap.put("error", 0);
			} catch (Exception e) {
				modelMap.put("error", 1);
				e.printStackTrace();
			}
	    	
	        return "comment/wx_pic_add";
	    }
	    
	    @RequestMapping(value = "initialWxOpenId", method = { RequestMethod.POST, RequestMethod.GET })
	    public String initialWxOpenId(String code, String originalUrl, HttpSession session) throws Exception {
	        // 依据微信回调传递code参数，再次请求微信获取openid，并把openid放入session中
	        String[] strs = originalUrl.split("\\?");
	        String param = "";
	        if (null != strs && strs.length > 1)
	        {
	            String[] params = strs[1].split("=");
	            param = new String(Base64Ext.decode(params[1]));
	        }
	        if (StringUtils.isNotBlank(param))
	        {
	            originalUrl = strs[0] + "?" + param;
	        }
	        
	        
	        // 根据openid查询用户是否存在，若不存在保存到数据库中，若存在则直接把用户信息存入session中
	        String openId = (String) session.getAttribute("openId");
	        String token = (String) session.getAttribute("token");
    		if (StringUtils.isBlank(openId)) {
    			Oauth2AccessToken accessToken = getOauth2AccessToken(code,session);
	    		if (null != accessToken && StringUtils.isNotBlank(accessToken.getAccessToken())) {
	                openId = accessToken.getOpenId();
	                token = accessToken.getAccessToken();
	            }
            }
    		session.setAttribute("openId", openId);
            session.setAttribute("token", token);
	        return "redirect:" + URLDecoder.decode(originalUrl, "UTF-8");// 跳转到用户请求的原始链接
	    }
	    
	    
	    public static Oauth2AccessToken getOauth2AccessToken(String code,HttpSession session) throws Exception{
	        if (StringUtils.isNotBlank(code)){
	            String url = "https://api.weixin.qq.com/sns/oauth2/access_token";
	            String appId = (String) session.getAttribute("c_appid");//"wx36c0752699ec541c";
	            String secret64 = (String) session.getAttribute("c_secret");
	            System.out.println("appId"+appId +",secret64" + secret64);
	            String secret = new String(Base64Ext.decode(secret64),"UTF-8");//"28dba62ab2c83f7fedb3b5d9edaba30b";
	            String grant_type = "authorization_code";

	            Map<String, String> map = new HashMap<String, String>();
	            map.put("appId", appId);
	            map.put("secret", secret);
	            map.put("grant_type", grant_type);
	            map.put("code", code);
	            String json = HttpInvokeUtil.httpGet(url, map);

	            return JsonConverter.parse(json, Oauth2AccessToken.class);
	        } else {
	            return null;
	        }
	    }
	    
	    public static WxUserVo getWxUserinfo(String openId ,String access_token){
	        if (StringUtils.isNotBlank(openId) && StringUtils.isNotBlank(access_token)){
	            String url = "https://api.weixin.qq.com/sns/userinfo";
	            Map<String, String> map = new HashMap<String, String>();
	            map.put("openid", openId);
	            map.put("lang", "zh_CN");
	            map.put("access_token", access_token);
	            String json = HttpInvokeUtil.httpGet(url, map);

	            return JsonConverter.parse(json, WxUserVo.class);
	        } else {
	            return null;
	        }
	    }
	    
	    @ResponseBody
	    @RequestMapping("wxaddpicsubmit")
	    public ResultPrompt wxaddpicsubmit(WxPicVo wxPicVo,HttpServletRequest request) {
	    	
	    	HttpSession session = request.getSession();
    		String openId = (String) session.getAttribute("openId");
    		wxPicVo.setOpenId(openId);
    		WxUserVo wxuser =  wxUserViewComp.queryWxUserByOpendId(openId, wxPicVo.getUserId());
    		if(LogicUtil.isNotNull(wxuser)) {
    			wxPicVo.setNickname(wxuser.getNickname());
    		}
    		wxPicVo.setPayStatus(EnumOrderPayStatus.NOCHECK.getValue());
    		wxPicVo.setUploadIp(IPUtils.getIpAddr(request));
	    	wxPicVo.setUploadTime(DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
	    	ResultPrompt ResultPrompt = wxPicViewComp.addOrUpdateWxPic(wxPicVo);
	        return ResultPrompt;
	    }
}
