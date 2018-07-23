package com.hys.service.comment.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hys.commons.crypto.Base64Ext;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.dal.component.comment.IActivDalComp;
import com.hys.model.comment.Activ;
import com.hys.service.comment.IActivService;

@Service
public class ActivServiceImpl implements IActivService {
	
	@Autowired
	private IActivDalComp activDalComp;

	@Override
	public boolean addActiv(Activ activ) {
		if(LogicUtil.isNull(activ)) {
			return false;
		}
		String code = activ.getUserId()+"ACTIV"+DateUtil.getCurrentDateTime("yyyyMMddHHmmss");
		String base64Code = Base64Ext.encode((activ.getUserId() + "|"+code).getBytes());
	    String wxlink = "wxpicadd?activ=" + base64Code;
		activ.setWxLink(wxlink);
		activ.setCode(code);
		Integer id = activDalComp.addActiv(activ);
		if(id != null){
			return true;
		}
		return false ;
	}

	@Override
	public boolean updateActiv(Activ activ) {
		if(LogicUtil.isNull(activ)) {
			return false;
		}
		return activDalComp.updateActiv(activ);
	}

	@Override
	public Activ queryActivById(Integer id, Integer userId) {
		
		if(LogicUtil.isNull(id) || LogicUtil.isNull(userId)) {
			return null;
		}
		
		return activDalComp.queryActivById(id, userId);
	}
	
	@Override
	public Activ queryActivByCode(String code, Integer userId) {
		
		if(LogicUtil.isNullOrEmpty(code) || LogicUtil.isNull(userId)) {
			return null;
		}
		
		return activDalComp.queryActivByCode(code, userId);
	}

	@Override
	public PageData<Activ> pageQueryActivs(PageParam<Activ> page) {
		if (LogicUtil.isNull(page) || LogicUtil.isNull(page.getP())){
            return null;
        }
		return activDalComp.pageQueryActivs(page);
	}
}
