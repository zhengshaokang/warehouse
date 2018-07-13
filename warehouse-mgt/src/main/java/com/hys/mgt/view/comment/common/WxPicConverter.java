package com.hys.mgt.view.comment.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.model.comment.WxPic;

public class WxPicConverter {

	public static WxPic convert2Do(WxPicVo wxPicVo){
    	  if (LogicUtil.isNull(wxPicVo)){
              return null;
          }
    	  WxPic wxPic = new WxPic();
    	  wxPic.setId(wxPicVo.getId());
    	  wxPic.setUserId(wxPicVo.getUserId());
    	  wxPic.setOrderNo(wxPicVo.getOrderNo());
    	  wxPic.setPicUrl(wxPicVo.getPicUrl());
    	  wxPic.setUploadTime(wxPicVo.getUploadTime());
    	  wxPic.setUploadIp(wxPicVo.getUploadIp());
          return wxPic;
    }

    
    public static WxPicVo convert2Vo(WxPic wxPic){
        if (LogicUtil.isNull(wxPic)){
        	return null;
        }
        WxPicVo wxPicVo = new WxPicVo();
        wxPicVo.setId(wxPic.getId());
        wxPicVo.setUserId(wxPic.getUserId());
        wxPicVo.setOrderNo(wxPic.getOrderNo());
        wxPicVo.setPicUrl(wxPic.getPicUrl());
        wxPicVo.setUploadTime(wxPic.getUploadTime());
        wxPicVo.setUploadIp(wxPic.getUploadIp());
        return wxPicVo;
    }

}