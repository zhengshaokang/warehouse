package com.hys.service.comment;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.comment.WxUser;

public interface IWxUserService {
	
	
    public boolean addWxUser(WxUser wxUser);
    public boolean updateWxUser(WxUser wxUser);
    public PageData<WxUser> pageQueryWxUsers(PageParam<WxUser> page);
    public WxUser queryWxUserByOpendId(String openId,Integer userId);
}
