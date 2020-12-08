//ok

package com.yidong.zhihu.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class FileUtils {


    public static final String TYPE_JPG = "JPG";
    public static final String TYPE_PNG = "PNG";
    public static final String TYPE_BMP = "BMP";
    public static final String TYPE_JPEG = "JPEG";

    public static final String TYPE_JPG1 = "jpg";
    public static final String TYPE_PNG1 = "png";
    public static final String TYPE_BMP1 = "bmp";
    public static final String TYPE_JPEG1 = "jpeg";

    /**
     * 获得文件后缀
     * @param fileName 文件全名
     * @return 文件后缀
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 获得文件前缀
     * @param fileName 文件全名
     * @return 文件前缀
     */
    public static String getPrefix(String fileName){
        return fileName.substring(0,fileName.lastIndexOf("."));
    }

    /**
     * 生成新的文件名
     * @param fileOldName 文件原名
     * @return  文件新名
     */
    public static String getFileNewName(String fileOldName){
        return UUID.randomUUID().toString().replace("-","")+getSuffix(fileOldName);
    }


    /**
     * 生成新的文件名
     * @param fileOldName 文件原名
     * @return  文件新名
     */
    public static String addFileNewNameByTime(String fileOldName){
        String suffix = getSuffix(fileOldName);
        String prefix = getPrefix(fileOldName);
        return prefix+ UUID.randomUUID()+suffix;
    }
    /**
     * 文件上传
     * @param file 文件
     * @param filePath 文件存放路径
     * @param fileName 源文件名
     */
    public static void upload(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    //删除文件
    public static boolean deleteFile(String path){
        File file=new File(path);
        if(file.exists()){
            file.delete();
            return true;
        }
        else {
            return false;
        }
    }


    /**
     * 判断文件的后缀，是不是图片
     * @param fileSuffix 文件后缀
     * @return  true为是，false为不是
     */
    public static boolean judgeFileType(String fileSuffix){
        return fileSuffix.equals(FileUtils.TYPE_BMP) || fileSuffix.equals(FileUtils.TYPE_JPG)
                || fileSuffix.equals(FileUtils.TYPE_PNG)||fileSuffix.equals(FileUtils.TYPE_JPEG)
                ||fileSuffix.equals(FileUtils.TYPE_BMP1) || fileSuffix.equals(FileUtils.TYPE_JPG1)
                || fileSuffix.equals(FileUtils.TYPE_PNG1)||fileSuffix.equals(FileUtils.TYPE_JPEG1);
    }

}
