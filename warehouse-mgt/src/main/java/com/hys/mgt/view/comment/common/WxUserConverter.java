package com.hys.mgt.view.comment.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.vo.WxUserVo;
import com.hys.model.comment.WxUser;

public class WxUserConverter {

	public static WxUser convert2Do(WxUserVo wxUserVo){
    	  if (LogicUtil.isNull(wxUserVo)){
              return null;
          }
    	  WxUser wxUser = new WxUser();
    	  wxUser.setId(wxUserVo.getId());
    	  wxUser.setUserId(wxUserVo.getUserId());
    	  wxUser.setCity(wxUserVo.getCity());
    	  wxUser.setCountry(wxUserVo.getCountry());
    	  wxUser.setHeadimgurl(wxUserVo.getHeadimgurl());
    	  wxUser.setLanguage(wxUserVo.getLanguage());
    	  wxUser.setLogintime(wxUserVo.getLogintime());
    	  wxUser.setNickname(wxUserVo.getNickname());
    	  wxUser.setProvince(wxUserVo.getProvince());
    	  wxUser.setSex(wxUserVo.getSex());
          return wxUser;
    }

    
    public static WxUserVo convert2Vo(WxUser wxUser){
        if (LogicUtil.isNull(wxUser)){
        	return null;
        }
        WxUserVo wxUserVo = new WxUserVo();
        wxUserVo.setId(wxUser.getId());
        wxUserVo.setUserId(wxUser.getUserId());
        wxUserVo.setCity(wxUser.getCity());
        wxUserVo.setCountry(wxUser.getCountry());
        wxUserVo.setHeadimgurl(wxUser.getHeadimgurl());
        wxUserVo.setLanguage(wxUser.getLanguage());
        wxUserVo.setLogintime(wxUser.getLogintime());
        wxUserVo.setNickname(wxUser.getNickname());
        wxUserVo.setProvince(wxUser.getProvince());
        wxUserVo.setSex(wxUser.getSex());
        return wxUserVo;
    }

}
