package com.hys.mgt.view.fileupload.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hys.commons.common.Constants;
import com.hys.commons.fileupload.FileSender;
import com.hys.commons.fileupload.util.FileImgProcess;
import com.hys.commons.fileupload.util.FileSavingUtils;
import com.hys.commons.http.RequestUtil;
import com.hys.commons.json.JsonConverter;
import com.hys.commons.util.LogicUtil;
import com.hys.mgt.view.common.utils.ServletUtils;
import com.hys.mgt.view.common.utils.WebConfUtils;
import com.hys.mgt.view.fileupload.common.FileSendWorker;
import com.hys.mgt.view.fileupload.component.IFilePath;
import com.hys.mgt.view.fileupload.vo.ReturnUpload;


/**
 * @author: zhengshaokang
 */
@Controller
@RequestMapping("/fileupload/")
public class FileUploadController
{

    @Autowired
    private FileSender fileSender;

    @Autowired
    private IFilePath filePath;

   
    @ResponseBody
    @RequestMapping("uploadpic")
    public String uploadPic(MultipartHttpServletRequest request) throws Exception {
        MultipartFile file = null;
        MultiValueMap<String, MultipartFile> multiFiles = request.getMultiFileMap();
        
        String picType = RequestUtil.getStringParameter(request, "picType", "0");// 图片类型，0为sku图片2详情图片3其他4广告图
        String goodsId = RequestUtil.getStringParameter(request, "sku", "XXXXXX");// sku
        //String skuId = RequestUtil.getStringParameter(request, "skuId", "");// 商品编号
        
        String locasBaseDirPath = ServletUtils.getRealPath() + WebConfUtils.getString("local_base_dir", "");
        locasBaseDirPath = FileSavingUtils.formatDirPath(locasBaseDirPath);
        try {

            for (String key : multiFiles.keySet()) {
                file = multiFiles.getFirst(key);
            }

            // 文件上传
            FileItemFactory factory = new DiskFileItemFactory();
            new ServletFileUpload(factory);

            String newDirPath = filePath.getFilePathByGoods(locasBaseDirPath,"goodspic",goodsId, "");
            if("2".equals(picType)){
            	newDirPath = filePath.getFilePathByGoods(locasBaseDirPath,"goodspic",goodsId, "details");
            }
            if("3".equals(picType)){
            	newDirPath = filePath.getFilePath(locasBaseDirPath,"ather");
            }
            if("4".equals(picType)){
            	newDirPath = filePath.getFilePath(locasBaseDirPath,"advert");
            } 
            File uploadDir = new File(newDirPath);
            if (!uploadDir.isDirectory()) {// 检查目录 
                uploadDir.mkdirs();// 生成目录
            }

            List<String> localFileRelativePaths = new ArrayList<String>();
           
            if (file.isEmpty()) {
                return null;
            }

            // 获取文件名
            String fileName = file.getOriginalFilename();
            if (!isPic(fileName) && isSize(file.getSize())) {
                return null;
            }
            InputStream is = file.getInputStream();
            
            InputStream is1 = file.getInputStream();
            
            
            // 获取文件的新名称,并生成文件新路径=文件所在目录路径+文件名称
            String newFilePath = newDirPath + FileSavingUtils.getNewFileName(fileName);

            // 保存源图及其缩略图到所在web容器的指定目录里
           //int thumbSizes[][] = getThumbSizes();// 获取图片压缩数组
            //boolean isSuccess = FileImgProcess.thumbingAndSavingAllImgs(newFilePath, thumbSizes, is);
            
            
            BufferedImage image = ImageIO.read(is1);  
            
            int srcWidth = image .getWidth();      // 源图宽度
            int srcHeight = image .getHeight();    // 源图高度
            
            if(srcWidth > 600) {
            	double reg  = (double)srcWidth/600;
            	Double height = srcHeight/reg;
            	srcHeight = height.intValue();
            	srcWidth = 600;
            }
            
            
            String uploadFilePath = FileImgProcess.thumbingAndSavingImgs(newFilePath,srcWidth, srcHeight,is);
            
            String localFileRelativePath = getRelativePath(locasBaseDirPath,uploadFilePath);
            
            ReturnUpload ru = new ReturnUpload();
            
            ru.setTagId(RequestUtil.getStringParameter(request, "tagId", ""));
            ru.setReturnCode("0");
            ru.setReturnPath("");
            ru.setPicType(picType);
            
            if (null !=uploadFilePath)  {
            	 ru.setReturnCode("1");
                 ru.setReturnPath(localFileRelativePath);
            }

            // 根据某一长图片的路径获取该图片所在目录下的所有图平的相对路径(相对于基础路径)
            //localFileRelativePaths.addAll(FileSavingUtils.getFileRelativePaths(locasBaseDirPath, newFilePath));
          //  String localFileRelativePath = getRelativePath(locasBaseDirPath, newFilePath);

            // 把web服务器上的源图和缩略图发送到远程服务器
            // this.asyncTransFile(locasBaseDirPath, localFileRelativePaths);
           // this.saveLocahostFile(locasBaseDirPath, localFileRelativePaths);

           
          
            // 把上传后的图片相对路径返回给网页
            return JsonConverter.format(ru);
        }  catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }


