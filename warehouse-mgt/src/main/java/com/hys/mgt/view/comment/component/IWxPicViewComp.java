package com.hys.mgt.view.comment.component;


import com.hys.commons.page.PageData;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.common.vo.ResultPrompt;

public interface IWxPicViewComp {
	
    public ResultPrompt addOrUpdateWxPic(WxPicVo wxPicVo);
    public PageData<WxPicVo> pageQueryWxPics(WxPicVo wxPicVo);
    public ResultPrompt validateOrder(String orderNo,Integer userId);
    public ResultPrompt updateOrderPayStatus(Integer wxPicId,Integer payStatus, Integer userId);
}
