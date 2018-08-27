/**
 * 
 */
package com.sun.common.redis;

import org.junit.Test;

import com.sun.common.jedis.JedisUtils;

/**
 * @author sunchangjunn
 * 2018年8月27日上午11:55:41
 */
public class RedisTest {
	
	@Test
	public void  test() {
		String value=JedisUtils.get("sun");
		System.out.println(value);
	}
}
