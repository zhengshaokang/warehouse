package com.hys.dal.component.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.comment.IOrderDalComp;
import com.hys.dal.db.comment.IOrderDao;
import com.hys.model.comment.Order;

@Component
public class OrderDalCompImpl implements IOrderDalComp {
	
	@Autowired
	private IOrderDao orderDao;

	@Override
	public int addOrder(Order order) {
		orderDao.addOrder(order);
		return order.getId();
	}

	@Override
	public boolean updateOrder(Order order) {
		return JdbcUtil.isSuccess(orderDao.updateOrder(order));
	}

	@Override
	public Order queryOrderByNo(String orderNo, Integer userId) {
		return orderDao.queryOrderByNo(orderNo, userId);
	}

	@Override
	public PageData<Order> pageQueryOrders(PageParam<Order> page) {
		List<Order> orders = orderDao.pageQueryOrders(page);
		return new PageData<Order>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), orders);
	}

	@Override
	public boolean deleteOrder(Integer id, Integer userId) {
		return JdbcUtil.isSuccess(orderDao.deleteOrder(id, userId));
	}

	@Override
	public Order queryOrderById(Integer id, Integer userId) {
		return orderDao.queryOrderById(id, userId);
	}

}
