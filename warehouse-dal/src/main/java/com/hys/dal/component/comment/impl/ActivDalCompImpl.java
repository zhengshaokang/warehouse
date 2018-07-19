package com.hys.dal.component.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.jdbc.JdbcUtil;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.dal.component.comment.IActivDalComp;
import com.hys.dal.db.comment.IActivDao;
import com.hys.model.comment.Activ;

@Component
public class ActivDalCompImpl implements IActivDalComp {
	
	@Autowired
	private IActivDao activDao;

	@Override
	public int addActiv(Activ activ) {
		activDao.addActiv(activ);
		return activ.getId();
	}

	@Override
	public boolean updateActiv(Activ activ) {
		return JdbcUtil.isSuccess(activDao.updateActiv(activ));
	}

	@Override
	public Activ queryActivById(Integer id, Integer userId) {
		return activDao.queryActivById(id, userId);
	}
	
	@Override
	public Activ queryActivByCode(String code, Integer userId) {
		return activDao.queryActivByCode(code, userId);
	}

	@Override
	public PageData<Activ> pageQueryActivs(PageParam<Activ> page) {
		List<Activ> activs = activDao.pageQueryActivs(page);
		return new PageData<Activ>(page.getPageNo(), page.getPageSize(), page.getDataTotal(), activs);
	}
}
