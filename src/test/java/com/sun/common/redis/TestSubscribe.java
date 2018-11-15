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
 * 2018年11月15日下午4:12:15
 */
public class TestSubscribe {
    @Test
    public  void testSubscribe() throws Exception{
//        Jedis jedis = new Jedis("localhost");
        Jedis jedis = JedisUtils.getResource();
        RedisMsgPubSubListener listener = new RedisMsgPubSubListener();
        jedis.subscribe(listener, "redisChatTest");
        
        System.out.println("执行下面的代码");
        //other code
    }
    

}
