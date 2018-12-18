/**
 * 
 */
package com.sun.common.time;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年12月14日下午2:13:32
 */
public class Timecon {
	@Test
	public void test() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String remoteFile = "/data/images/";
		String currTime=sdf.format(new Date());
		System.out.println(remoteFile+currTime);
		File file =new File("G:\\333.jpg");
		System.out.println(file.getName());
		
		
		
	}

}
