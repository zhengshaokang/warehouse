package com.hys.dal.component.user;
import java.util.List;
import com.hys.commons.page.PageData;
import com.hys.commons.page.PageParam;
import com.hys.model.user.SysAuth;
public interface ISysAuthDalComp {
	/**
     * 添加系统权限
     * 
     * @param sysAuth
     *        系统权限
     * @return 权限ID
     * @author: chenjunxiu
     * @date: 2014年3月22日下午4:19:08
     */
    public String addSysAuth(SysAuth sysAuth);

    /**
     * 删除系统权限
     * 
     * @param authId
     *        系统权限Id
     * @return 删除影响的行数
     * @author: chenjunxiu
     * @date: 2014年3月22日下午4:19:08
     */
    public boolean deleteSysAuth(String authId);

    /**
     * 更新系统权限
     * 
     * @param sysAuth
     *        系统权限
     * @return 更新影响的行数
     * @author: chenjunxiu
     * @date: 2014年3月22日下午4:18:48
     */
    public boolean updateSysAuth(SysAuth sysAuth);

    /**
     * 查询权限表
     * 
     * @param authId
     * @return
     * @author: chenjunxiu
     * @date: 2014年3月22日下午4:17:36
     */
    public SysAuth querySysAuth(String authId);

    /**
     * 查询所有系统权限
     * 
     * @return
     * @author: chenjunxiu
     * @date: 2014年3月22日下午4:17:10
     */
    public List<SysAuth> querySysAuths();

    /**
     * 根据用户查询按树形结构排好序的权限列表
     * 
     * @param userId
     *        用户ID
     * @return 按树形结构排好序的权限列表
     * @author: hualong
     * @date: 2014年3月22日下午3:42:10
     */
    public List<SysAuth> queryAuthsByUserId(String userId);
    
    /**
     * 分页查询列表
     * 
     * @param page
     *        分页参数
     * @return
     */
    public PageData<SysAuth> pageQuerySysAuth(PageParam<SysAuth> page);
    

}
