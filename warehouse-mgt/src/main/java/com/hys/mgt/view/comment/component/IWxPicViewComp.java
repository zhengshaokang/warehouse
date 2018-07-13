package com.hys.mgt.view.comment.component;


import com.hys.commons.page.PageData;
import com.hys.mgt.view.comment.vo.WxPicVo;
import com.hys.mgt.view.common.vo.ResultPrompt;

public interface IWxPicViewComp {
	
    public ResultPrompt addOrUpdateWxPic(WxPicVo wxPicVo);
    public PageData<WxPicVo> pageQueryWxPics(WxPicVo wxPicVo);
  
}
