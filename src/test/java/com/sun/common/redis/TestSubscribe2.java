/**
 * 
 */
package com.sun.common.redis;

import org.junit.Test;

import com.sun.advanced.broadcast.RedisMsgPubSubListener;
import com.sun.common.jedis.JedisUtils;

import redis.clients.jedis.Jedis;

/**
 * @author sunchangjunn
 * 2018年11月15日下午4:17:49
 */
public class TestSubscribe2 {
	 @Test
	    public void testPublish() throws Exception{
		 while(true) {
			  Jedis jedis = JedisUtils.getResource();
		      RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
		      jedis.subscribe(listener, "redisChatTest");
		 }
	
      
	 }
}
