package com.hys.mgt.view.user.vo;

import java.io.Serializable;
import java.util.List;

import com.hys.mgt.view.common.constants.WebConstants;
import com.hys.model.user.SysAuth;


/**
 * 系统角色
 * 
 * @date 2014年7月22日 下午3:14:49
 */
public class SysRoleVo implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	public String id;// 角色ID
    public String roleName;// 角色名称
    public Integer roleStatus = -1;// 角色状态:0正常，1冻结

    private String auth;

    // 扩展属性
    public List<SysAuth> auths;// 用户角色

    private int pageSize = WebConstants.PAGESIZE;

    /**
     * @return pageSize
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * @param pageSize
     *        the pageSize to set
     */
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    /**
     * @return auth
     */
    public String getAuth()
    {
        return auth;
    }

    /**
     * @param auth
     *        the auth to set
     */
    public void setAuth(String auth)
    {
        this.auth = auth;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getRoleName()
    {
        return roleName;
    }

    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    public int getRoleStatus()
    {
        return roleStatus;
    }

    public void setRoleStatus(int roleStatus)
    {
        this.roleStatus = roleStatus;
    }

    public List<SysAuth> getAuths()
    {
        return auths;
    }

    public void setAuths(List<SysAuth> auths)
    {
        this.auths = auths;
    }

}
