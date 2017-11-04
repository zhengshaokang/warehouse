package com.hys.service.user.component;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.user.SysUser;
public interface ISysUserService {
	/**
     * 新增用户信息
     * 
     * @param user
     * @return
     */
    public String addSysUser(SysUser user);

    /**
     * 修改用户信息
     * 
     * @param SysUser
     *        用户
     * @return 成功返回true 失败返回false
     */
    public boolean updateSysUser(SysUser user);

    
    /**
     * 根据用户ID查询用户信息
     * 
     * @param SysUserId
     *        用户ID
     * @return 用户信息
     */
    public SysUser querySysUserById(String SysUserId);

    /**
     * 根据用户呢称查询用户信息
     * 
     * @param SysUsername
     *        用户呢称
     * @return 用户信息
     */
    public SysUser querySysUserByName(String SysUsername);

    /**
     * 根据用户名称查询用户信息
     * 
     * @param email
     *        用户邮箱
     * @return 用户信息
     */
    public SysUser querySysUserByEmail(String email);

    /**
     * 根据用户手机查询用户信息
     * 
     * @param phone
     *        用户手机
     * @return 用户信息
     */
    public SysUser querySysUserByPhone(String phone);


    /**
     * 分页查询用户
     * 
     * @param page
     *        分页查询
     * @return
     */
    public PageData<SysUser> pageQuerySysUsers(PageParam< SysUser> page);
    /**
     * 用户登陆
     * 
     * @param page
     *        分页查询
     * @return
     */
    public SysUser querySysUser4Login(SysUser user);
    /**
     * 分页用户审核
     * 
     * @param page
     *        分页查询
     * @return
     */
    public PageData<SysUser> pageQuerySysCensorUsers(PageParam<SysUser> page);
    

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public SysUser prosceniumLogin(String username, Integer idType);
}
