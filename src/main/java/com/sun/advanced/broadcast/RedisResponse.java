/**
 * 
 */
package com.sun.advanced.broadcast;

import com.sun.common.jedis.JedisUtils;

/**
 * @author sunchangjunn
 * 2018年11月15日下午3:38:20
 */
public class RedisResponse {
	
	public static void main(String[] args) {
		MsgListener msgListener = new  MsgListener();
		msgListener.onSubscribe("test", 10);
		JedisUtils.subscribe(msgListener, "test");
		System.out.println("结束");
	}

}
