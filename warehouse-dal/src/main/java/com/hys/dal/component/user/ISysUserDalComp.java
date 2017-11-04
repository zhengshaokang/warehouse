package com.hys.dal.component.user;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.user.SysUser;

public interface ISysUserDalComp {
	 /**
     * 新增用户信息
     * 
     * @param user
     * @return
     */
    public String addSysUser(SysUser sysUser);

    /**
     * 修改用户信息
     * 
     * @param SysUser
     * @return
     */
    public boolean updateSysUser(SysUser sysUser);

    /**
     * 根据用户编号查询用户信息
     * 
     * @param uid为
     *        ：用户ID，openid，email，phone都可以登录
     * @return
     */
    public SysUser querySysUser(SysUser sysUser);

    public SysUser querySysUserLogin(SysUser sysuser);
    public List<SysUser> querySysUsers();
    /**
     * 分页查询用户列表
     * 
     * @param page
     *        分页参数
     * @return
     */
    public PageData<SysUser> pageQuerySysUsers(PageParam<SysUser> page);
    
    /**
     * 用户登陆
     * 
     * @param page
     *        分页查询
     * @return
     */
    public SysUser querySysUser4Login(SysUser user);

    /**
     * 分页查询用户审核列表
     * 
     * @param page
     *        分页参数
     * @return
     */
    public PageData<SysUser> pageQuerySysCensorUsers(PageParam<SysUser> page);
    

    /**
     * 查询前台用户，根据用户名
     * @param username
     * @return
     */
    public SysUser prosceniumLogin(String username, Integer idType);
}
