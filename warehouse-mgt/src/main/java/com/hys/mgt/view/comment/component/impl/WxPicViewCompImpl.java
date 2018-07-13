package com.hys.mgt.view.comment.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.common.WxPicConverter;
import com.hys.mgt.view.comment.component.IWxPicViewComp;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.model.comment.WxPic;
import com.hys.service.comment.IWxPicService;

@Component
public class WxPicViewCompImpl implements IWxPicViewComp {
	
	private final static Logger log = LogProxy.getLogger(WxPicViewCompImpl.class);
	
	@Autowired
	private IWxPicService wxPicService;

	@Override
	public ResultPrompt addOrUpdateWxPic(WxPicVo wxPicVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			if(LogicUtil.isNotNull(wxPicVo) && LogicUtil.isNotNullAndEmpty(wxPicVo.getOrderNo())) {
				WxPic pic = wxPicService.queryWxPicByOrderNo(wxPicVo.getOrderNo(), wxPicVo.getUserId());
				
				if(LogicUtil.isNotNull(pic)) { //更新
//					pic.setPicUrl(wxPicVo.getPicUrl());
//					pic.setUploadIp(wxPicVo.getUploadIp());
//					pic.setUploadTime(wxPicVo.getUploadTime());
//					boolean b = wxPicService.updateWxPic(pic);
//					if(b) {	
//						 rp.setStatusCode("200");
//						 rp.setMessage("操作成功！");
//						 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
//						 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
//					 } else {
						 rp.setStatusCode("300");
						 rp.setMessage("该订单号已经提交过，请核实！");
						 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
						 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
//					 }
					
				} else { //新增
					WxPic picadd = WxPicConverter.convert2Do(wxPicVo);
					
					boolean b = wxPicService.addWxPic(picadd);
					if(b) {	
						 rp.setStatusCode("200");
						 rp.setMessage("操作成功！");
						 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
						 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
					 } else {
						 rp.setStatusCode("300");
						 rp.setMessage("操作失败！");
						 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
						 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
					 }
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
		}
		return rp;
	}


	@Override
	public PageData<WxPicVo> pageQueryWxPics(WxPicVo wxPicVo) {
		PageData<WxPicVo> pageVo = null;
        try
        {
        	WxPic u = (WxPic) WxPicConverter.convert2Do(wxPicVo);  
            PageParam<WxPic> page = new PageParam<WxPic>();
            page.setP(u);
            page.setPageNo(wxPicVo.getPageNum());
            page.setPageSize(wxPicVo.getNumPerPage());
            PageData<WxPic> pd = wxPicService.pageQueryWxPics(page);

            List<WxPic> list = pd.getPageData();
            List<WxPicVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list))
            {
                for (WxPic user : list)
                {
                    listvo.add((WxPicVo) WxPicConverter.convert2Vo(user));
                }
            }
            pageVo = new PageData<WxPicVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        }
        catch (Exception e)
        {
            log.debug("pageQueryUsers exception=>" + e);
        }
        return pageVo;
	}

}
