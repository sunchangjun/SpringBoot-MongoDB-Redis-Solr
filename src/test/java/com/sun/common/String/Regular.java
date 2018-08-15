/**
 * 
 */
package com.sun.common.String;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author sunchangjunn 2018年8月7日下午6:34:27 正则表达式测试类
 */

public class Regular {

	@Test
	public void test() {
//		String contextRegs = "^外文名：Jay Chou$";
//		String context = "外文名：Jay Chou";
//
//		Pattern pattern = Pattern.compile(contextRegs);
//		Matcher matcher = pattern.matcher(context);
//		boolean rs = matcher.matches();
//		System.out.println(rs);

		String countryRegs = "^国籍：?[\u4e00-\u9fa5]+";
		String country = "国籍：中国 ";

        boolean status = country.contains("国籍：");
        System.out.println(status);
		System.out.println(country.matches(countryRegs));;
		Pattern pattern = Pattern.compile(countryRegs);
		Matcher matcher = pattern.matcher(country);
		System.out.println(matcher.matches());


	
//		
//		String testTxt = "国籍：中国 ";
//	//  注意[\u4E00-\u9FA5]里面的斜杠字符，千万不可省略，不区分大小写
//	Pattern pat = Pattern.compile("^国籍：*");
//	Matcher mat = pat.matcher(testTxt);
//	System.out.println(mat.matches());
	
	
  
	
	

	}
	
	

}
