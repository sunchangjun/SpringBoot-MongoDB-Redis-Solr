package com.sun.common.IoStream;

import org.junit.Test;

import java.io.*;

public class IoStreamDemo {


    /**
     * io流读取文件
     */
//    @Test
    public void readFile(){
        FileInputStream fis = null;
        try {
            String filePath = "D:\\File\\io流读取试验.txt" ;
            // 1,根据path路径实例化一个输入流的对象
             fis = new FileInputStream("filePath");
            //2. 返回这个输入流中可以被读的剩下的bytes字节的估计值；
            int size =  fis.available() ;
            //3. 根据输入流中的字节数创建byte数组；
            byte[] array = new byte[size];
            //4.把数据读取到数组中；
            fis.read( array ) ;


            //5.根据获取到的Byte数组新建一个字符串，然后输出；
            String   result = new String(array);
        System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if ( fis != null) {
                try {//关闭
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        }


    /**
     * io流写入文件
     */
    @Test
    public void writeFile(){
        //文件地址
        String filePath = "D:\\File\\io流读取试验.txt" ;
        //要写入的内容
        String content = "今天是2017/1/9,天气很好" ;
        FileOutputStream fos = null ;
        try {
            //1、根据文件路径创建输出流
            fos  = new FileOutputStream( filePath );
            //2、把string转换为byte数组；
            byte[] array = content.getBytes() ;
            //3、把byte数组输出；
            fos.write( array );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if ( fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }






}
