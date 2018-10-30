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
    

    @Test
    public void test() throws Exception {
        filePath="G:/";
        fileName="11.txt";
        FtpUtil.downloadFile(ftpHost, ftpUserName, ftpPassword, ftpPort,
                ftpPath, filePath, fileName);
        
//        filePath="F:/";
////        filePath="F:/upload";
//        fileName="11.txt";
//        FileInputStream input=new FileInputStream(new File(filePath+File.separatorChar+fileName));
//        FtpUtil.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName, input);
    }

}
