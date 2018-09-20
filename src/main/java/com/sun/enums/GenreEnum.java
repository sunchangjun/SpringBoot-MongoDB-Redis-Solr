package com.sun.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

public enum GenreEnum {


	// val, name
	GENRE_1(Integer.valueOf("1"), "流行"), GENRE_2(Integer.valueOf("2"), "摇滚"), GENRE_3(Integer.valueOf("3"), "民谣"),
	GENRE_4(Integer.valueOf("4"), "电子"), GENRE_5(Integer.valueOf("5"), "爵士"), GENRE_6(Integer.valueOf("6"), "嘻哈"),
	GENRE_9(Integer.valueOf("9"), "轻音乐"), GENRE_10(Integer.valueOf("10"), "民歌"), GENRE_14(Integer.valueOf("14"), "古典"),
	GENRE_22(Integer.valueOf("22"), "蓝调"), GENRE_25(Integer.valueOf("25"), "乡村"),;
	

	private Integer value;
	private String name;

	private GenreEnum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	
	
	private static Map<Integer,String> genreEnumValueNameMap = null;
	public static Map<Integer,String> getValueNameMap(){
		if(null == genreEnumValueNameMap) {
			genreEnumValueNameMap=new  HashMap<Integer,String>();
			for (GenreEnum genreEnum : GenreEnum.values()) {
				genreEnumValueNameMap.put(genreEnum.getValue(), genreEnum.getName());
			}
		}
		return  genreEnumValueNameMap;
	}
	
	
	
	public static String getNameFromValue(Integer vue) {
		for (GenreEnum genreEnum : GenreEnum.values()) {
			if(genreEnum.getValue().equals(vue)) {
				return genreEnum.getName();
			}		
		}		
		return "";
	}


	private static List<GenreEnum> genreEnumList = null;
	public static List<GenreEnum> getGenreEnums() {
		if(CollectionUtils.isEmpty(genreEnumList)) {
			genreEnumList = new ArrayList<GenreEnum>();
			for (GenreEnum genreEnum : GenreEnum.values()) {
				genreEnumList.add(genreEnum);
			}
		}
		return genreEnumList;
	}

}
