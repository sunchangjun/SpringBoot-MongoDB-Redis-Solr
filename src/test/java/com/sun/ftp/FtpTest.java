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

import com.sun.common.ftp.MyFtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Test;

public class FtpTest {
    @Test
   public void test(){
        MyFtpUtil my =new MyFtpUtil();
        boolean bool =my.uploadFie("/user/sun","G:\\down\\err.txt","");
        System.out.println(bool);
   }

}
