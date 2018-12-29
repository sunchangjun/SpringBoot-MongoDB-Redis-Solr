/**
 * 
 */
package com.sun.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年12月14日下午5:30:57
 */
public class FileName {
	@Test
	public void  test(){
		InputStream io= null;
		OutputStream os =null;
		try {
			File file =new  File("\\\\192.168.3.100\\qqmusic\\songs\\b\\k\\e04792b79a234291ba7b50e40149e576.flac");
			 io= new  FileInputStream(file);
			 os = new  FileOutputStream(new  File("G:\\down\\"+file.getName()));
				
		    int len;
		    byte[] bs = new byte[1024];
	        // 开始读取
	        while ((len = io.read(bs)) != -1) {
	            os.write(bs, 0, len);
	        }
			  
			System.out.println(file.getName());
		} catch(Exception e){
			e.printStackTrace();
			
		}finally {
		    try {
                os.close();
                io.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}

	}

}
