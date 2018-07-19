package com.hys.dal.db.comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.comment.Shop;
/**
 * 用户DAO
 * 
 */
public interface IShopDao {
	
	
    public int addShop(Shop shop);
    public int updateShop(Shop shop);
    public int deleteShop(@Param("id") Integer id,@Param("userId") Integer userId);
    public Shop queryShopById(@Param("id")Integer id,@Param("userId")Integer userId);
    public List<Shop> queryShopByUserId(@Param("userId")Integer userId);
    public List<Shop> queryShopByUserIdAndPlatfrom(@Param("userId")Integer userId,@Param("platform")Integer platform);
    public Shop validateShopName(@Param("name")String name,@Param("platform")Integer platform,@Param("userId")Integer userId);
    /**
     * 分页查询
     * 
     * @param page
     *        分页参数
     * @return
     */
    public List<Shop> pageQueryShops(PageParam<Shop> page);
    
  
}
