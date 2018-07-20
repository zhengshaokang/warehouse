package com.hys.dal.component.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.comment.IWxPicDalComp;
import com.hys.dal.db.comment.IWxPicDao;
import com.hys.model.comment.WxPic;

@Component
public class WxPicDalCompImpl implements IWxPicDalComp {
	
	@Autowired
	private IWxPicDao picDao;

	@Override
	public int addWxPic(WxPic wxPic) {
		picDao.addWxPic(wxPic);
		return wxPic.getId();
	}

	@Override
	public boolean updateWxPic(WxPic wxPic) {
		return JdbcUtil.isSuccess(picDao.updateWxPic(wxPic));
	}

	@Override
	public WxPic queryWxPicByOrderNo(String orderNo, Integer userId) {
		return picDao.queryWxPicByOrderNo(orderNo, userId);
	}

	@Override
	public PageData<WxPic> pageQueryWxPics(PageParam<WxPic> page) {
		List<WxPic> pics = picDao.pageQueryWxPics(page);
		return new PageData<WxPic>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), pics);
	}

	@Override
	public WxPic queryWxPicById(Integer id, Integer userId) {
		return picDao.queryWxPicById(id, userId);
	}

}
