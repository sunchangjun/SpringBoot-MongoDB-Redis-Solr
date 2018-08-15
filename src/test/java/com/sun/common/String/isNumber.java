/**
 * 
 */
package com.sun.common.String;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年8月9日上午10:39:27
 */
public class isNumber {
	@Test
	public void test() {
		String str="1";
		System.out.println(StringUtils.isNumeric(str));
		System.out.println(StringUtils.isNumericSpace(str));
		
	}

}
