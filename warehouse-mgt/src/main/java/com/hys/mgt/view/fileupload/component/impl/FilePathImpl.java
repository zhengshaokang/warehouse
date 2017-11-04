package com.hys.mgt.view.fileupload.component.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.hys.commons.common.Constants;
import com.hys.commons.fileupload.util.FileSavingUtils;
import com.hys.commons.util.DateUtil;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.fileupload.component.IFilePath;


/**
 * 商品图片存储路径
 * 
 */
@Service
public class FilePathImpl implements IFilePath
{

    /**
     * 返回路径：基础路径/图片类型/年/月/日/商品编号/货品编号
     */
    @Override
    public String getFilePathByGoods(String baseDirPath,String fileType, String... args)
    {
        if (LogicUtil.isNull(baseDirPath) || LogicUtil.isNullOrEmpty(fileType) || LogicUtil.isNullOrEmpty(args))
        {
            return null;
        }

        if (args.length != 2 || args[0] == null || args[1] == null)
        {
            return null;
        }

        String goodsId = args[0];// 商品编号
        String skuId = args[1];// 货品编号

        String ymd = DateUtil.formatDate(new Date());// 日期
        StringBuffer sb = new StringBuffer();
        sb.append(FileSavingUtils.formatDirPath(baseDirPath));
        sb.append(fileType);
        sb.append(Constants.SLASH);
        sb.append(ymd.replaceAll(Constants.MIDDLELINE, Constants.EMPTY_STR));
        sb.append(Constants.SLASH);
        sb.append(goodsId);
        sb.append(Constants.SLASH);
        if(LogicUtil.isNotNullAndEmpty(skuId)){ //如果是商品图片就返回商品图片的根目录，如果是货品图片就在加一个文件夹
	        sb.append(skuId);
	        sb.append(Constants.SLASH);
        }
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
        sb.append(ymd.replaceAll(Constants.MIDDLELINE, Constants.EMPTY_STR));
        sb.append(Constants.SLASH);
        return sb.toString();
	}

}
