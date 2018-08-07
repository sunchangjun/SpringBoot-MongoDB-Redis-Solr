package com.sun.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.bcel.generic.ARRAYLENGTH;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WTHXtest {

	public static void main(String[] args) {
		// 创建模拟浏览器对象
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);// 新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
		webClient.getOptions().setThrowExceptionOnScriptError(false);// 当JS执行出错的时候是否抛出异常, 这里选择不需要
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);// 当HTTP的状态非200时是否抛出异常, 这里选择不需要
		webClient.getOptions().setActiveXNative(false);
		webClient.getOptions().setCssEnabled(true);// 是否启用CSS, 因为不需要展现页面, 所以不需要启用
		webClient.getOptions().setJavaScriptEnabled(true); // 很重要，启用JS
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());// 很重要，设置支持AJAX

		List<String> list = new ArrayList<>(Arrays.asList(
				"https://y.qq.com/portal/album_lib.html#sin=0&&&&&num=20&get_tags=1&sort=2&year=-1&type=-1&genre=1&company=-1&area=1&",
				"https://y.qq.com/portal/album_lib.html#sin=0&&&&&num=20&get_tags=1&sort=2&year=-1&type=-1&genre=1&company=-1&area=0&",
				"https://y.qq.com/portal/album_lib.html#sin=0&&&&&num=20&get_tags=1&sort=2&year=-1&type=-1&genre=1&company=-1&area=3&"));
//		for (String string : list) {
//		
//
//		}

		HtmlPage page = null;
		try {
			// 尝试加载上面图片例子给出的网页
			page = webClient.getPage(
					"https://y.qq.com/portal/album_lib.html#sin=20&&&&&num=20&get_tags=1&sort=2&year=-1&type=-1&genre=1&company=-1&area=0&");
			Thread.sleep(3000);// 主要是这个线程的等待 因为js加载也是需要时间的
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			webClient.close();
		}

		String pageXml = page.asXml();// 直接将加载完成的页面转换成xml格式的字符串
		String  str=StringUtils.substringBetween(pageXml, "", "");
		System.out.println(pageXml);
		Document document = Jsoup.parse(pageXml);// 获取html文档
		Elements infoListEle = document.getElementById("album_list").getElementsByAttributeValue("class",
				"playlist__cover mod_cover");// 获取元素节点等
		for (Element element : infoListEle) {
			String htmlUrl = element.select("a").attr("abs:href");
			System.out.println(htmlUrl);

		}

	}

}
