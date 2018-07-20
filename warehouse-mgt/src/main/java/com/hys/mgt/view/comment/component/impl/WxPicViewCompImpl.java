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
import com.hys.dal.select.conenum.EnumOrderPayStatus;
import com.hys.mgt.view.comment.common.WxPicConverter;
import com.hys.mgt.view.comment.component.IWxPicViewComp;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.model.comment.Order;
import com.hys.model.comment.WxPic;
import com.hys.service.comment.IOrderService;
import com.hys.service.comment.IWxPicService;

@Component
public class WxPicViewCompImpl implements IWxPicViewComp {
	
	private final static Logger log = LogProxy.getLogger(WxPicViewCompImpl.class);
	
	@Autowired
	private IWxPicService wxPicService;
	@Autowired
	private IOrderService orderService;

	@Override
	public ResultPrompt addOrUpdateWxPic(WxPicVo wxPicVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			if(LogicUtil.isNotNull(wxPicVo) && LogicUtil.isNotNullAndEmpty(wxPicVo.getOrderNo())) {
				WxPic pic = wxPicService.queryWxPicByOrderNo(wxPicVo.getOrderNo(), wxPicVo.getUserId());
				
				if(LogicUtil.isNotNull(pic)) { //更新
					if(pic.getPayStatus() == EnumOrderPayStatus.NOPASS.getValue()) {
						pic.setPicUrl(wxPicVo.getPicUrl());
						pic.setUploadIp(wxPicVo.getUploadIp());
						pic.setUploadTime(wxPicVo.getUploadTime());
						boolean b = wxPicService.updateWxPic(pic);
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
					} else {
						 rp.setStatusCode("300");
						 rp.setMessage("该订单号已经提交过，请核实！");
						 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
						 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
					 }
					
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
	@Override
	public ResultPrompt validateOrder(String orderNo, Integer userId) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			//验证订单是否存在，不能重复
			Order  pc = orderService.queryOrderByNo(orderNo, userId);
			if(null != pc) {
				 rp.setStatusCode("200");
				 return rp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
		}
		return rp;
	}


	@Override
	public ResultPrompt updateOrderPayStatus(Integer wxPicId, Integer payStatus, Integer userId) {
		// 查询评价订单号，跟进订单号查询订单，修改评价和订单的审核状态
		ResultPrompt rp = new ResultPrompt();
		try {
			WxPic pic = wxPicService.queryWxPicById(wxPicId, userId);
			if(null != pic) {
				 if(LogicUtil.isNullOrEmpty(pic.getOrderNo())) {
					 rp.setStatusCode("300");
					 rp.setMessage("该数据订单号为空！");
			    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
			    	 return rp;
				 }
				 Order  order = orderService.queryOrderByNo(pic.getOrderNo(), userId);
				 if(LogicUtil.isNull(order)) {
					 rp.setStatusCode("300");
					 rp.setMessage("订单号对应的订单列表不存在！");
			    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
			    	 return rp;
				 }
				 pic.setPayStatus(payStatus);
				 boolean b = wxPicService.updateWxPic(pic);
				 order.setIsPay(payStatus);
				 boolean b1 = orderService.updateOrder(order);
				 if(b1 && b) {
					 rp.setStatusCode("200");
					 rp.setMessage("操作成功！");
			    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
				 } else {
					 rp.setStatusCode("300");
			    	 rp.setMessage("操作失败！");
			    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
				 }
			} else {
				 rp.setStatusCode("300");
				 rp.setMessage("该条评价不存在！");
		    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
		    	 return rp;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
		}
		return rp;
	}
}
