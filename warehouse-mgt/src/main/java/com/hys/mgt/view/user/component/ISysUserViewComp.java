package com.hys.mgt.view.user.component;

import org.springframework.ui.ModelMap;

import com.hys.commons.page.PageData;
import com.hys.mgt.view.common.vo.ResultPrompt;
import com.hys.mgt.view.user.vo.SysUserVo;

public interface ISysUserViewComp {
	 /**
     * 新增用户信息
     * 
     * @param user
     * @return
     */
    public ResultPrompt addUser(SysUserVo user,String addRoleId);
    
    /**
     * 修改用户信息
     * 
     * @param user
     * @return
     */
    public ResultPrompt updateUser(SysUserVo user, String[] addRoleIds, String[] delRoleIds);

   

    /**
     * 根据用户编号查询用户信息
     * 
     * @param userId
     * @return
     */
    public SysUserVo queryUserById(String userId);


    /**
     * 分页查询用户
     * 
     * @param page
     *        分页查询
     * @return
     * @author: chenjunxiu
     */
    public PageData<SysUserVo> pageQueryUsers(SysUserVo vo);

    /**
     * 删除
     * @param userId
     * @return
     */
    public ResultPrompt userDel(String userId);
    /**
     * 重置密码
     * @param user
     * @return
     */
    public ResultPrompt userResetPassWord(SysUserVo user);
    
    public ResultPrompt uppwd(SysUserVo user, String pwd);
    
    public void userUpdateOrAdd(String userId, ModelMap modelMap);
    /**
     * 查询用户审核
     * 
     * @param page
     *        分页查询
     * @return
     * @author: chenjunxiu
     */
    public PageData<SysUserVo> pageQueryCensorUsers(SysUserVo vo);

    /**
     * 修改用户审核状态
     * 
     * @param page
     *        
     * @return
     * @author: chenjunciu
     */
    public ResultPrompt updateUserCensor(String userId, SysUserVo user);
    
    

}
