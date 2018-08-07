package com.sun.common;

import java.io.IOException;

import javax.lang.model.util.Elements;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.sun.base.BaseJunitTest;
import com.sun.base.JsonForMat;
import com.sun.common.okhttp3.OkHttp3Util;

public class testJsoup extends BaseJunitTest {

	@Test
	public void test() throws IOException {
		OkHttp3Util httpclient = new OkHttp3Util();
		String url = "https://y.qq.com/n/yqq/album/003BLKps37CfPE.html#stat=y_new.album_lib.album_pic";
		String respose = httpclient.get(url);		
		String str=StringUtils.substringBetween(respose, "albumid :", "albummid :");//substringAfterLast(respose, "albummid");
		System.out.println(str.trim().replace(",", ""));
//		Document doc = Jsoup.parse(respose);
//		System.out.println(doc);
//		System.out.println(doc.getElementById("albumid"));
		

	}

}
