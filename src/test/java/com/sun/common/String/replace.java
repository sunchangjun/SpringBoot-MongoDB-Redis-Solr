/**
 * 
 */
package com.sun.common.String;

import java.util.UUID;

import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年8月9日下午6:01:51
 */
public class replace {
//	@Test
	public void test() {
		String str=" 中国  ";
		System.out.println(	str);
	
		
	}
	@Test
	public void test2() {
		 UUID uuid = UUID.randomUUID();
 		 String uuidString=uuid.toString().replace("-", "");
 		 System.out.println(uuidString);
 		 String subuuid=uuidString.substring(5, uuidString.length());
 		 System.out.println("CSPID"+subuuid);
 		 
		
	}

}
