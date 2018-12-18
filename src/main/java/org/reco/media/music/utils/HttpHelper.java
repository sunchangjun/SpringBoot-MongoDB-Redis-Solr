package org.reco.media.music.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
/**
 * http下载工具
 * @author zhangsl
 * @date 2018-04-26
 */
public class HttpHelper {
	
	public static String httpGet(String url) throws Exception {
		String content = null;
		CloseableHttpClient httpclient = HttpClientBuilder.create()
				.build();
		try {
			HttpGet get = new HttpGet(url);
			HttpResponse response = httpclient.execute(get);
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, "utf-8");
		} finally {
			httpclient.close();
		}
		return content;
	}
	
	/**
	 * 从url下载文件,保存到目标位置path
	 * @param url 下载地址
	 * @param savePath 保存文件的全路径, 如/roo/abc.png
	 * @throws Exception
	 */
	public static void httpDownload(String url, String savePath) throws Exception {
		FileOutputStream out = null;
		InputStream is = null;
		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			initConnection(conn);
			conn.connect();
			is = conn.getInputStream();
			byte[] buf = new byte[10240];
			out = new FileOutputStream(savePath);
			int len = 0;
			while((len=is.read(buf))!=-1){
                out.write(buf, 0, len);
            }
			is.close();
			out.close();
		} catch (Exception e) {
			if (is != null)
				is.close();
			if(out != null)
				out.close();
			File file = new File(savePath);
			if (file.exists()) {//not finish, delete
				file.delete();
			}
			throw e;
		}
	}

	protected static void downloadByNio(String address, String path) throws Exception {
		InputStream ins = new URL(address).openStream();
		Path target = Paths.get(path);
		Files.copy(ins, target, StandardCopyOption.REPLACE_EXISTING);
	}

	private static void initConnection(HttpURLConnection conn) {
		conn.setRequestProperty("User-agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
		conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setConnectTimeout(30 * 1000);
		conn.setReadTimeout(30 * 1000);
	}

	public static void main(String[] args) throws Exception {
		httpDownload(
				"http://118.112.10.143/vcloud1049.tc.qq.com/1049_M0110600000fOufR30z4Ql1001554928.f40.mp4?vkey=F3E3C47383B90C046BE5CA858C1140DC844B2CEAED34329A93ED4E10FED31EE4E3E758B09797182C731F888895F85D1CA964ED0F1FA1F4AF0B14DB1BFB3712455A76DDC2988BE96DC0B6FC53B8A2032D45FFDE74F8A38DF6&stdfrom=1",
				"d:/demozsl.m4a");
	}

}
