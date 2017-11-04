package com.hys.model.user;

import java.io.Serializable;

/**
 * 系统权限
 * 
 */
public class SysAuth implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String id;// 编号
    private String authName;// 菜单名称
    private String authUrl;// 菜单链接
    private String authPic;// 权限图片路径
    private String parentId;// 父菜单号
    private Integer sort=0;// 菜单排序
    private Integer recordStatus = -1;// 记录状态：0未删除 1删除
    private Integer authbutton=-1;//按钮权限，0增加、1删除、2修改、3导出
    

	public Integer getAuthbutton() {
		return authbutton;
	}

	public void setAuthbutton(Integer authbutton) {
		this.authbutton = authbutton;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}

	public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getAuthName()
    {
        return authName;
    }

    public void setAuthName(String authName)
    {
        this.authName = authName;
    }

    public String getAuthUrl()
    {
        return authUrl;
    }

    public void setAuthUrl(String authUrl)
    {
        this.authUrl = authUrl;
    }

    public String getAuthPic()
    {
        return authPic;
    }

    public void setAuthPic(String authPic)
    {
        this.authPic = authPic;
    }

    public String getParentId()
    {
        return parentId;
    }

    public void setParentId(String parentId)
    {
        this.parentId = parentId;
    }

    public int getSort()
    {
        return sort;
    }

    public void setSort(int sort)
    {
        this.sort = sort;
    }

    public int getRecordStatus()
    {
        return recordStatus;
    }

    public void setRecordStatus(int recordStatus)
    {
        this.recordStatus = recordStatus;
    }

    @Override
    public String toString()
    {
        return "SysAuth [id=" + id + ", authName=" + authName + ", authUrl=" + authUrl + ", authPic=" + authPic
                + ", parentId=" + parentId + ", sort=" + sort + ", recordStatus=" + recordStatus + "]";
    }

}
