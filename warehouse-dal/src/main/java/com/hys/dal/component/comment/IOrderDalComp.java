package com.hys.dal.component.comment;


import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.comment.Order;

public interface IOrderDalComp {
	
	
    public int addOrder(Order order);
    public boolean updateOrder(Order order);
    public boolean deleteOrder(Integer id,Integer userId);
    public Order queryOrderByNo(String orderNo,Integer userId);
    public Order queryOrderById(Integer id,Integer userId);
    public PageData<Order> pageQueryOrders(PageParam<Order> page);
    
  
}
