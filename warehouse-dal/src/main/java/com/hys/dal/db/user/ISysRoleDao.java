package com.hys.dal.db.user;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.hys.commons.page.PageParam;
import com.hys.model.user.SysRole;
public interface ISysRoleDao {
	/**
     * 添加系统角色
     * 
     * @param sysRole
     * @return 添加影响的行数
     */
    public int addSysRole(SysRole sysRole);

    /**
     * 更新系统角色
     * 
     * @param sysRole
     *        系统角色
     * @return 更新影响的行数
     */
    public int updateSysRole(SysRole sysRole);


    /**
     * 添加系统角色与权限
     * 
     * @param roleId
     *        系统角色Id
     * @param authId
     *        系统权限Id
     * @return 添加影响的行数
     * @date:
     */
    public int addSysRoleAuth(@Param("roleId") String roleId, @Param("authId") String authId);

    /**
     * 删除系统角色与权限
     * 
     * @param roleId
     *        系统角色Id
     * @param authId
     *        系统权限Id
     * @return 删除影响的行数
     * @date: 
     */
    public int deleteSysRoleAuth(@Param("roleId") String roleId, @Param("authId") String authId);

    /**
     * 分页查询系统角色
     * 
     * @param page
     *        分页参数
     * @return 系统角色
     * @date: 
     */
    public List<SysRole> querySysRoles(PageParam<SysRole> page);

    /**
     * 根据用户id查询角色列表
     * 
     * @param userId
     *        用户编号
     * @return 角色列表
     * @date:
     */
    public List<SysRole> queryRolesByUserId(@Param("userId") String userId);

    /**
     * 根据角色ID查询系统权限
     * 
     * @param roleId
     *        角色ID
     * @return 系统权限
     * @date: 
     */
    public List<SysRole> queryRolesByAuthId(@Param("authId") String authId);

}
