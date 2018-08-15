/**
 * 
 */
package com.sun.rnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Alex Tang
 * 2018年4月8日
 * desc:
 */
public enum QmResType {
	QM_TYPE(1,"分类"),
	SONG(2,"歌曲"),
	SINGER(3,"歌手"),
	MV(4,"MV"),
	ALBUM(5,"专辑"),
	DISS(6,"歌单");
	QmResType(Integer code,String name){
		this.code = code;
		this.name = name;
	}
	private Integer code;
	private String name;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private static Map<Integer,String> resTypeMap = null;
	public static Map<Integer,String> getResTypeMap(){
		if(resTypeMap == null){
			resTypeMap = new HashMap<>();
			for(QmResType type : QmResType.values()){
				resTypeMap.put(type.getCode(), type.getName());
			}
		}
		return resTypeMap;
	}
}
