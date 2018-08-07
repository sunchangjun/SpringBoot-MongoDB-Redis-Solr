package com.sun.common;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class testHtmlUtil {
	
	@Test
    public void test() {
        final WebClient webClient = new WebClient(BrowserVersion.CHROME);//新建一个模拟谷歌Chrome浏览器的浏览器客户端对象

        webClient.getOptions().setThrowExceptionOnScriptError(false);//当JS执行出错的时候是否抛出异常, 这里选择不需要
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);//当HTTP的状态非200时是否抛出异常, 这里选择不需要
        webClient.getOptions().setActiveXNative(false);
        webClient.getOptions().setCssEnabled(true);//是否启用CSS, 因为不需要展现页面, 所以不需要启用
        webClient.getOptions().setJavaScriptEnabled(true); //很重要，启用JS
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//很重要，设置支持AJAX

        HtmlPage page = null;
        try {
//            page = webClient.getPage("http://ent.sina.com.cn/film/");//尝试加载上面图片例子给出的网页
        	//尝试加载上面图片例子给出的网页
            page = webClient.getPage("https://y.qq.com/portal/album_lib.html#sin=400&num=20&get_tags=1&sort=2&year=-1&type=-1&genre=-1&company=-1&area=1&");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            webClient.close();
        }

        webClient.waitForBackgroundJavaScript(30000);//异步JS执行需要耗时,所以这里线程要阻塞30秒,等待异步JS执行结束

        String pageXml = page.asXml();//直接将加载完成的页面转换成xml格式的字符串

        //TODO 下面的代码就是对字符串的操作了,常规的爬虫操作,用到了比较好用的Jsoup库

        Document document = Jsoup.parse(pageXml);//获取html文档
//        System.out.println(document);
        Element link = document.select("a").first();
        String relHref = link.attr("href"); // == "/"
        String absHref = link.attr("abs:href"); // "http://www.xttblog.com/"
        System.out.println(absHref);
        List<Element> infoListEle = document.getElementById("album_list").getElementsByAttributeValue("class", "playlist__list");//获取元素节点等
      System.out.println(infoListEle);
//        infoListEle.forEach(element -> {
//            System.out.println(element.getElementsByTag("h2").first().getElementsByTag("a").text());
//            System.out.println(element.getElementsByTag("h2").first().getElementsByTag("a").attr("href"));
//        }
//	);
    }

}
