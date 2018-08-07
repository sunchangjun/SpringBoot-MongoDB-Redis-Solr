/**
 * 
 */
package com.sun.crawl;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @author sunchangjunn 2018年8月7日下午3:13:59
 */
public class CrawlSinger {

	@Test
	public void test() {
		String baseUrl = "https://y.qq.com/portal/singer_list.html";
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		// 设置webClient的相关参数
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setTimeout(50000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		String listHtml = "";
		try {
			HtmlPage rootPage = webClient.getPage(baseUrl);
			Thread.sleep(4000);// 主要是这个线程的等待 因为js加载也是需要时间的
			System.out.println("线程结束沉睡");
			listHtml = rootPage.asXml();

		} catch (Exception e) {
		}

		String subUrl = "";
		try {
			Document document = Jsoup.parse(listHtml);// 获取html文档
			List<Element> infoListEle = document.getElementById("mod-singerlist").getElementsByAttributeValue("class","singer_list__item");// 获取元素节点等
			for (Element element : infoListEle) {
				subUrl = element.select("a").attr("href");
				System.out.println("title______" + element.select("a").attr("title"));
				System.out.println("data-singermid___" + element.select("a").attr("data-singermid"));
				System.out.println("data-singerid___" + element.select("a").attr("data-singerid"));
				System.out.println("___________");
				//爬歌手详情页面		
					HtmlPage subPage = webClient.getPage(subUrl);
					Thread.sleep(4000);// 主要是这个线程的等待 因为js加载也是需要时间的
					String subHtml = subPage.asXml();
					Document subDocument = Jsoup.parse(subHtml);
					List<Element> subElementList=	subDocument.getElementById("popup_data_detail").getElementsByAttributeValue("class","popup_data_detail__cont");
					System.out.println("::::::::::::::::::::::::::");
//					System.out.println(subElementList);
					Element subElement=subElementList.get(0);

					Elements country=subElement.select("p");
					for (Element element2 : country) {
						String x="^";
						System.out.println("'''''''''''''''''''''");
						System.out.println(element2);
						System.out.println("'''''''''''''''''''''");
						
					}
				
		

			}
		} catch (Exception e) {
			
		}
	
	

	}

}
