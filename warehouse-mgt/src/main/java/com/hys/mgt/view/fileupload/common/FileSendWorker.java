package com.hys.mgt.view.fileupload.common;

import java.util.List;

import com.hys.commons.fileupload.FileSender;

/**
 * 劳动者,发送给图片服务器
 * 
 */
public class FileSendWorker implements Runnable
{
    private FileSender fileSender;// 文件发送器

    private String localBaseDirPath;// 本地文件基础目录

    private List<String> localFileRelativePaths;// 本地多个源文件业务路径

    private String remoteBaseDirPath;// 远程文件基础目录

    /**
     * 文件异步发送器 注：文件路径=文件基础路径+业务路径
     * 
     * @param fileSender
     *        文件发送器
     * @param localBaseDirPath
     *        本地文件基础目录
     * @param localFileRelativePaths
     *        本地多个源文件业务路径
     * @param remoteBaseDirPath
     *        远程文件基础目录
     */
    public FileSendWorker(FileSender fileSender, String localBaseDirPath, List<String> localFileRelativePaths,
            String remoteBaseDirPath)
    {
        super();
        this.fileSender = fileSender;
        this.localBaseDirPath = localBaseDirPath;
        this.localFileRelativePaths = localFileRelativePaths;
        this.remoteBaseDirPath = remoteBaseDirPath;
    }

    public FileSender getFileSender()
    {
        return fileSender;
    }

    public void setFileSender(FileSender fileSender)
    {
        this.fileSender = fileSender;
    }

    public String getLocalBaseDirPath()
    {
        return localBaseDirPath;
    }

    public void setLocalBaseDirPath(String localBaseDirPath)
    {
        this.localBaseDirPath = localBaseDirPath;
    }

    public List<String> getLocalFileRelativePaths()
    {
        return localFileRelativePaths;
    }

    public void setLocalFileRelativePaths(List<String> localFileRelativePaths)
    {
        this.localFileRelativePaths = localFileRelativePaths;
    }

    public String getRemoteBaseDirPath()
    {
        return remoteBaseDirPath;
    }

    public void setRemoteBaseDirPath(String remoteBaseDirPath)
    {
        this.remoteBaseDirPath = remoteBaseDirPath;
    }

    @Override
    public void run()
    {
        fileSender.openConnection();
        fileSender.sendFiles(localBaseDirPath, localFileRelativePaths, remoteBaseDirPath);
        fileSender.closeConnection();
    }
}
