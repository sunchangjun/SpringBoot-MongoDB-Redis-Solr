/**
 * 
 */
package com.sun.common.String;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年8月9日下午2:14:22
 */
public class contains {
	@Test
	public void test() {
//		String str="<p>生日：1989-07-03</p>";
//		boolean bool=StringUtils.contains(str, "<p> 代表作品");
//		System.out.println(bool);

		String one ="这一段话能否被匹配";

		boolean bool=StringUtils.contains(one, "一段话");
		System.out.println(bool);
	}

}
