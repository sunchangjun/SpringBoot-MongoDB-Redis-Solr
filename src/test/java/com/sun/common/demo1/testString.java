package com.sun.common.demo1;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class testString {
	public static void main(String[] args) {
//		String str="专辑中无歌曲信息:4184664";
//		System.out.println(StringUtils.substringAfter(str, ":"));
		String[] stringArray="15,5".split(",");
		System.out.println(JSONObject.toJSONString(stringArray));

	}

}
