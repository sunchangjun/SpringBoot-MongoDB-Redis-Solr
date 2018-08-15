/**
 * 
 */
package com.sun.common.crawl.group;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author sunchangjunn
 * 2018年8月9日下午4:28:08
 */
public class group {
	
	public void test() {
	
		//假设得到的页面
		String html="";
		Document document = Jsoup.parse(html);
		//通过id和class获取元素
		Elements picSingerList = document.getElementById("mod-singerlist").getElementsByAttributeValue("class","singer_list__item");
		for (Element element : picSingerList) {
			//通过标签和属性得到属性值
			String	subUrl = element.select("a").attr("href");
			String singerName = element.select("a").attr("title");
			
			//<p>str<p>  检验内容是否包含某些,如果包含再处理提取内容
			boolean bool=element.toString().contains("str");;
			//提取文字
			String context=StringUtils.substringAfter("国家:中国", "国家:");
			
		}
		
		
		
		//也可以用jsoup自带的访问网页获取内容,但是底层调用的httpclient,缺点只能获取html静态页面,无法获取js加载后的数据
		try {
			Document doc = Jsoup.connect("http://example.com/").get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
