package com.hys.mgt.view.comment.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.vo.OrderVo;
import com.hys.model.comment.Order;

public class OrderConverter {

	public static Order convert2Do(OrderVo orderVo){
    	  if (LogicUtil.isNull(orderVo)){
              return null;
          }
    	  Order order = new Order();
    	  order.setId(orderVo.getId());
    	  order.setCustomerMobile(orderVo.getCustomerMobile());
    	  order.setCustomerName(orderVo.getCustomerName());
    	  order.setIsJoin(orderVo.getIsJoin());
    	  order.setIsPay(orderVo.getIsPay());
    	  order.setOrderNo(orderVo.getOrderNo()!=null ? orderVo.getOrderNo().trim():"");
    	  order.setOrderStatus(orderVo.getOrderStatus());
    	  order.setOrderTime(orderVo.getOrderTime());
    	  order.setRemark(orderVo.getRemark());
    	  order.setShopId(orderVo.getShopId());
    	  order.setUserId(orderVo.getUserId());
    	  
          return order;
    }

    
    public static OrderVo convert2Vo(Order order){
        if (LogicUtil.isNull(order)){
        	return null;
        }
        OrderVo orderVo = new OrderVo();
        orderVo.setId(order.getId());
        orderVo.setCustomerMobile(order.getCustomerMobile());
        orderVo.setCustomerName(order.getCustomerName());
        orderVo.setIsJoin(order.getIsJoin());
        orderVo.setIsPay(order.getIsPay());
        orderVo.setOrderNo(order.getOrderNo());
        orderVo.setOrderStatus(order.getOrderStatus());
        orderVo.setOrderTime(order.getOrderTime());
        orderVo.setRemark(order.getRemark());
        orderVo.setShopId(order.getShopId());
        orderVo.setUserId(order.getUserId());
        return orderVo;
    }

}
