package com.hys.dal.db.comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.comment.Activ;
/**
 * 用户DAO
 * 
 */
public interface IActivDao {
	
	
    public int addActiv(Activ activ);
    public int updateActiv(Activ activ);
    public Activ queryActivById(@Param("id")Integer id,@Param("userId")Integer userId);
    public Activ queryActivByCode(@Param("code")String code,@Param("userId")Integer userId);
    /**
     * 分页查询
     * 
     * @param page
     *        分页参数
     * @return
     */
    public List<Activ> pageQueryActivs(PageParam<Activ> page);
    
  
}
