package com.hys.commons.fileupload.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hys.commons.util.LogicUtil;

/**
 */
public class BatchThumbImgUtils
{

    static List<File> fileList = new ArrayList<File>();

    private static int[][] getSizes(String sizeStr)
    {
        try
        {
            String[] sizePair = sizeStr.split(",|，");
            int[][] sizes = new int[sizePair.length][2];
            for (int i = 0; i < sizes.length; i++)
            {
                String p[] = sizePair[i].split("x|X");
                sizes[i][0] = Integer.valueOf(p[0]);
                sizes[i][1] = Integer.valueOf(p[1]);
            }

            return sizes;
        }
        catch (Exception e)
        {
            System.out.println("format size is error");
        }

        return new int[0][0];
    }

    public static void getImages(File baseDir)
    {
        File[] files = baseDir.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            File subFile = files[i];
            if (subFile.isDirectory())
            {
                getImages(subFile);
            }
            else
            {
                if (isImg(subFile.getName()))
                {
                    if (!subFile.getName().matches(".*?_[0-9]+x[0-9]+\\..*?"))
                    {
                        fileList.add(subFile);
                    }
                }
            }
        }
    }

    private static boolean isImg(String fileName)
    {
        if (!LogicUtil.isNullOrEmpty(fileName))
        {
            fileName = fileName.toLowerCase();
            if (fileName.endsWith("jpg") || fileName.endsWith("jpeg") || fileName.endsWith("png"))
            {
                return true;
            }
        }
        return false;
    }

    public static void thumbing(String baseDir, String sizeStr)
    {
        if (LogicUtil.isNullOrEmpty(baseDir) || LogicUtil.isNullOrEmpty(sizeStr))
        {
            return;
        }

        fileList.clear();
        getImages(new File(formatBaseDir(baseDir)));
        // deleteThumbedImg(fileList);

        int sizes[][] = getSizes(sizeStr);
        for (File file : fileList)
        {
            try
            {
                String originalImgPath = file.getCanonicalPath();
                for (int i = 0; i < sizes.length; i++)
                {
                    String targetImgPath = getTargetImgPath(file, sizes[i]);
                    ImageThumbUtils.thumbImage(originalImgPath, sizes[i][0], sizes[i][1], targetImgPath);
                }
            }
            catch (Exception e)
            {
                System.out.println("thumbing image error,path is=" + file);
            }
        }
    }

    // private static void deleteThumbedImg(List<File> fileList)
    // {
    // for (Iterator<File> iter = fileList.iterator(); iter.hasNext();)
    // {
    // File file = iter.next();
    // String name = file.getName();
    // if (name.matches(".*?_[0-9]+x[0-9]+\\..*?"))
    // {
    // file.delete();
    // iter.remove();
    // System.out.println("delete file is=>" + file);
    // }
    // }
    // }

    private static String getTargetImgPath(File file, int[] size)
    {
        String name = file.getName();
        String sufix = name.substring(name.lastIndexOf(".") + 1);
        name = name + "_" + size[0] + "x" + size[1] + "." + sufix;

        try
        {
            return formatBaseDir(file.getParentFile().getCanonicalPath()) + name;
        }
        catch (Exception e)
        {
            System.out.println("get canonical path is error：" + file);
        }

        return "";
    }

    private static String formatBaseDir(String dir)
    {
        if (null != dir && dir.trim().length() > 0)
        {
            dir = dir.replaceAll("\\\\", "/");
            if (!dir.trim().endsWith("/"))
            {
                return dir.trim() + "/";
            }
            else
            {
                return dir.trim();
            }
        }
        return "";
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入图片根目录：");
        String baseDir = sc.nextLine();
        System.out.println("请输入图片压缩尺寸格式：宽x高；如：100x100,200x300；输入exit退出");
        String sizeStr = sc.nextLine();

        while (!"exit".equals(sizeStr.trim()))
        {
            System.out.println("\n压缩开始>>>");
            thumbing(baseDir, sizeStr);
            System.out.println("<<<压缩完成\n");

            System.out.println("请输入图片根目录：");
            baseDir = sc.nextLine();
            System.out.println("请输入图片压缩尺寸格式：宽x高；如：100x100,200x300；输入exit退出");
            sizeStr = sc.nextLine();
        }

        sc.close();
    }
}
