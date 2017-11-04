package com.hys.commons.conf;

/**
 * 观察者模式通知
 * 
 */
public interface FileUpdate
{
    // 通知你某个文件变化了
    public void updateFile(String fileName);

}
