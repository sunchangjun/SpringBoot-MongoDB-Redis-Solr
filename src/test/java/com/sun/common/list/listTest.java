/**
 * 
 */
package com.sun.common.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * @author sunchangjunn
 * 2018年8月8日下午5:48:16
 */
public class listTest {
	
	public static void main(String[] args) {
		List<Integer> a=new ArrayList<Integer>(Arrays.asList(1,2));
		List<Integer> b=new ArrayList<Integer>(Arrays.asList(3,4));
		List<Integer> c=new ArrayList<Integer>();
		c.addAll(a);
		c.addAll(b);
		System.out.println(JSONObject.toJSONString(c));
	}
	


}
