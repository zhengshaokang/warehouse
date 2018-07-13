package com.hys.mgt.view.comment.component;


import com.hys.commons.page.PageData;
import com.hys.mgt.view.comment.vo.WxUserVo;

public interface IWxUserViewComp {
	
    public PageData<WxUserVo> pageQueryWxUsers(WxUserVo wxUserVo);
  
}
