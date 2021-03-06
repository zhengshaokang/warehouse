package com.hys.mgt.view.fileupload.vo;

import java.io.Serializable;

/**
 * 图片上传返回对象
 * 
 * @author: ZhengShaoKang
 * @date 2014年5月10日 上午10:48:20
 */
public class NewsPicReturnUpload implements Serializable
{
    private static final long serialVersionUID = 1L;

    private String tagId;
    private String returnPath;// 图片路径
    private String returnCode;// 1表示成功 0表示失败


    public String getReturnPath()
    {
        return returnPath;
    }

    public void setReturnPath(String returnPath)
    {
        this.returnPath = returnPath;
    }

	public String getReturnCode()
    {
        return returnCode;
    }

    public void setReturnCode(String returnCode)
    {
        this.returnCode = returnCode;
    }

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
    
}
