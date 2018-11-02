/**
 * 
 */
package com.sun.crawl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
//import org.junit.Test;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sun.mysql.entity.QmSinger;
import com.sun.common.conversion.JsonForMat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author sunchangjunn 2018年8月7日下午3:13:59
 */
public class CrawlSinger {
	private static final Logger logger = LoggerFactory.getLogger(CrawlSinger.class);

//	@Test
	public void test() {
		String root="https://y.qq.com/portal/singer_list.html#index=%s&area=%s&sex=%s&genre=%s&page=1&";
		String areas="200";
		String[] areaArray=areas.split(",");
		String types="1";
		String[] typeArray=types.split(",");
		String grens="1";
		String[] grenArray=grens.split(",");
		
		
		for (String area : areaArray) {
			for (String type : typeArray) {
				for (String genre : grenArray) {
					crawlSingerHtml("-100", area, type, genre,1);
					System.out.println("下一想");
					
				}
			}
		}
	
		

	}
	
	
	public void crawlSingerHtml(String index,String area,String type,String genre,int currentPage) {
		int endPage=0;
		WebClient webClient = new WebClient(BrowserVersion.CHROME);
		// 设置webClient的相关参数
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setTimeout(50000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		String rootUrl="https://y.qq.com/portal/singer_list.html#index=%s&area=%s&sex=%s&genre=%s&page=%s&";		
		boolean flag=true;
	
		do {
			currentPage++;
			String requestUrl=	String.format(rootUrl, index,area,type,genre,currentPage);
		
			
			
			String listHtml = "";
			try {
				System.out.println(requestUrl);
				HtmlPage rootPage = webClient.getPage(requestUrl);
				Thread.sleep(4000);// 主要是这个线程的等待 因为js加载也是需要时间的
				listHtml = rootPage.asXml();

			} catch (Exception e) {
			}

			String subUrl = "";
			try {

				Document document = Jsoup.parse(listHtml);// 获取html文档
				Thread.sleep(4000);
				//获取最后一页参数
				List<Integer> sortpageList=new  ArrayList<Integer>();
				List<Element> pageParams = document.getElementsByAttributeValue("class","js_pageindex");
				for (Element element : pageParams) {
					if(StringUtils.isNumeric(element.text())) {
						sortpageList.add(Integer.valueOf(element.text()));
					}					
				}
				sort(sortpageList);
				System.out.println(sortpageList);
				
				
				
				List<Element> pageList=new ArrayList<Element>();				
				List<Element> picSingerList = document.getElementById("mod-singerlist").
						getElementsByAttributeValue("class","singer_list__item");
				List<Element> testSingerList = document.getElementById("mod-singerlist").
						getElementsByAttributeValue("class","singer_list_txt__link js_singer");
				pageList.addAll(picSingerList);
				pageList.addAll(testSingerList);
				if(CollectionUtils.isEmpty(pageList)) {
					flag=false;
					System.out.println(false+"++++++++++++++++++++++++++++");
//					return;
				}
				for (Element element : pageList) {		
					subUrl = element.select("a").attr("href");
					String singerName = element.select("a").attr("title");
					String singermid = element.select("a").attr("data-singermid");
					String singerid = element.select("a").attr("data-singerid");
					// 爬歌手详情页面
					HtmlPage subPage = webClient.getPage(subUrl);
					Thread.sleep(4000);// 主要是这个线程的等待 因为js加载也是需要时间的
					String subHtml = subPage.asXml();
					Document subDocument = Jsoup.parse(subHtml);
					Elements  picElement=subDocument.getElementsByAttributeValue("class", "data__photo js_index");
					String picUrl=picElement.get(0).select("img").attr("src");
					List<Element> subElementList = subDocument.getElementById("popup_data_detail").getElementsByAttributeValue("class", "popup_data_detail__cont");
					Element subElement = subElementList.get(0);



					
					
					QmSinger qmSinger = new QmSinger();
					qmSinger.setSingerId(Long.valueOf(singerid));
					qmSinger.setSingerName(singerName);
					qmSinger.setSingerMid(singermid);
					qmSinger.setSingerPic("http:"+picUrl);
					qmSinger.setArea(Integer.valueOf(area));
					qmSinger.setGenre(Integer.valueOf(genre));
					qmSinger.setType(Integer.valueOf(type));
					StringBuffer sb = new StringBuffer();
					Elements jianjie = subElement.select("p");
					for (Element element2 : jianjie) {					
						if (element2.toString().contains("<p> 外文名：")) {
							if(StringUtils.isNotBlank(element2.text())) {
								qmSinger.setSingerTranslatorName(element2.text());
							}					
						} else if (element2.toString().contains("<p> 国籍：")) {
							if(StringUtils.isNotBlank(element2.text())) {
								qmSinger.setCountry(element2.text());
							}	
						} else if (element2.toString().contains("<p> 出生地：")) {
							if(StringUtils.isNotBlank(element2.text())) {
								qmSinger.setBirthplace(element2.text());
							}
						} else if (element2.toString().contains("<p> 职业：")) {
							if(StringUtils.isNotBlank(element2.text())) {
								qmSinger.setProfession(element2.text());
							}
						} else if (element2.toString().contains("<p> 代表作品")) {
							if(StringUtils.isNotBlank(element2.text())) {
								qmSinger.setProduction(element2.text());
							}
						}else {
								sb.append(element2.toString());
						}
					}
					qmSinger.setIntro(sb.toString().replace(" ", ""));
					System.out.println(sb.toString().replace(" ", ""));
					System.out.println(JsonForMat.format(JSONObject.toJSONString(qmSinger)));
					
					try {
						
					} catch (Exception e) {
						// TODO: handle exception
					}
				
				}
			} catch (Exception e) {

			}
		} while (flag);

		
		
	}
	public List<Integer>  sort(List<Integer> list){
		Collections.sort(list, new Comparator<Integer>(){
			@Override
			public int compare(Integer arg0, Integer arg1) {
				  if(arg0 < arg1){
	                    return 1;
	                }
	                if(arg0 == arg1){
	                    return 0;
	                }
	                return -1;			
			}			
		});		
		return list;
	}
}
