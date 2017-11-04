package com.hys.mgt.view.fileupload.component;

/**
 * 依据存储的文件类型创建各类文件存储的相对路径
 */
public interface INewsPicPath
{

    /**
     * 创建文件存储的相对路径
     * 
     * @param baseDirPath
     *        某种类型文件的基础目录路径
     * @param fileType
     * 		      文件 类型     
     * @param args
     *        用户生成文件相对路径的参数
     * @return 文件存储的相对路径
     */
    public String getFilePathByNews(String baseDirPath,String fileType, String... args);
    /**
     * 
     * @param baseDirPath
     * 		某种类型文件的基础目录路径
     * @param fileType  
     * 		文件 类型     
     * @return
     */
    public String getFilePath(String baseDirPath,String fileType);
    

}
