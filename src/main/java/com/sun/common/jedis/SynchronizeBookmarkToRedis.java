package com.sun.common.jedis;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * redis上传下载最新书签
 */
public class SynchronizeBookmarkToRedis {
	
	private static final String localPath="D:\\File\\";

    public static void main(String[] args) {
        run("bookmarks_2018_11_16");
//        down();
    }

    /**
     * 上传书签到redis
     */
    public static void run(String  fileNam) {
        System.out.println("开始上传");
     long begin =   System.currentTimeMillis();
        String filePath=localPath+fileNam+".html";
        File file = new File(filePath);

        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //获取String类型的时间
        String createdate = sdf.format(date);
        String fileName =   file.getName();
        JedisUtils.set("chrome_book", file);
        long end =   System.currentTimeMillis();
        System.out.println(end-begin+"毫秒");
    }

    /**
     * 从redis下载书签
     */
    public static void down() {
        String key ="chrome_book";
        //我要获取当前的日期
        Date date = new Date();
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取String类型的时间
        String createdate = sdf.format(date);
        try{
//        String filePath=localPath+"chorm_book_"+createdate+".html";
        String filePathAndName = localPath+key+"_"+createdate+".html";
        //创建不同的文件夹目录
             File file =new File(localPath);
       //判断文件夹是否存在
            if  (!file .exists()  && !file .isDirectory())
            { System.out.println("//不存在");
                file .mkdir();
            } else {
                System.out.println("//目录存在");
            }
            //读文件流
        byte[] bytes = JedisUtils.getBytes(key);
        InputStream sbs = new ByteArrayInputStream(bytes);
        BufferedInputStream bis = new BufferedInputStream(sbs);

            //写入到文件（注意文件保存路径的后面一定要加上文件的名称）
            FileOutputStream     fileOut = new FileOutputStream(filePathAndName);
            BufferedOutputStream bos = new BufferedOutputStream(fileOut);

            byte[] buf = new byte[4096];
            int length = bis.read(buf);
            //保存文件
            while(length != -1)
            {
                bos.write(buf, 0, length);
                length = bis.read(buf);
            }
            bos.close();
            bis.close();
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("抛出异常！！");
        }




        System.out.println("完成");

    }


}
