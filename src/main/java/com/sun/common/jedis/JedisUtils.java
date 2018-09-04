/**
 * 
 */
package com.sun.common.jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

/**
 * @author sunchangjunn
 * 2018年8月27日上午11:45:42
 */
public class JedisUtils {
	
	private static Logger logger = LoggerFactory.getLogger(JedisUtils.class);
	
	private static JedisPool jedisPool = null;
	 
	//Redis服务器IP
    private static String ADDR ="47.98.153.144";
    
    //Redis的端口号
    private static int PORT = 6379;
    
    //访问密码
    private static String AUTH = "scj19890606";
    
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;
    
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;
    
    private static int TIMEOUT = 10000;
    
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;
    /**
             * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
           jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
//            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	
	/**
	 * 获取资源
	 * @return
	 * @throws JedisException
	 */
	public static Jedis getResource() throws JedisException {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();

		} catch (JedisException e) {
			logger.warn("getResource.", e);
			returnBrokenResource(jedis);
			throw e;
		}
		return jedis;
	}

	public static void returnBrokenResource(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnBrokenResource(jedis);
		}
	}
	

	public static void returnResource(Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}


	/*获取缓存*/
	public static String get(String key) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.get(key);
		
	
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return result;
	}


	/**
	 * 向链表尾部插入元素
	 * @param key
	 * @param value
	 * @return
	 */
	public static Long rpush(String key,String value) {
		Long result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result= jedis.rpush(key,value);


		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return result;
	}


	/**
	 * 移除并返回链表头部的元素
	 * @param key
	 * @return
	 */
	public static String lpop (String key) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result= jedis.lpop (key);


		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*设置缓存*/
	public static String set(String key,String value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.set(key,value);
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*删除链表中的元素*/
	public static Long lrem(String key,long count,String value) {
		Long result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.lrem (key,count,value);
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return result;
	}


	/*查询key是否存在*/
	public static Boolean lrem(String key) {
		Boolean result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.exists(key);
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return result;
	}

	/*查询key的生命周期(毫秒)*/
	public static Long pttl  (String key) {
		Long result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.pttl  (key);
		} catch (Exception e) {
			logger.info(e.getMessage());
		} finally {
			returnResource(jedis);
		}
		return result;
	}
}
