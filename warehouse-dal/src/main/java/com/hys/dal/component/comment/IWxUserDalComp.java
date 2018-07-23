package com.hys.dal.component.comment;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.comment.WxUser;

public interface IWxUserDalComp {
	
	
    public int addWxUser(WxUser wxUser);
    public boolean updateWxUser(WxUser wxUser);
    public WxUser queryWxUserByOpendId(String openId,Integer userId);
    public PageData<WxUser> pageQueryWxUsers(PageParam<WxUser> page);
    
  
}
