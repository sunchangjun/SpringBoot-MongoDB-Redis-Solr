/**
 * 
 */
package com.sun.common.String;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * 字符串截取
 * @author sunchangjunn
 * 2018年8月8日下午6:29:15
 */
public class StringIntercept {
	
	@Test
	public void test() {
		String str="外文名：Joker";
		System.out.println(StringUtils.substringAfter(str, "外文名："));;
		
	}

}
