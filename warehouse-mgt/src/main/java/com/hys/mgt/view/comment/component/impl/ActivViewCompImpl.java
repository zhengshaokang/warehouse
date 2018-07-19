package com.hys.mgt.view.comment.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hys.commons.logutil.LogProxy;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.common.ActivConverter;
import com.hys.mgt.view.comment.component.IActivViewComp;
import com.hys.mgt.view.comment.vo.ActivVo;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.model.comment.Activ;
import com.hys.service.comment.IActivService;

@Component
public class ActivViewCompImpl implements IActivViewComp {
	
	private final static Logger log = LogProxy.getLogger(ActivViewCompImpl.class);
	
	@Autowired
	private IActivService activService;

	@Override
	public ResultPrompt addActiv(ActivVo activVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			boolean b = activService.addActiv(ActivConverter.convert2Do(activVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/activ-list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/activ-list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	 rp.setNavTabId("comment/activ-list"); // 要刷新的tab页id
		}
		return rp;
	}

	@Override
	public ActivVo queryActivById(Integer id, Integer userId) {
		
		return ActivConverter.convert2Vo(activService.queryActivById(id, userId));
	}
	
	@Override
	public ActivVo queryActivByCode(String code, Integer userId) {
		
		return ActivConverter.convert2Vo(activService.queryActivByCode(code, userId));
	}

	@Override
	public PageData<ActivVo> pageQueryActivs(ActivVo activVo) {
		PageData<ActivVo> pageVo = null;
        try
        {
        	Activ u = (Activ) ActivConverter.convert2Do(activVo);  
            PageParam<Activ> page = new PageParam<Activ>();
            page.setP(u);
            page.setPageNo(activVo.getPageNum());
            page.setPageSize(activVo.getNumPerPage());
            PageData<Activ> pd = activService.pageQueryActivs(page);

            List<Activ> list = pd.getPageData();
            List<ActivVo> listvo = new ArrayList<>();
            if (LogicUtil.isNotNull(list))
            {
                for (Activ user : list)
                {
                    listvo.add((ActivVo) ActivConverter.convert2Vo(user));
                }
            }
            pageVo = new PageData<ActivVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        }
        catch (Exception e)
        {
            log.debug("pageQueryUsers exception=>" + e);
        }
        return pageVo;
	}

	@Override
	public ResultPrompt updateActiv(ActivVo activVo) {
		ResultPrompt rp = new ResultPrompt();
		
		try {
			
			boolean b = activService.updateActiv(ActivConverter.convert2Do(activVo));
			
			if(b) {	
				 rp.setStatusCode("200");
				 rp.setMessage("操作成功！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/activ-list"); // 要刷新的tab页id
			 } else {
				 rp.setStatusCode("300");
				 rp.setMessage("操作失败！");
				 rp.setCallbackType("closeCurrent"); // 关闭当前窗口
				 rp.setNavTabId("comment/activ-list"); // 要刷新的tab页id
			 }
		} catch (Exception e) {
			e.printStackTrace();
		    rp.setStatusCode("300");
	    	rp.setMessage("操作失败！");
	    	rp.setCallbackType("closeCurrent"); // 关闭当前窗口
	    	 rp.setNavTabId("comment/activ-list"); // 要刷新的tab页id
		}
		return rp;
	}

}
