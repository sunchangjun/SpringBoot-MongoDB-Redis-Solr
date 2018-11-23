/**
 * 
 */
package com.sun.common.Http;

import org.junit.Test;

import com.sun.common.http.HttpURLConnectionUtil;

/**
 * @author sunchangjunn
 * 2018年11月19日下午5:05:07
 */
public class HttpUrlConentTest {
	
	public  void  test() {
		String url="https://y.qq.com/download/download.js?jsonpCallback=MusicJsonCallback&loginUin=1833896677&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0";
		String  param="";
		String  reponse=HttpURLConnectionUtil.sendPostRequest(url, param);
		System.out.println(reponse);
	}
	@Test
	public  void  testget() {
		String  reponse=HttpURLConnectionUtil.sendGetRequest("http://192.168.3.183:5000/");
		System.out.println(reponse);
	}

}
