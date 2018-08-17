/**
 * 
 */
package com.sun.conversion;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.common.conversion.ConversionUtil;
import com.sun.mysql.entity.Album;

/**
 * @author sunchangjunn
 * 2018年8月16日下午2:08:58
 */
public class ClassToMap {
	@Test
	public void test() {
		Album album =new   Album();
		album.setAlbum_id(100);
		album.setAlbum_name("qiutian");
		
		System.out.println(JSONObject.toJSONString(ConversionUtil.javaBeanToMap(album)));;
		Map<String,Object> map=new HashMap<String,Object>();
		ObjectMapper oMapper = new ObjectMapper();
		map.put("album_id", 100);
		map.put("album_name", "qiutian");


		
		Album b=ConversionUtil.mapToJavaBean(map, Album.class);
		System.out.println(b.getAlbum_id());
		
	}
			

}
