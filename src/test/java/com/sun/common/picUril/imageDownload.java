/**
 * 
 */
package com.sun.common.picUril;

/**
 * @author sunchangjunn
 * 2018年8月22日上午11:03:05
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 图片下载到本地
 * 
 * @author sunchangjunn 2018年8月22日下午1:39:23
 */
public class imageDownload {
	public static void main(String[] args) throws Exception {

		String urlString = "http://y.gtimg.cn/music/photo/radio/track_radio_%d_%d_%d.jpg";
//		for (int k = 23; k < 500; k++) {
//			for (int i = 0; i <= 13; i++) {
//				for (int j = 0; j <= 10; j++) {
//					String url = String.format(urlString,k, i, j);
//					StringBuffer sb = new StringBuffer("track_radio_").append(k+"_").append(i + "_").append(j + ".jpg");
//					System.out.println(url);
//					System.out.println(sb.toString());
//
//					download(url, sb.toString(), "d:/temp/");
//				}
//			}
//		}

		download("http://y.gtimg.cn/music/photo/radio/track_radio_199_10_8.jpg","1111.jpg", "d:/pic/");
	}

	public static void download(String urlString, String filename, String savePath) throws Exception {
		try {			
			  HttpURLConnection conn = (HttpURLConnection) new URL(urlString)
	                    .openConnection();
	            conn.setReadTimeout(5000);
	            conn.setConnectTimeout(5000);
	            conn.setRequestMethod("GET");

	            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
	               return;
	            }
	            
			   InputStream is = conn.getInputStream();
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			System.out.println("成功:"+urlString);
			// 完毕，关闭所有链接
			os.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
