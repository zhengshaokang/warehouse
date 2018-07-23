package com.hys.dal.db.comment;

import java.util.List;



import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.comment.WxUser;
/**
 * 用户DAO
 * 
 */
public interface IWxUserDao {
	
	
    public int addWxUser(WxUser wxUser);
    public int updateWxUser(WxUser wxUser);
    public WxUser queryWxUserByOpendId(@Param("openId")String openId,@Param("userId") Integer userId);
    /**
     * 分页查询
     * 
     * @param page
     *        分页参数
     * @return
     */
    public List<WxUser> pageQueryWxUsers(PageParam<WxUser> page);
    
  
}
