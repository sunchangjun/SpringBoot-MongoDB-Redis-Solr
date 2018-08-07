package com.sun.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



import com.alibaba.fastjson.JSONObject;
import com.sun.common.QqSongAlbum.Album;
import com.sun.common.okhttp3.OkHttp3Util;

public class WTHXMain {
	public static void main(String[] args) {
		OkHttp3Util httpclient = new OkHttp3Util();
		
		String  qqAlbumUrl="https://u.y.qq.com/cgi-bin/musicu.fcg?callback=getUCGI8812619174790346&g_tk=5381&jsonpCallback=getUCGI8812619174790346&loginUin=0&hostUin=0&format=jsonp&inCharset=utf8&outCharset=utf-8&notice=0&platform=yqq&needNewCode=0&data={\"albumlib\":{\"method\":\"get_album_by_tags\",\"param\":{\"area\":areaNumber,\"company\":-1,\"genre\":-1,\"type\":-1,\"year\":-1,\"sort\":2,\"get_tags\":1,\"sin\":sinNumber,\"num\":20,\"click_albumid\":0},\"module\":\"music.web_album_library\"}}";
		List<Integer> areaList=new  ArrayList<>(Arrays.asList(0,1,3,4,14,15));
		StringBuffer sb=new StringBuffer("专辑id:");
		//遍历地区
		for (Integer area : areaList) {			
				int i = 0;
				int pageTotal=0;
				do {	
					System.out.println(i);
					String	AlbumUrl = qqAlbumUrl.replace("areaNumber", area + "").replace("sinNumber", i + "");
					
//					System.out.println(AlbumUrl);
					String ret = httpclient.get(AlbumUrl);
					int start = ret.indexOf("(");
					int end = ret.lastIndexOf(")");
					ret = ret.substring(start + 1, end);
//					System.out.println("___"+ret);
					QqSongAlbum qqAlbum=	JSONObject.parseObject(ret, QqSongAlbum.class);
//					System.out.println(JSONObject.toJSONString(qqAlbum));
					if (null == qqAlbum || qqAlbum.getCode()== -500001) {
						break;
					}
					// 获取专辑id
					List<Album> albumList=qqAlbum.getAlbumlib().getData().getList();
					for (Album album : albumList) {
						sb.append(album.getAlbum_id()+",");
						
					}
					System.out.println(sb);
					

					// 获取页数
					int total = qqAlbum.getAlbumlib().getData().getTotal();
					 pageTotal = total % 20 > 0 ? total/20+1 : total/20;
					 i=i+20;
				} while (i <=pageTotal);
				
//				qqAlbumUrl=qqAlbumUrl.replace("areaNumber", area+"").replace("sinNumber", i+"");
//				System.out.println(qqAlbumUrl);
//				String ret = httpclient.get(qqAlbumUrl);
//				int start = ret.indexOf("(");
//				int end = ret.lastIndexOf(")");
//				ret = ret.substring(start+1, end);
////				System.out.println(ret);
//				QqSongAlbum qq=	JSONObject.parseObject(ret, QqSongAlbum.class);
//				System.out.println(JSONObject.toJSONString(qq));
			
			
			
		}
//		String respose = httpclient.get(url);
	

		}

		
		
	}


