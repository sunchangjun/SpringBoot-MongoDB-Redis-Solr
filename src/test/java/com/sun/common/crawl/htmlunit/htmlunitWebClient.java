/**
 * 
 */
package com.sun.common.crawl.htmlunit;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


/**
 * @author sunchangjunn
 * 2018年8月9日下午4:22:34
 */
public class htmlunitWebClient {
	
	public void test() {
		  final WebClient webClient = new WebClient(BrowserVersion.CHROME);//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象
	        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
	        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
	        webClient.getOptions().setActiveXNative(false);
	        webClient.getOptions().setCssEnabled(true);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
	        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
	        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX
	        

	        String listHtml = "";
	        try {
	        	HtmlPage    page = webClient.getPage("https://y.qq.com/portal/album_lib.html#sin=400&num=20&get_tags=1&sort=2&year=-1&type=-1&genre=-1&company=-1&area=1&");
	        	Thread.sleep(4000);// 主要是这个线程的等待 因为js加载也是需要时间的
				listHtml = page.asXml();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        //得到js加载后的页面,listHtml用group解析
		
	}

}
