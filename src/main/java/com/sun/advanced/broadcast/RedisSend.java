/**
 * 
 */
package com.sun.advanced.broadcast;

import com.sun.common.jedis.JedisUtils;

/**
 * @author sunchangjunn
 * 2018年11月15日下午3:47:54
 */
public class RedisSend {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			JedisUtils.publish("test", i+"");		

			
		}
		System.out.println("发送结束 ");
	}

}
