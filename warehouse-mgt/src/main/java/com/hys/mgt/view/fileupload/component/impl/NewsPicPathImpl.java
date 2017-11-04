package com.hys.mgt.view.fileupload.component.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.hys.commons.common.Constants;
import com.hys.commons.fileupload.util.FileSavingUtils;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.fileupload.component.INewsPicPath;


/**
 * 商品图片存储路径
 * 
 */
@Service
public class NewsPicPathImpl implements INewsPicPath
{

    /**
     * 返回路径：基础路径/图片类型/年/月/日/商品编号/货品编号
     */
    @Override
    public String getFilePathByNews(String baseDirPath,String fileType, String... args)
    {
        if (LogicUtil.isNull(baseDirPath) || LogicUtil.isNullOrEmpty(fileType) || LogicUtil.isNullOrEmpty(args))
        {
            return null;
        }

        if (args.length != 1 || args[0] == null)
        {
            return null;
        }

        String newsId = args[0];// 新闻编 号

        String ymd = DateUtil.formatDate(new Date());// 日期
        StringBuffer sb = new StringBuffer();
        sb.append(FileSavingUtils.formatDirPath(baseDirPath));
        sb.append(fileType);
        sb.append(Constants.SLASH);
        sb.append(ymd.replaceAll(Constants.MIDDLELINE, Constants.EMPTY_STR));
        sb.append(Constants.SLASH);
        sb.append(newsId);
        sb.append(Constants.SLASH);
        return sb.toString();
    }

    /**
     * 返回路径：基础路径/图片类型/年/月/日/
     */
	@Override
	public String getFilePath(String baseDirPath, String fileType) {
		if (LogicUtil.isNull(baseDirPath) || LogicUtil.isNullOrEmpty(fileType))
        {
            return null;
        }

        String ymd = DateUtil.formatDate(new Date());// 日期
        StringBuffer sb = new StringBuffer();
        sb.append(FileSavingUtils.formatDirPath(baseDirPath));
        sb.append(fileType);
        sb.append(Constants.SLASH);
        sb.append(ymd.replaceAll(Constants.MIDDLELINE, Constants.SLASH));
        sb.append(Constants.SLASH);
        return sb.toString();
	}

}
