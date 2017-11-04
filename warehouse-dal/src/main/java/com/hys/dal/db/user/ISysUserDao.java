package com.hys.dal.db.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hys.commons.page.PageParam;
import com.hys.model.user.SysUser;
/**
 * 用户DAO
 * 
 */
public interface ISysUserDao {
	
	
	/**
     * 新增用户信息
     * 
     * @param sysuser
     * @return 影响的行数
     */
    public int addSysUser(SysUser user);

    /**
     * 修改用户信息
     * 
     * @param sysuser
     * @return 影响的行数
     */
    public int updateSysUser(SysUser sysUser);
    /**
     * 根据SysUserid、SysUsername、email、phone、openid用户
     * 
     * @param SysUser
     *        用户ID，openid，email，phone
     * @return
     */
    public SysUser querySysUser(SysUser sysUser);
    /**
     * 分页查询用户列表
     * 
     * @param page
     *        分页参数
     * @return
     */
    public List<SysUser> pageQuerySysUsers(PageParam<SysUser> page);
    /**
     * 用户登陆
     * 
     * @param page
     *        分页查询
     * @return
     */
    public SysUser querySysUser4Login(SysUser user);
    /**
     * 用户审核
     * 
     * @param user
     *        
     * @return
     */
    public List<SysUser> querySysCensorUser(PageParam<SysUser> page);
    /**
     *  登录用户查询
     * 
     * @param user
     *        
     * @return
     */
    
    public SysUser querySysUserLogin(SysUser sysuser);
    
    
    /**
     * 添加系统角色与用户关联
     * 
     * @param roleId
     *        系统角色Id
     * @param userId
     *        用户Id
     * @return 添加影响的行数
     */
    public int addSysRoleUser(@Param("roleId") String roleId, @Param("userId") String userId);

    /**
     * 删除系统角色与用户关联
     * 
     * @param roleId
     *        系统角色Id
     * @param userId
     *        用户Id
     * @return 删除影响的行数
     */
    public int deleteSysRoleUser(@Param("roleId") String roleId, @Param("userId") String userId);
    
    
    /**
     * 用于前台登陆,根据用户名查询用户
     * @param username
     * @return SysUser
     */
    public SysUser prosceniumLogin(@Param("username") String username, @Param("idType")Integer idType);
    
    public List<SysUser> querySysUsers();  
  
}
