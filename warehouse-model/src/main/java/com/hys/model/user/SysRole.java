package com.hys.model.user;

import java.io.Serializable;
import java.util.List;

/**
 * 系统角色
 * 
 */
public class SysRole implements Serializable
{
    private static final long serialVersionUID = 1L;

    public String id;// 角色ID
    public String roleName;// 角色名称
    public Integer roleStatus =1;// 角色状态:1正常，0冻结
    // 扩展属性
    public List<SysAuth> auths;// 用户角色
   
    public List<SysAuth> getAuths() {
		return auths;
	}


	public void setAuths(List<SysAuth> auths) {
		this.auths = auths;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public int getRoleStatus() {
		return roleStatus;
	}


	public void setRoleStatus(int roleStatus) {
		this.roleStatus = roleStatus;
	}

	@Override
    public String toString()
    {
        return "SysRole [id=" + id + ", roleName=" + roleName + ", roleStatus=" + roleStatus + ", auths=" + auths + "]";
    }

}
