package com.hys.mgt.view.comment.component;


import com.hys.commons.page.PageData;
import com.hys.mgt.view.comment.vo.ActivVo;
import com.hys.mgt.view.common.vo.ResultPrompt;

public interface IActivViewComp {
	
    public ResultPrompt addActiv(ActivVo activVo);
    public ResultPrompt updateActiv(ActivVo activVo);
    public ActivVo queryActivById(Integer id,Integer userId);
    public ActivVo queryActivByCode(String code,Integer userId);
    public PageData<ActivVo> pageQueryActivs(ActivVo activVo);
  
}
