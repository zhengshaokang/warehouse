package com.hys.dal.db.user;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.hys.commons.page.PageParam;
import com.hys.model.user.SysAuth;
public interface ISysAuthDao {
	 /**
     * 添加系统权限
     * 
     * @param sysAuth
     *        系统权限
     * @return 添加影响的行数
     * @date:
     */
    public int addSysAuth(SysAuth sysAuth);

    /**
     * 删除系统权限
     * 
     * @param authId
     *        系统权限Id
     * @return 删除影响的行数
     * @date: 
     */
    public int deleteSysAuth(@Param("authId") String authId);

    /**
     * 更新系统权限
     * 
     * @param sysAuth
     *        系统权限
     * @return 更新影响的行数
     * @date:
     */
    public int updateSysAuth(SysAuth sysAuth);

    /**
     * 查询权限表
     * 
     * @param authId
     * @return
     * @date: 
     */
    public SysAuth querySysAuth(@Param("authId") String authId);

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
    public List<SysAuth> queryAuthsByUserId(@Param("userId") String userId);
    
    /**
     * 根据角色ID查询系统权限
     * 
     * @param roleId
     *        角色ID
     * @return 系统权限
     * @date:
     */
    public List<SysAuth> queryAuthsByRoleId(@Param("roleId") String roleId);
    /**
     * 分页查询列表
     * 
     * @param page
     *        分页参数
     * @return
     */
    public List<SysAuth> PageQuerySysAuth(PageParam<SysAuth> page);
    
}
