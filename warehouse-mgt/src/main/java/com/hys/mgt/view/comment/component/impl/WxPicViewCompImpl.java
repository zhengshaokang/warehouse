package com.hys.mgt.view.comment.component.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.crypto.Base64Ext;
import com.hys.commons.logutil.LogProxy;
import com.hys.commons.otherapi.wxapi.WeiXinApiUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.select.conenum.EnumOrderPayStatus;
import com.hys.mgt.view.comment.common.OrderConverter;
import com.hys.mgt.view.comment.common.WxPicConverter;
import com.hys.mgt.view.comment.component.IWxPicViewComp;
import com.hys.mgt.view.comment.vo.OrderVo;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.common.utils.SessionUtils;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.vo.SysUserVo;
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
					pic.setPicUrl(wxPicVo.getPicUrl());
					pic.setUploadIp(wxPicVo.getUploadIp());
					pic.setUploadTime(wxPicVo.getUploadTime());
					pic.setPayStatus(EnumOrderPayStatus.NOCHECK.getValue());
					 
					Order order= orderService.queryOrderByNo(pic.getOrderNo(), pic.getUserId());
					boolean b = wxPicService.updateWxPic(pic);
					if(null != order) {
						order.setIsPay(EnumOrderPayStatus.NOCHECK.getValue());
						orderService.updateOrder(order);
					}
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
	public OrderVo validateOrder(String orderNo, Integer userId) {
		OrderVo vo = new OrderVo();
		vo.setId(-1);
		try {
			//验证订单是否存在，不能重复
			Order  pc = orderService.queryOrderByNo(orderNo, userId);
			if(null != pc) {
				 return OrderConverter.convert2Vo(pc);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	
	@Override
	public ResultPrompt updateOrderPayStatus(Integer wxPicId, Integer picSorStatus,String reason,Integer sendMassge) {
		
		
		// 查询评价订单号，跟进订单号查询订单，修改评价和订单的审核状态
		ResultPrompt rp = new ResultPrompt();
		try {
			
			HttpSession session = SessionUtils.getSession();
	    	SysUserVo sysAdminVo = (SysUserVo) session.getAttribute("sysadmin");
	    	
	    	Integer userId =Integer.parseInt(sysAdminVo.getId());
			
	    	Integer payStatus = EnumOrderPayStatus.YESPAY.getValue();
			if(picSorStatus == 0) { //审核不通过
				payStatus = EnumOrderPayStatus.NOPASS.getValue();
			}
	    	
			WxPic pic = wxPicService.queryWxPicById(wxPicId, userId);
			if(null != pic) {
				 if(LogicUtil.isNullOrEmpty(pic.getOrderNo())) {
					 rp.setStatusCode("300");
					 rp.setMessage("该数据订单号为空！");
			    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
			    	 rp.setCallbackType("closeCurrent");
			    	 return rp;
				 }
				 Order  order = orderService.queryOrderByNo(pic.getOrderNo(), userId);
				 if(LogicUtil.isNull(order)) {
					 rp.setStatusCode("300");
					 rp.setMessage("订单号对应的订单列表不存在！");
			    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
			    	 rp.setCallbackType("closeCurrent");
			    	 return rp;
				 }
				 pic.setPayStatus(payStatus);
				 boolean b = wxPicService.updateWxPic(pic);
				 order.setIsPay(payStatus);
				 boolean b1 = orderService.updateOrder(order);
				 if(b1 && b) {
					 String appId = sysAdminVo.getAppId();//"wx36c0752699ec541c";
					 String secret64 = sysAdminVo.getSecret();
					 if(LogicUtil.isNotNullAndEmpty(appId) && LogicUtil.isNotNullAndEmpty(secret64) && sendMassge == 1) {
						 String secret = new String(Base64Ext.decode(secret64),"UTF-8");//"28dba62ab2c83f7fedb3b5d9edaba30b";
						 String accessToken = WeiXinApiUtil.getAccessToken(appId,secret);
						 System.out.println("appId:" + appId);
						 System.out.println("secret:" + secret);
						 System.out.println("accessToken:" + accessToken);
						 
						 String openid = pic.getOpenId();//客户微信openid
						 String msg = "";
						 String templateId = "wj8zA-Mmk0HLD9Mt9nhbdQtP3IC8h23hZc1oGR1N6UY"; //模板消息id
						 String templateUrl = "";  //模板消息跳转url
						 Map<String,Object> miniprogram = new HashMap<String,Object>(); //模板消息跳转小程序
						 miniprogram.put("appid", "");  //小程序appid
						 miniprogram.put("pagepath", "");//小程序路径
						 Map<String,Object> data = new HashMap<String,Object>(); //模板消息数据参数
						 
						 
						 Map<String,Object> first = new HashMap<String,Object>();
						 first.put("value", reason);
						 first.put("color", "#FF0000");
						 Map<String,Object> keyword1 = new HashMap<String,Object>();
						 keyword1.put("value", "审核通过");
						 keyword1.put("color", "#000");
						 Map<String,Object> keyword2 = new HashMap<String,Object>();
						 keyword2.put("value", DateUtil.getCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
						 keyword2.put("color", "#000");
						 Map<String,Object> remark = new HashMap<String,Object>();
						 remark.put("value", "谢谢您的参与！");
						 remark.put("color", "#FF0000");
						 if(picSorStatus == 0) { //不通过
							 templateId = "XYglxw6VJOZrIOc3kEGoj_II9tH5Ev80LosTW4CkCvI";
							 templateUrl = "http://wx.vva.com.cn/wxpicadd?activ=MXxBQ1RJVjIwMTgwNzE5MTU1MDQ0";
							 keyword1.put("value", "审核不通过");
							 remark.put("value", "点击详情重新提交");
						 }
						 data.put("first", first);   
						 data.put("keyword1", keyword1);
					     data.put("keyword2", keyword2);
					     data.put("remark", remark);
						 
						 WxPicSendThread th = new WxPicSendThread(openid, accessToken, msg, templateId, templateUrl, miniprogram, data);
						 th.run();
					 }
					 
					 rp.setStatusCode("200");
					 rp.setMessage("操作成功！");
			    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
			    	 rp.setCallbackType("closeCurrent");
				 } else {
					 rp.setStatusCode("300");
			    	 rp.setMessage("操作失败！");
			    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
			    	 rp.setCallbackType("closeCurrent");
				 }
			} else {
				 rp.setStatusCode("300");
				 rp.setMessage("该条评价不存在！");
		    	 rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
		    	 rp.setCallbackType("closeCurrent");
		    	 return rp;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setNavTabId("comment/wxpic-list"); // 要刷新的tab页id
	    	rp.setCallbackType("closeCurrent");
		}
		return rp;
	}
}
