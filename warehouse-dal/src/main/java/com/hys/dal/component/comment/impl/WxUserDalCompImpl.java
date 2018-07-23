package com.hys.dal.component.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.comment.IWxUserDalComp;
import com.hys.dal.db.comment.IWxUserDao;
import com.hys.model.comment.WxUser;
@Component
public class WxUserDalCompImpl implements IWxUserDalComp {
	@Autowired
	private IWxUserDao wxUserDao;

	@Override
	public int addWxUser(WxUser wxUser) {
		wxUserDao.addWxUser(wxUser);
		return wxUser.getId();
	}

	@Override
	public boolean updateWxUser(WxUser wxUser) {
		return JdbcUtil.isSuccess(wxUserDao.updateWxUser(wxUser));
	}

	@Override
	public PageData<WxUser> pageQueryWxUsers(PageParam<WxUser> page) {
		List<WxUser> users = wxUserDao.pageQueryWxUsers(page);
		return new PageData<WxUser>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), users);
	}

	@Override
	public WxUser queryWxUserByOpendId(String openId, Integer userId) {
		return wxUserDao.queryWxUserByOpendId(openId, userId);
	}

}
