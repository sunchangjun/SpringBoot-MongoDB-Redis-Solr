/**
 * 
 */
package com.sun.ThreadPool;

import java.util.Queue;

/**
 * @author sunchangjunn
 * 2018年8月7日下午1:18:14
 */
public class testException {
	
	public static void test(Queue que) throws Exception {
		for (Object object : que) {
			System.out.println(object.toString());
			if(object.equals(2)) {
				throw new Exception("11111111111");
			}
			
		}
		
	}

}
