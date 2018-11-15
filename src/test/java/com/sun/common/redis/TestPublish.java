/**
 * 
 */
package com.sun.common.redis;

import org.junit.Test;

import com.sun.common.jedis.JedisUtils;

import redis.clients.jedis.Jedis;

public class TestPublish {
    @Test
    public void testPublish() throws Exception{
//        Jedis jedis = new Jedis("localhost");
        Jedis jedis = JedisUtils.getResource();
        jedis.publish("redisChatTest", "我是天才");
        Thread.sleep(2000);
        jedis.publish("redisChatTest", "我牛逼");
        Thread.sleep(2000);
        jedis.publish("redisChatTest", "哈哈");
    }



}
