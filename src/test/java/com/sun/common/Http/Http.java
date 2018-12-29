/**
 * 
 */
package com.sun.common.Http;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;


/**
 * @author sunchangjunn
 * 2018年12月26日下午5:59:26
 */
public class Http {
  	private static final String res_domain = "http://service.recomusic.net";
    /**
     * 以http的方式取文件到本地
     * @param localFile
     * @return
     */
    private static String getHttpFile(String fileUri,Integer fileType,String fileName) throws Exception{
  
    	fileUri = fileUri.replace("\\", "/");
    	String newFile = "d:\\upimage\\"+fileName;
    	File dir = new File("d:\\upimage");
    	if(!dir.exists()){
    		dir.mkdir();
    	}
    	String address = null;
    	if(fileUri.startsWith("http")){
    		 address = fileUri;
    	}else{
    		address = fileUri.replace("//192.168.3.100", res_domain);
    	}
    	try {
    		URL url = new URL(address);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			InputStream is = conn.getInputStream();
			BufferedInputStream bis=new BufferedInputStream(is);
			FileOutputStream fos = new FileOutputStream(new File(newFile));
			byte[] buffer = new byte[1024 * 1024 * 10];
			int len=0;
			while( (len=bis.read(buffer)) != -1 ) {
				fos.write(buffer, 0, len);
				fos.flush();
			}
			is.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return newFile;
    }

}
