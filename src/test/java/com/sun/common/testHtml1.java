package com.sun.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

public class testHtml1 {
	@Test
    public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		String url = "https://y.qq.com/portal/album_lib.html#sin=00&num=20&get_tags=1&sort=2&year=-1&type=-1&genre=-1&company=-1&area=1&";
		try {
		    WebClient webClient = new WebClient(BrowserVersion.CHROME);
		    //设置webClient的相关参数
		    webClient.getOptions().setJavaScriptEnabled(true);
		    webClient.getOptions().setCssEnabled(false);
		    webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		    //webClient.getOptions().setTimeout(50000);
		    webClient.getOptions().setThrowExceptionOnScriptError(false);
		    //模拟浏览器打开一个目标网址
		    HtmlPage rootPage = webClient.getPage(url);
		    System.out.println("为了获取js执行的数据 线程开始沉睡等待");
		    Thread.sleep(3000);//主要是这个线程的等待 因为js加载也是需要时间的
		    System.out.println("线程结束沉睡");
		    String html = rootPage.asText();
		    System.out.println(rootPage.asText());
		} catch (Exception e) {
		}
	}
}