    private String getRelativePath(String basePath, String filePath)
    {
        if (LogicUtil.isNotNullAndEmpty(basePath) && LogicUtil.isNotNullAndEmpty(filePath))
        {
            return filePath.replace(basePath, "");
        }
        return null;
    }

    private boolean isPic(String fileName)
    {
        if (FileImgProcess.isImg(fileName))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @SuppressWarnings("all")
    private boolean inSize(FileItem item)
    {
        long size = item.getSize();
        long maxSize = WebConfUtils.getLong("pic_max_size", 0);
        if (maxSize >= size)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean isSize(long size)
    {
        long maxSize = WebConfUtils.getLong("pic_max_size", 0);
        if (maxSize >= size)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    // 字符串转2维数组 arrayStr：字符串；spec1:一维分隔符；spe2：2维分隔符
    private int[][] getThumbSizes()
    {
        String arrayStr = WebConfUtils.getString("goods_thumb_size", "");
        // 把传进来的字符串用spe1分隔开来
        String[] farr = arrayStr.split(Constants.SEMICOLON);
        if (farr == null)
        {
            return null;
        }

        int[][] tsarr = new int[farr.length][];// 根据String一维数组的大小来分配int的一维数组
        for (int i = 0; i < farr.length; i++)
        {
            String[] sarr = farr[i].split(Constants.X);// 再对一维数组中的每个字符串常量使用spe2(,)再分隔
            if (sarr == null)
            {
                continue;
            }
            tsarr[i] = new int[sarr.length];// 分配int的二维数组
            for (int j = 0; j < sarr.length; j++)
            {
                tsarr[i][j] = Integer.parseInt(sarr[j]);// 将字符串转化乘int型数据
            }
        }

        return tsarr;
    }


    private void syncTransFile(String localBaseDirPath, List<String> localFileRelativePaths)
    {
        String remoteBaseDirPath = WebConfUtils.getString("remote_base_dir", "");
        Runnable task = new FileSendWorker(fileSender, localBaseDirPath, localFileRelativePaths, remoteBaseDirPath);
        task.run();
    }
    
    private void saveLocahostFile(String localBaseDirPath, List<String> localFileRelativePaths) throws Exception {
    	String remoteBaseDirPath = WebConfUtils.getString("remote_base_dir", "");
    	if(LogicUtil.isNotNullAndEmpty(localFileRelativePaths)) {
    		for (String filePath : localFileRelativePaths) {
    			
    			String newPath = remoteBaseDirPath + filePath;
    			File newFile = new File(newPath);
    			InputStream is = new FileInputStream(localBaseDirPath + filePath);
    			FileSavingUtils.writeFileToDisc(newFile, is);
    		}
    	}
    }

    public static void main(String[] args)
    {
//        List<String> relativePaths = new ArrayList<String>();
//        relativePaths.add("aaa");
//        relativePaths.add("bbbb");
//        System.out.println(ArrayUtils.toString(relativePaths.toArray()).replace("{", "").replace("}", ""));
    	
    	System.out.println((double)(1080)/600);
    	double reg  = Math.round(((1080/600)*100)/100);
    	System.out.println(reg);
    	System.out.println(1920/reg);
    }

    @SuppressWarnings("all")
    private String toJson(List<String> relativePaths)
    {
        return JsonConverter.format(relativePaths);
    }
}
