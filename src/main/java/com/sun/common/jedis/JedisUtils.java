/**
 * 
 */
package com.sun.common.jedis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public static String set(String key,Object value) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
            if (value instanceof  String){
                result = jedis.set(key,(String) value);
            }else{
                result = jedis.set(key,JSONObject.toJSONString(value));
            }
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
	/*删除key*/
	public static Long del(String key) {
		Long result = null;
		Jedis jedis = null;
		try {
			jedis = getResource();
			result = jedis.del(key);
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


    /*设置缓存并设置过期时间(秒)*/
    public static String setex (String key,int seconds ,String value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.setex (key,seconds,value);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /*设置缓存并设置过期时间(耗秒)*/
    public static String psetex (String key,int seconds ,String value) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.psetex (key,seconds,value);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /*设置某时间点过期(秒)*/
    public static Long expireAt (String key,long timestamp ,String value) {
        Long result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.set(key,value);
            result = jedis.expireAt(key,timestamp);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            returnResource(jedis);
        }
        return result;
    }

    /*设置某时间点过期(毫秒)*/
    public static Long pexpireAt (String key,long timestamp ,String value) {
        Long result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.set(key,value);
            result = jedis.pexpireAt(key,timestamp);
        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            returnResource(jedis);
        }
        return result;
    }
    /*移除key的过期时间*/
    public static Long persist (String key) {
        Long result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.persist(key);

        } catch (Exception e) {
            logger.info(e.getMessage());
        } finally {
            returnResource(jedis);
        }
        return result;
    }
    /*设置对象列表缓存*/
    public static <T> boolean setList(String key, List<T> list) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = getResource();
            if (jedis != null) {
                for (T vz : list) {
                    if (vz instanceof String) {
                        jedis.lpush(key, (String) vz);
                    } else {
                        String  objectJson=JSONObject.toJSONString(vz);
                        jedis.lpush(key, objectJson);
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /*返回列表对象*/
	public static <T> List<T> getListEntity(String key, Class<T> entityClass) {
        Jedis jedis = null;
        String result = null;
		try {
            jedis = getResource();
			if (jedis != null) {
				List<String> valueJson = jedis.lrange(key, 0, -1);
				JSONArray json = new JSONArray();
				json.addAll(valueJson);
                return	JSONObject.parseArray(JSONObject.toJSONString(json),entityClass);
			} else {
				return null;
			}
		} catch (Exception e) {
            logger.info(e.getMessage());
			return null;
		} finally {
			returnResource(jedis);
		}
	}

    /*设置map*/
    public static <K, V> boolean setMap(String key, Map<String, V> map) {
        Jedis jedis = null;
        String result = null;
        try {
            jedis = getResource();
            if (jedis != null) {
                Set<Map.Entry<String, V>> entry = map.entrySet();
                for (Iterator<Map.Entry<String, V>> ite = entry.iterator(); ite.hasNext();) {
                    Map.Entry<String, V> kv = ite.next();
                    if (kv.getValue() instanceof String) {
                        jedis.hset(key, kv.getKey(), (String) kv.getValue());
                    } else if (kv.getValue() instanceof List) {
                        jedis.hset(key, kv.getKey(), JSONObject.toJSONString(kv.getValue()));
                    } else {
                        jedis.hset(key, kv.getKey(), JSONObject.toJSONString(kv.getValue()));
                    }
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }


    /*返回Map*/
	public static <K, V> Map<String, V> getMap(String key) {
            Jedis jedis = null;
		try {
            jedis = getResource();
			if (jedis != null) {
				Map<String, V> map = (Map<String, V>) jedis.hgetAll(key);
				return map;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			returnResource(jedis);
		}
	}
    /* incr(key)：名称为key的string增1操作 */
    public static boolean incr(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.incr(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * incrby(key, integer)：名称为key的string增加integer
     */
    public static boolean incrBy(String key, int value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.incrBy(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**   * decr(key)：名称为key的string减1操作  */
    public static boolean decr(String key) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.decr(key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * decrby(key, integer)：名称为key的string减少integer
     */
    public static boolean decrBy(String key, int value) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.decrBy(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            returnResource(jedis);
        }
    }

}
