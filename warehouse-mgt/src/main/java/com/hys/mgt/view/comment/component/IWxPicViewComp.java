package com.hys.mgt.view.comment.component;


import com.hys.commons.page.PageData;
import com.hys.mgt.view.comment.vo.OrderVo;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.common.vo.ResultPrompt;

public interface IWxPicViewComp {
	
    public ResultPrompt addOrUpdateWxPic(WxPicVo wxPicVo);
    public PageData<WxPicVo> pageQueryWxPics(WxPicVo wxPicVo);
    public OrderVo validateOrder(String orderNo,Integer userId);
    /**
     * 
     * @param wxPicId  评价记录id
     * @param picSorStatus 审核状态
     * @param reason  审核原因
     * @param sendMassge  是否发送通知
     * @return
     */
    public ResultPrompt updateOrderPayStatus(Integer wxPicId,Integer picSorStatus,String reason,Integer sendMassge);
}
