package org.reco.media.music.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

public class HttpClientHelper {

	/**
	 * 登陆类型的请求
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static HttpResult loginPost(String url, List<NameValuePair> params) throws Exception{
		CookieStore cookieStore = new BasicCookieStore();
		HttpClientBuilder builder = HttpClientBuilder.create();
		builder.setDefaultCookieStore(cookieStore);
		CloseableHttpClient httpclient = builder.build();
		try {
			HttpPost httpost = new HttpPost(url);
			httpost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity,"utf-8");
			return new HttpResult(content, cookieStore);
		} finally {
			httpclient.close();
		}
	}
	
	public static HttpResult post(String url, List<NameValuePair> params, CookieStore cookieStore) throws Exception{
		HttpClientBuilder builder = HttpClientBuilder.create();
		if(cookieStore!=null){
			builder.setDefaultCookieStore(cookieStore);
		}
		CloseableHttpClient httpclient = builder.build();
		try {
			HttpPost httpost = new HttpPost(url);
			httpost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity,"utf-8");
			return new HttpResult(content);
		} finally {
			httpclient.close();
		}
	}
	
	public static HttpResult post(String url, HttpEntity formEntity, CookieStore cookieStore) throws Exception{
		HttpClientBuilder builder = HttpClientBuilder.create();
		if(cookieStore!=null){
			builder.setDefaultCookieStore(cookieStore);
		}
		CloseableHttpClient httpclient = builder.build();
		try {
			HttpPost httpost = new HttpPost(url);
			httpost.setEntity(formEntity);
			//httpost.setHeader("content-Type", "multipart/form-data");
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity,"utf-8");
			return new HttpResult(content);
		} finally {
			httpclient.close();
		}
	}
	
	public static HttpResult postAlais(String url, List<NameValuePair> params, CookieStore cookieStore) throws Exception{
		HttpClientBuilder builder = HttpClientBuilder.create();
		if(cookieStore!=null){
			builder.setDefaultCookieStore(cookieStore);
		}
		CloseableHttpClient httpclient = builder.build();
		try {
			HttpPost httpost = new HttpPost(url);
			httpost.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			
			FileBody bin = new FileBody(new File("d:/upload/upload3.zip"));
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addPart("file", bin)
					.addTextBody("md5Value", "daac28ce67317c65b6cc3417b7682377")
					.setCharset(CharsetUtils.get("UTF-8")).build();
			httpost.setEntity(reqEntity);
			
			HttpResponse response = httpclient.execute(httpost);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity,"utf-8");
			return new HttpResult(content);
		} finally {
			httpclient.close();
		}
	}
	
	public static HttpResult post(String url, List<NameValuePair> params) throws Exception{
		return post(url, params, null);
	}
	
	public static HttpResult post(String url, Map<String, String> params) throws Exception{
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if(params != null){
			for(Entry<String, String> entry : params.entrySet()){
				nvps.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
			}
		}
		return post(url, nvps, null);
	}
	
}
