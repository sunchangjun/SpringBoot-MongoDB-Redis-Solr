package com.sun.common;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.lang.String;
 
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
 
public class TestMusic {
 
	public static void main(String[] args) throws Exception {
 
		Document doc = Jsoup.connect("http://www.xiami.com//artist/top-iim17edb"/*可输入任意艺人首页地址*/).get();// 访问艺人首页
		Elements chapterUrls = doc.select(".song_name");// 爬取歌曲列表
 
		for (Element chapterUrl : chapterUrls) {
			Element usefulChapterUrl = chapterUrl.select("a").get(0);// 访问歌曲而不是mv
			String chapterLocation = "http://www.xiami.com" + usefulChapterUrl.attr("href");// 获得歌曲地址
			Document chapterDoc = Jsoup.connect(chapterLocation)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Fi").get();// 访问歌曲页面
			String title = usefulChapterUrl.html().toString();// 歌名
 
			File file = new File("D:" + title + ".txt");// D盘创建文件
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			Elements ele = chapterDoc.select("#lrc").select(".lrc_main");// 爬取歌词
 
			// 以每句歌词换行的形式输入文件
			for (Element a : ele) {
				List<TextNode> texts = a.textNodes();
				for (int i = 0; i < texts.size(); i++) {
					TextNode t = texts.get(i);
					String content = t.text();
					bw.write(content);
					bw.newLine();
					System.out.println(content);
				}
 
			}
			bw.close();
		}
	}
}
