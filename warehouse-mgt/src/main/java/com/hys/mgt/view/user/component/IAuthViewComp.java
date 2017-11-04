package com.hys.mgt.view.user.component;

import java.util.List;

import org.springframework.ui.ModelMap;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.vo.SysAuthVo;
import com.hys.model.user.SysAuth;


public interface IAuthViewComp {
	 /**
     * 
     * 
     * @param page
     *        查询
     * @return
     * @author: chenjunxiu
     */
	public List<SysAuth> queryAll();
	
	 /**
     * 分页查询
     * 
     * @param page
     *        分页查询
     * @return
     * @author: chenjunxiu
     */
    public PageData<SysAuthVo> pageQuerySysAuth(SysAuthVo vo);
	 /**
     * 新增权限信息
     * 
     * @param user
     * @return
     */
    public ResultPrompt addAuth(SysAuthVo user);
    
    /**
     * 修改权限信息
     * 
     * @param user
     * @return
     */
    public ResultPrompt updateUser(SysAuthVo user);
    /**
     * 查询权限信息
     * 
     * @param user
     * @return
     */
    public SysAuthVo QuerySysAuth(String userId,ModelMap modelMap);

    /**
     * 删除权限信息
     * 
     * @param user
     * @return
     */
    public ResultPrompt authDel(String userId);
    
    
   

}
