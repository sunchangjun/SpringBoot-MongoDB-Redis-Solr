/**
 * 
 */
package com.sun.common.swith;

import org.junit.Test;

import com.sun.rnum.QmResType;

/**
 * @author sunchangjunn
 * 2018年8月23日上午10:38:27
 */
public class swith {
	@Test
	public void test() {
		
		Integer i=1;
		switch (i) {
		case 1:
			System.out.println(1);
			break;
		case 2:
			System.out.println(2);
			break;

		default:
			break;
		}
		
	}

}
