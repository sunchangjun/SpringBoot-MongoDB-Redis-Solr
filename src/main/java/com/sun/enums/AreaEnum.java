/**
 * 
 */
package com.sun.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

/**
 * @author sunchangjunn 2018年8月15日下午12:09:08
 */
public enum AreaEnum {
	
	AREA_200(Integer.valueOf("200"), "内地"),
	AREA_2(Integer.valueOf("2"), "港台"), 
	AREA_3(Integer.valueOf("3"), "韩国"),
	AREA_4(Integer.valueOf("4"), "日本"), 
	AREA_5(Integer.valueOf("5"), "欧美"),
	AREA_6(Integer.valueOf("6"), "其他"),
	;
	private static Map<Integer, String> AreaEnumMap=null;
	private static List<AreaEnum> areaEnumList = null;
	
	private Integer value;
	private String name;

	private AreaEnum(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static String getNameFromValue(Integer value) {
		if (value == null)
			return null;
		for (AreaEnum areaEnum : AreaEnum.values()) {
			if (areaEnum.getValue().equals(value))
				return areaEnum.getName();
		}
		return "";
	}
	
	

	public static Map<Integer, String> getValueNameMap(){
		if(null == AreaEnumMap) {
			AreaEnumMap=new HashMap<Integer, String>();
			for (AreaEnum areaEnum : AreaEnum.values()) {
				AreaEnumMap.put(areaEnum.getValue(), areaEnum.getName());
			}
		}
		return AreaEnumMap;
	}

	
	public static List<AreaEnum> getAreaEnums() {
		if(CollectionUtils.isEmpty(areaEnumList)) {
			areaEnumList = new ArrayList<AreaEnum>();
				for (AreaEnum areaEnum : AreaEnum.values()) {
					areaEnumList.add(areaEnum);
				}
		}	
		return areaEnumList;
	}

}
