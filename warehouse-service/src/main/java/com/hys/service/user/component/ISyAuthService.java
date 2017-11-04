package com.hys.service.user.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.user.SysAuth;
import com.hys.model.user.SysUser;



public interface ISyAuthService {
	/**
     * 添加系统权限
     * 
     * @param sysAuth
     *        系统权限
     * @return 权限ID
     * @date: 
     */
    public String addSysAuth(SysAuth sysAuth);

    /**
     * 删除系统权限
     * 
     * @param authId
     *        系统权限Id
     * @return 删除影响的行数
     * @date: 
     */
    public boolean deleteSysAuth(String authId);

    /**
     * 更新系统权限
     * 
     * @param sysAuth
     *        系统权限
     * @return 更新影响的行数
     * @date:
     */
    public boolean updateSysAuth(SysAuth sysAuth);

    /**
     * 查询权限表
     * 
     * @param authId
     * @return
     * @date:
     */
    public SysAuth querySysAuth(String authId);

    /**
     * 查询所有系统权限
     * 
     * @return
     * @date: 
     */
    public List<SysAuth> querySysAuths();

    /**
     * 根据用户查询按树形结构排好序的权限列表
     * 
     * @param userId
     *        用户ID
     * @return 按树形结构排好序的权限列表
     * @date: 
     */
    public List<SysAuth> queryAuthsByUserId(String userId);
    

    /**
     * 根据帐号密码查询
     * 
     * @param username
     *        用户帐号
     * @param password
     *        用户密码
     * @return 系统管理员信息
     */
    public SysUser querySysUserByUsernamePwd(String username, String password);
    
	 /**
     * 分页查询
     * 
     * @param page
     *        分页查询
     * @return
     */
    public PageData<SysAuth> pageQuerySysAuth(PageParam<SysAuth> page);


    
}
