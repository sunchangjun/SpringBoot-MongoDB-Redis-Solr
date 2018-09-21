/**
 * 
 */
package com.sun.common.gc;

import java.util.ArrayList;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * @author sunchangjunn
 * 2018年8月17日下午6:36:38
 */
public class gcForTest {
	@Test
	public void test() {
		ArrayList aList = new ArrayList();
    	String a = new String("Good");
    	String b = new String("Nice");
    	aList.add(a);
    	aList.add(b);
    	
    	 
    	
//    	aList.clear();
    	System.out.println("a = " + a);
    	  a = null;
    	  b = null;    	
    	   System.out.println(aList.size());
    	   System.out.println(JSONObject.toJSONString(aList));
		
	}

}
