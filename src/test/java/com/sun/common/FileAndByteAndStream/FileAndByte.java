package com.sun.common.FileAndByteAndStream;

import com.alibaba.fastjson.JSONObject;
import com.sun.common.jedis.JedisUtils;
import org.junit.Test;

import java.io.*;

public class FileAndByte {
//    @Test
    public void byte2File(){

        try {
            //获取byte
            byte[] buf = JedisUtils.getBytes("chromBookmark");
            //新建文件
            File file = new File("D:\\File\\1.html");
            //文件转化为输出流
            FileOutputStream fos = new FileOutputStream(file);
            //包装让速度更快
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            //写出
            bos.write(buf);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void file2Byte(){
        try {
            //读取文件
            File file = new File("D:\\File\\1.html");
            //获取输入流
            InputStream   input = new FileInputStream(file);
            //新建byte[]
            byte[] byt = new byte[input.available()];
            //输入流写入byte
            input.read(byt);
            System.out.print(JSONObject.toJSONString(byt));
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
