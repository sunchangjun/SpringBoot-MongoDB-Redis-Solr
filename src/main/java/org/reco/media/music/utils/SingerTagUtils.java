package org.reco.media.music.utils;

import java.util.HashMap;
import java.util.Map;

public class SingerTagUtils {
	private static Map<Integer,String> areaMap = new HashMap<Integer,String>();
	private static Map<Integer,String> sexMap = new HashMap<Integer,String>();
	private static Map<Integer,String> genreMap = new HashMap<Integer,String>();
	
	static{
		areaMap.put(-100, "t1");
		areaMap.put(200, "t4");
		areaMap.put(2, "t5");
		areaMap.put(3, "t6");
		areaMap.put(4, "t7");
		areaMap.put(5, "t8");
		areaMap.put(6, "t9");
		
		sexMap.put(-100, "t2");
		sexMap.put(0, "t10");
		sexMap.put(1, "t11");
		sexMap.put(2, "t12");
		
		genreMap.put(-100, "t3");
		genreMap.put(1, "t13");
		genreMap.put(2, "t14");
		genreMap.put(3, "t15");
		genreMap.put(4, "t16");
		genreMap.put(5, "t17");
		genreMap.put(6, "t18");
		genreMap.put(7, "t19");
		genreMap.put(8, "t20");
		genreMap.put(9, "t21");
		genreMap.put(10, "t22");
		genreMap.put(14, "t23");
		genreMap.put(20, "t24");
		genreMap.put(25, "t25");
	}
	
	public static String getSolrAreaTag(Integer qqtag){
		return areaMap.get(qqtag);
	}
	
	public static String getSolrSexTag(Integer qqtag){
		return sexMap.get(qqtag);
	}
	
	public static String getSolrGenreTag(Integer qqtag){
		return genreMap.get(qqtag);
	}
	
}
