/**
 * 
 */
package com.sun.common.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.bcel.generic.ARRAYLENGTH;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author sunchangjunn
 * 2018年8月10日上午10:43:32
 */
public class Sort {
	
	@Test
	public void test() {
		List<String> list=new ArrayList<String>(Arrays.asList("1","5","2","10","8"));

		/**
		 * 升序
		 */
		Collections.sort(list);
		


//		/**
//		 * 倒转之前的顺序
//		 */
//		Collections.reverse(list);
	  
		System.out.println(JSONObject.toJSONString(list));
	}

}
