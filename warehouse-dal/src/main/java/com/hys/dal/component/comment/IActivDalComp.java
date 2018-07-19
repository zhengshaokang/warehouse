package com.hys.dal.component.comment;





import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.comment.Activ;

public interface IActivDalComp {
	
    
    public int addActiv(Activ activ);
    public boolean updateActiv(Activ activ);
    public Activ queryActivById(Integer id,Integer userId);
    public Activ queryActivByCode(String code,Integer userId);
    public PageData<Activ> pageQueryActivs(PageParam<Activ> page);
    
  
}
