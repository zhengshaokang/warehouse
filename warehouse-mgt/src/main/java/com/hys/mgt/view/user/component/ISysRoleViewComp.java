package com.hys.mgt.view.user.component;

import java.util.List;

import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.user.SysAuth;
import com.hys.model.user.SysRole;
import com.hys.model.user.SysUser;

/**
 * 系统角色
 * 
 * @author: zhengshaokang
 */
public interface ISysRoleViewComp
{

	 /**
     * 添加系统角色
     * 
     * @param sysRole
     * @return 成功true，失败为false
     * @author: hualong
     * @date: 2014年3月22日下午3:33:30
     */
    public String addSysRole(SysRole sysRole);

    /**
     * 更新系统角色
     * 
     * @param sysRole
     *        系统角色
     * @return 成功true，失败为false
     * @author: hualong
     * @date: 2014年3月22日下午3:35:27
     */
    public boolean updateSysRole(SysRole sysRole);

    /**
     * 添加系统角色与用户关联
     * 
     * @param addRoleIds
     *        待增系统角色Id
     * @param delRoleIds
     *        待删系统角色Id
     * @param userId
     *        用户Id
     * @return 成功true，失败为false
     * @author: hualong
     * @date: 2014年3月22日下午3:35:57
     */
    public boolean updateUserSysRole(String userId, List<String> addRoleIds, List<String> delRoleIds);

    /**
     * 更新系统角色与权限
     * 
     * @param roleId
     *        系统角色
     * @param addAuthIds
     *        待添加的权限
     * @param delAuthIds
     *        待删除的权限
     * @return 成功true，失败为false
     * @author: hualong
     * @date: 2014年3月22日下午4:53:47
     */
    public boolean updateSysRoleAuth(String roleId, List<String> addAuthIds, List<String> delAuthIds);

    /**
     * 分页查询系统角色
     * 
     * @param page
     *        分页参数
     * @return 系统角色
     * @author: hualong
     * @date: 2014年3月22日下午3:39:44
     */
    public PageData<SysRole> querySysRoles(PageParam< SysRole> page);

    /**
     * 依据用户角色查询用户列表
     * 
     * @param roleId
     *        角色编号
     * @return 用户
     * @author: hualong
     * @date: 2014年3月22日下午3:42:10
     */
    public List<SysUser> queryUsersByRoleId(String roleId);

    /**
     * 根据用户id查询角色列表
     * 
     * @param userId
     *        用户编号
     * @return 角色列表
     * @author: hualong
     * @date: 2014年3月22日下午3:48:14
     */
    public List<SysRole> queryRolesByUserId(String userId);

    /**
     * 根据权限id查询角色列表
     * 
     * @param authId
     *        权限id
     * @return 角色列表
     * @author: hualong
     * @date: 2014年3月22日下午3:48:14
     */
    public List<SysRole> queryRolesByAuthId(String authId);

    /**
     * 根据角色id查询权限列表
     * 
     * @param roleId
     *        角色编号
     * @return 权限列表
     * @author: hualong
     * @date: 2014年3月22日下午3:48:14
     */
    public List<SysAuth> queryAuthsByRoleId(String roleId);

}
