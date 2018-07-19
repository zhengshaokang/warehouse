package com.hys.mgt.view.comment.component;


import org.springframework.web.multipart.MultipartFile;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.comment.vo.OrderVo;
import com.hys.mgt.view.common.vo.ResultPrompt;

public interface IOrderViewComp {
	
	
    public ResultPrompt addOrder(OrderVo orderVo);
    public ResultPrompt updateOrder(OrderVo orderVo);
    public OrderVo queryOrderById(Integer id,Integer userId);
    public ResultPrompt deleteOrder(Integer id,Integer userId);
    public PageData<OrderVo> pageQueryOrders(OrderVo vo);
    public ResultPrompt uploadOrder(MultipartFile file,Integer userId,Integer shopId,Integer platform);
  
}
