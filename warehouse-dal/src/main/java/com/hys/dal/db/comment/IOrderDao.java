package com.hys.dal.db.comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.comment.Order;
/**
 * 用户DAO
 * 
 */
public interface IOrderDao {
	
	
    public int addOrder(Order order);
    public int updateOrder(Order order);
    public int deleteOrder(@Param("id") Integer id,@Param("userId") Integer userId);
    public Order queryOrderById(@Param("id")Integer id,@Param("userId")Integer userId);
    public Order queryOrderByNo(@Param("orderNo")String orderNo,@Param("userId")Integer userId);
    /**
     * 分页查询
     * 
     * @param page
     *        分页参数
     * @return
     */
    public List<Order> pageQueryOrders(PageParam<Order> page);
    
  
}
