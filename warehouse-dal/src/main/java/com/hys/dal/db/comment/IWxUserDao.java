package com.hys.dal.db.comment;

import java.util.List;


import com.hys.commons.page.PageParam;
import com.hys.model.comment.WxUser;
/**
 * 用户DAO
 * 
 */
public interface IWxUserDao {
	
	
    public int addWxUser(WxUser wxUser);
    public int updateWxUser(WxUser wxUser);
    /**
     * 分页查询
     * 
     * @param page
     *        分页参数
     * @return
     */
    public List<WxUser> pageQueryWxUsers(PageParam<WxUser> page);
    
  
}
