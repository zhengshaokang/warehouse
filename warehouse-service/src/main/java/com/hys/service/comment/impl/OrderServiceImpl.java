package com.hys.service.comment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.comment.IOrderDalComp;
import com.hys.model.comment.Order;
import com.hys.service.comment.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Autowired
	private IOrderDalComp orderDalComp;
	
	@Override
	public boolean addOrder(Order order) {
		if(LogicUtil.isNull(order)) {
			return false;
		}
		Integer id = orderDalComp.addOrder(order);
		if(id != null){
			return true;
		}
		return false ;
	}

	@Override
	public boolean updateOrder(Order order) {
		if(LogicUtil.isNull(order)) {
			return false;
		}
		return orderDalComp.updateOrder(order);
	}

	@Override
	public Order queryOrderByNo(String orderNo, Integer userId) {
		if(LogicUtil.isNull(orderNo) || LogicUtil.isNull(userId)) {
			return null;
		}
		return orderDalComp.queryOrderByNo(orderNo, userId);
	}

	@Override
	public PageData<Order> pageQueryOrders(PageParam<Order> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return orderDalComp.pageQueryOrders(page);
	}

	@Override
	public boolean deleteOrder(Integer id, Integer userId) {
		if(LogicUtil.isNull(id) || LogicUtil.isNull(userId)) {
			return false;
		}
		return orderDalComp.deleteOrder(id, userId);
	}

	@Override
	public Order queryOrderById(Integer id, Integer userId) {
		if(LogicUtil.isNull(id) || LogicUtil.isNull(userId)) {
			return null;
		}
		return orderDalComp.queryOrderById(id, userId);
	}

}
