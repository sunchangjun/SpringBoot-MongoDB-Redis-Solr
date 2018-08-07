package com.sun.common;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class testHttpClient {
	public static void main(String[] args) throws ClientProtocolException, IOException {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建http GET请求
		String name=java.net.URLEncoder.encode("{\"albumlib\":{\"method\":\"get_album_by_tags\",\"param\":{\"area\":1,\"company\":-1,\"genre\":-1,\"type\":-1,\"year\":-1,\"sort\":2,\"get_tags\":1,\"sin\":0,\"num\":20,\"click_albumid\":0},\"module\":\"music.web_album_library\"}}","UTF-8");
		HttpGet httpGet = new HttpGet("https://u.y.qq.com/cgi-bin/musicu.fcg?callback=getUCGI8812619174790346&g_tk=5381&jsonpCallback=getUCGI8812619174790346&loginUin=0&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0&data="+name);


		CloseableHttpResponse response = httpclient.execute(httpGet);
		System.out.println(response);
	}

}
