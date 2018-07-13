package com.hys.service.comment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.comment.IWxUserDalComp;
import com.hys.model.comment.WxUser;
import com.hys.service.comment.IWxUserService;

@Service
public class WxUserServiceImpl implements IWxUserService {
	
	@Autowired
	private IWxUserDalComp wxUserDalComp;

	@Override
	public boolean addWxUser(WxUser wxUser) {
		if(LogicUtil.isNull(wxUser)) {
			return false;
		}
		Integer id = wxUserDalComp.addWxUser(wxUser);
		if(id != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateWxUser(WxUser wxUser) {
		if(LogicUtil.isNull(wxUser) || LogicUtil.isNull(wxUser.getId())) {
			return false;
		}
		return wxUserDalComp.updateWxUser(wxUser);
	}

	@Override
	public PageData<WxUser> pageQueryWxUsers(PageParam<WxUser> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return wxUserDalComp.pageQueryWxUsers(page);
	}

}
