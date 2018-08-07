/**
 * 
 */
package com.sun.common.String;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年8月7日下午6:34:27
 * 正则表达式测试类
 */


public class Regular {
	
	@Test
	public void test() {
		String contextRegs="^<p> 外文名：* </p>$";
		String context="<p> 外文名：Joker </p>";
		 System.out.println(context.matches(contextRegs));
		 
		
		 String countryRegs="^<p> 国籍：* </p>$";
		String country="<p> 国籍：中国 </p>";
		System.out.println(country.matches(countryRegs));
		

		
		
		
	}

}
