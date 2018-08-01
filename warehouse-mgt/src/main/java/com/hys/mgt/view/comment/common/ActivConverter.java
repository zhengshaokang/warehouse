package com.hys.mgt.view.comment.common;

import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.comment.vo.ActivVo;
import com.hys.model.comment.Activ;

public class ActivConverter {

	public static Activ convert2Do(ActivVo activVo){
    	  if (LogicUtil.isNull(activVo)){
              return null;
          }
    	  Activ activ = new Activ();
    	  activ.setId(activVo.getId());
    	  activ.setCreateTime(activVo.getCreateTime());
    	  activ.setUserId(activVo.getUserId());
    	  activ.setBgPath(activVo.getBgPath());
    	  activ.setName(activVo.getName()!=null?activVo.getName().trim():"");
    	  activ.setTitle(activVo.getTitle()!=null?activVo.getTitle().trim():"");
    	  activ.setDescription(activVo.getDescription());
    	  activ.setEndTime(activVo.getEndTime());
    	  activ.setStartTime(activVo.getStartTime());
    	  activ.setWorkflowPath(activVo.getWorkflowPath());
    	  activ.setWxLink(activVo.getWxLink());
    	  activ.setCode(activVo.getCode());
    	  activ.setSuBgPath(activVo.getSuBgPath());
    	  activ.setQcardPath(activVo.getQcardPath());
          return activ;
    }

    
    public static ActivVo convert2Vo(Activ activ){
        if (LogicUtil.isNull(activ)){
        	return null;
        }
        ActivVo activVo = new ActivVo();
        activVo.setId(activ.getId());
        activVo.setCreateTime(activ.getCreateTime());
        activVo.setUserId(activ.getUserId());
        activVo.setBgPath(activ.getBgPath());
        activVo.setName(activ.getName()!=null?activ.getName().trim():"");
        activVo.setTitle(activ.getTitle()!=null?activ.getTitle().trim():"");
        activVo.setDescription(activ.getDescription());
        activVo.setEndTime(activ.getEndTime());
        activVo.setStartTime(activ.getStartTime());
        activVo.setWorkflowPath(activ.getWorkflowPath());
        activVo.setWxLink(activ.getWxLink());
        activVo.setCode(activ.getCode());
        activVo.setSuBgPath(activ.getSuBgPath());
        activVo.setQcardPath(activ.getQcardPath());
        return activVo;
    }

}
