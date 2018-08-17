
package com.sun.common.conversion;

import java.util.Map;
import org.apache.poi.ss.formula.functions.T;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 转换工具类
 * @author sunchangjunn 2018年8月16日下午2:29:04
 */
public class ConversionUtil {
	
	/**
	 * javaBean转换为map
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> javaBeanToMap(Object javaBean) {
		ObjectMapper oMapper = new ObjectMapper();
		Map<String, Object> map = oMapper.convertValue(javaBean, Map.class);
		return  map;
	}
	
	
	/**
	 * map转换为javaBean
	 * @param map
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("hiding")
	public static <T> T mapToJavaBean(Map<String,Object> map,Class<T> clazz ) {
		ObjectMapper oMapper = new ObjectMapper();
		return	oMapper.convertValue(map,clazz);
		
	}

}
