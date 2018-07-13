package com.hys.service.comment;


import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.comment.WxPic;

public interface IWxPicService {
	
	
    public boolean addWxPic(WxPic wxPic);
    public boolean updateWxPic(WxPic wxPic);
    public WxPic queryWxPicByOrderNo(String orderNo,Integer userId);
    public PageData<WxPic> pageQueryWxPics(PageParam<WxPic> page);
    
  
}
