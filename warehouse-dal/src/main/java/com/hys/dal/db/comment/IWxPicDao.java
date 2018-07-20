package com.hys.dal.db.comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.comment.WxPic;
/**
 * 用户DAO
 * 
 */
public interface IWxPicDao {
	
	
    public int addWxPic(WxPic wxPic);
    public int updateWxPic(WxPic wxPic);
    public WxPic queryWxPicByOrderNo(@Param("orderNo")String orderNo,@Param("userId")Integer userId);
    public WxPic queryWxPicById(@Param("id")Integer id,@Param("userId")Integer userId);
    /**
     * 分页查询
     * 
     * @param page
     *        分页参数
     * @return
     */
    public List<WxPic> pageQueryWxPics(PageParam<WxPic> page);
    
  
}
