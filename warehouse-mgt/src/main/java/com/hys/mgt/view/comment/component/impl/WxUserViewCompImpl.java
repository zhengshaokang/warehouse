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
import com.hys.mgt.view.comment.common.WxUserConverter;
import com.hys.mgt.view.comment.component.IWxUserViewComp;
import com.hys.mgt.view.comment.vo.WxUserVo;
import com.hys.model.comment.WxUser;
import com.hys.service.comment.IWxUserService;

@Component
public class WxUserViewCompImpl implements IWxUserViewComp {
	
	private final static Logger log = LogProxy.getLogger(WxUserViewCompImpl.class);
	
	@Autowired
	private IWxUserService wxUserService;

	@Override
	public PageData<WxUserVo> pageQueryWxUsers(WxUserVo wxUserVo) {
		PageData<WxUserVo> pageVo = null;
        try
        {
        	WxUser u = (WxUser) WxUserConverter.convert2Do(wxUserVo);  
            PageParam<WxUser> page = new PageParam<WxUser>();
            page.setP(u);
            page.setPageNo(wxUserVo.getPageNum());
            page.setPageSize(wxUserVo.getNumPerPage());
            PageData<WxUser> pd = wxUserService.pageQueryWxUsers(page);

            List<WxUser> list = pd.getPageData();
            List<WxUserVo> listvo = new ArrayList<WxUserVo>();
            if (LogicUtil.isNotNull(list))
            {
                for (WxUser user : list)
                {
                    listvo.add((WxUserVo) WxUserConverter.convert2Vo(user));
                }
            }
            pageVo = new PageData<WxUserVo>(pd.getPageNo(), pd.getPageSize(), pd.getDataTotal(), listvo);
        }
        catch (Exception e)
        {
            log.debug("pageQueryWxUsers exception=>" + e);
        }
        return pageVo;
	}

}
