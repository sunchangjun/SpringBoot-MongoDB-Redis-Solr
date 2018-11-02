/**
 * 
 */
package com.sun.ftp;

/**
 * @author sunchangjunn
 * 2018年10月30日下午5:13:58
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

public class FtpTest {
    
    String ftpHost= "47.98.153.144"; //ftp服务器地址
    int ftpPort = 21;//ftp服务员器端口号
    String ftpUserName = "suncj";//anonymous匿名用户登录，不需要密码。administrator指定用户登录
    String ftpPassword = "scj19890606";//指定用户密码
//    String ftpPath = "ftpdir/"; //ftp文件存放物理路径
    String ftpPath = "/home/suncj/sun_ftp/"; //ftp文件存放物理路径
    String filePath=""; //文件路径
    String fileName="";//文件名称
    

//    @Test
//    public void test() throws Exception {
////        filePath="G:/";
////        fileName="11.txt";
////        FtpUtil.downloadFile(ftpHost, ftpUserName, ftpPassword, ftpPort,
////                ftpPath, filePath, fileName);
//
//        filePath="F:/";
//        fileName="11.txt";
//        FileInputStream input=new FileInputStream(new File(filePath+File.separatorChar+fileName));
//        System.out.println(filePath+File.separatorChar+fileName);
//        FtpUtil.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName, input);
//    }



    public static FTPClient getFtpClient(){
        FTPClient ftpClient = new FTPClient();
        try{

            ftpClient.connect("47.98.153.144",21);
            ftpClient.login("suncj", "scj19890606");
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            ftpClient.setControlEncoding("UTF-8");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ftpClient;
    }

    @Test
    public  boolean FtpUpload(){
        String path="/home/suncj/sun_ftp/";
        File filename =new File("G:/11.txt");
        Boolean success = false;
        String LOCAL_CHARSET = "GBK";
        String SERVER_CHARSET = "ISO-8859-1";
        try {
            FileInputStream in = new FileInputStream(filename);
            //get properties & get connect ftpClient

            FTPClient ftpClient = getFtpClient();

            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if(FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                LOCAL_CHARSET = "UTF-8";
            }
            ftpClient.setControlEncoding(LOCAL_CHARSET);

//            if(fileType == FTP.BINARY_FILE_TYPE){
//                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            }
            //check if connection failed
            int reply = 0;
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                return success;
            }
            //mkdirs(in case of multiple level directory)
            if(path != null && !path.isEmpty()){
                //MKdirs中有关于path的转码
                boolean tmpMkdir = Mkdirs(ftpClient, path);
                System.out.println(ftpClient.printWorkingDirectory());
                boolean tmpCd = ftpClient.changeWorkingDirectory(path);
                System.out.println(ftpClient.printWorkingDirectory());
            }
            //上传文件时，文件名称需要做编码转换
            String name = new String(filename.getName().getBytes(LOCAL_CHARSET),SERVER_CHARSET);
            boolean tmpStore = ftpClient.storeFile(name, in);
            boolean tmpDoCommand = ftpClient.sendSiteCommand("chmod 755 " + name);
            //here is a mark that maybe i should close ftp connections after all store..
            //like create a new function to close :-(
            in.close();
            ftpClient.logout();
            success = true;
        }catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return success;
    }

    public static Boolean Mkdirs(FTPClient ftpClient,String path){
        Boolean success = false;
        String[] subDirs = path.split("/");

        String LOCAL_CHARSET = "GBK";
        String SERVER_CHARSET = "ISO-8859-1";

        //check if is absolute path
        if(path.substring(0, 0).equalsIgnoreCase("/")){
            subDirs[0] = "/" + subDirs[0];
        }
        boolean tmpMkdirs = false;
        try {
            // 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
            if(FTPReply.isPositiveCompletion(ftpClient.sendCommand("OPTS UTF8", "ON"))) {
                LOCAL_CHARSET = "UTF-8";
            }
            ftpClient.setControlEncoding(LOCAL_CHARSET);

            String orginPath = ftpClient.printWorkingDirectory();
            for(String subDir : subDirs){
                //encoding
                String strSubDir = new String(subDir.getBytes(LOCAL_CHARSET),SERVER_CHARSET);
                tmpMkdirs = ftpClient.makeDirectory(strSubDir);
                boolean tmpDoCommand = ftpClient.sendSiteCommand("chmod 755 " + strSubDir);
                ftpClient.changeWorkingDirectory(strSubDir);
                success = success || tmpMkdirs;
            }
            //ftpClient.changeWorkingDirectory(orginPath);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return success;
    }

}
