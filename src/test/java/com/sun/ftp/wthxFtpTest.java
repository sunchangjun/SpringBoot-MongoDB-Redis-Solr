/**
 * 
 */
package com.sun.ftp;

import org.junit.Test;

import com.sun.common.ftp.FtpUtils;

/**
 * @author sunchangjunn
 * 2018年10月30日下午5:49:11
 */
public class wthxFtpTest {
	
	
//	@Test
//	public void test() {
//		FtpUtils ftpcline = new FtpUtils("47.98.153.144", 21, "suncj", "scj19890606");
//		ftpcline.deleteFile("/home/suncj/sun_ftp", "ftptest.txt");
//	}
	
	
//	@Test
//	public void test1() {
//		FtpUtils ftpcline = new FtpUtils("47.98.153.144", 21, "suncj", "scj19890606");
//		ftpcline.downloadFile("/home/suncj/sun_ftp", "11.txt", "G:/");
//	}

	@Test
	public void test2upload() throws Exception {
		FtpUtils ftpcline = new FtpUtils("47.98.153.144", 21, "suncj", "scj19890606");
		ftpcline.uploadFile("/home/suncj/sun_ftp","11.txt","F:/11.txt");
	}

}
