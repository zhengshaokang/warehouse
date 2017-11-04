package com.hys.mgt.view.user.vo;

import java.io.Serializable;

import com.hys.mgt.view.common.constants.WebConstants;


/**
 * 系统权限
 * 
 * @date 2014年7月22日 下午4:09:11
 */
public class SysAuthVo implements Serializable
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
    private int pageNum = 1;
    private int numPerPage = WebConstants.PAGESIZE;

 
    public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public String getAuthPic() {
		return authPic;
	}

	public void setAuthPic(String authPic) {
		this.authPic = authPic;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Integer recordStatus) {
		this.recordStatus = recordStatus;
	}



	public Integer getAuthbutton() {
		return authbutton;
	}

	public void setAuthbutton(Integer authbutton) {
		this.authbutton = authbutton;
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

}
