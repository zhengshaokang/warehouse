package com.hys.service.comment.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.comment.IWxPicDalComp;
import com.hys.model.comment.WxPic;
import com.hys.service.comment.IWxPicService;

@Service
public class WxPicServiceImpl implements IWxPicService {
	@Autowired
	private IWxPicDalComp wxPicDalComp;

	@Override
	public boolean addWxPic(WxPic wxPic) {
		if(LogicUtil.isNull(wxPic)) {
			return false;
		}
		Integer id = wxPicDalComp.addWxPic(wxPic);
		if(id != null){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateWxPic(WxPic wxPic) {
		if(LogicUtil.isNull(wxPic) || LogicUtil.isNull(wxPic.getId())) {
			return false;
		}
		return wxPicDalComp.updateWxPic(wxPic);
	}

	@Override
	public WxPic queryWxPicByOrderNo(String orderNo, Integer userId) {
		if(LogicUtil.isNull(orderNo) || LogicUtil.isNull(userId)){
			return null;
		}
		return wxPicDalComp.queryWxPicByOrderNo(orderNo, userId);
	}

	@Override
	public PageData<WxPic> pageQueryWxPics(PageParam<WxPic> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return wxPicDalComp.pageQueryWxPics(page);
	}

	@Override
	public WxPic queryWxPicById(Integer id, Integer userId) {
		if(LogicUtil.isNull(id) || LogicUtil.isNull(userId)){
			return null;
		}
		return wxPicDalComp.queryWxPicById(id, userId);
	}

}
