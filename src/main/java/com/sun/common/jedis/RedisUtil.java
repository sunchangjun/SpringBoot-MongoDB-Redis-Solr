//package com.sun.common.jedis;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.ResourceBundle;
//import java.util.Set;
//
//import com.alibaba.fastjson.JSONObject;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//import redis.clients.jedis.JedisShardInfo;
//import redis.clients.jedis.ShardedJedis;
//import redis.clients.jedis.ShardedJedisPool;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class RedisUtil {
//	private static Logger log = LoggerFactory.getLogger(RedisUtil.class);
//
//	private static JedisPool jedisPool = null;
////	private static ShardedJedisPool shardedJedisPool = null;
//
//	// 可用连接实例的最大数目，默认值为8；
//	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
//	private static int MAX_ACTIVE = 1024;
//
//	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
//	private static int MAX_IDLE = 20;
//
//	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
//	private static int MAX_WAIT = 10000;
//
//	private static boolean TEST_ON_BORROW = true;
//
//	private static boolean TESTONRETURN = false;
//
//	/**
//	 * 初始化Redis连接池
//	 */
//	static {
//		try {
//			// 加载redis配置文件
//			ResourceBundle bundle = ResourceBundle.getBundle("application-dev");
//			if (bundle == null) {
//				throw new IllegalArgumentException("[application-dev.properties] is not found!");
//			}
//			// 创建jedis池配置实例
//			JedisPoolConfig config = new JedisPoolConfig();
//			// 设置池配置项值
//			config.setMaxTotal(MAX_ACTIVE);
//			config.setMaxIdle(MAX_IDLE);
//			config.setMaxWaitMillis(MAX_WAIT);
//			config.setMaxIdle(MAX_IDLE); // 最大空闲连接数
//			config.setTestOnBorrow(TEST_ON_BORROW);
//			config.setTestOnReturn(TESTONRETURN);
//			jedisPool = new JedisPool(config, bundle.getString("spring.redis.host"),
//					Integer.valueOf(bundle.getString("spring.redis.port")), 10000);
//			// ,bundle.getString("spring.redis.password"));//有密码需要设置密码
//
////			 // slave链接
////			 List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
//////			 shards.add(new JedisShardInfo(bundle.getString("redis.ip"),Integer.valueOf(bundle.getString("redis.port1"))));
////			 shards.add(new JedisShardInfo(bundle.getString(bundle.getString("spring.redis.host")),Integer.valueOf(bundle.getString("spring.redis.port"))));
////			 shardedJedisPool = new ShardedJedisPool(config, shards);
//			 log.info("初始化Redis连接池success");
//		} catch (Exception e) {
//			log.error("初始化Redis连接池 出错！", e);
//		}
//	}
//
//
//	/**
//	 * 获取Jedis实例
//	 *
//	 * @return
//	 */
//	public synchronized static Jedis getJedis() {
//		try {
//			if (jedisPool != null) {
//				Jedis resource = jedisPool.getResource();
//				return resource;
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			log.error("Redis缓存获取Jedis实例 出错！", e);
//			return null;
//		}
//	}
//
////	/**
////	 * 获取shardedJedis实例
////	 *
////	 * @return
////	 */
////	public static ShardedJedis getShardedJedis() {
////		try {
////			if (shardedJedisPool != null) {
////				ShardedJedis resource = shardedJedisPool.getResource();
////				return resource;
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			log.error("Redis缓存获取shardedJedis实例 出错！", e);
////			return null;
////		}
////	}
//
//	/**
//	 * 释放jedis资源
//	 *
//	 * @param jedis
//	 */
//	public static void returnResource(final Jedis jedis) {
//		if (jedis != null) {
//			jedisPool.returnResource(jedis);
//		}
//	}
//
////	/**
////	 * 释放shardedJedis资源
////	 *
////	 * @param jedis
////	 */
////	public static void returnResource(final ShardedJedis shardedJedis) {
////		if (shardedJedis != null) {
////			shardedJedisPool.returnResource(shardedJedis);
////		}
////	}
//
//	/**
//	 * 向缓存中设置字符串内容
//	 *
//	 * @param key
//	 *            key
//	 * @param value
//	 *            value
//	 * @return
//	 * @throws Exception
//	 */
//	public static boolean set(String key, String value) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			if (jedis != null) {
//				jedis.set(key, value);
//			}
//			return true;
//		} catch (Exception e) {
//			log.error("Redis缓存设置key值 出错！", e);
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//	public static boolean setex(String key, String value,int seconds) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			if (jedis != null) {
//				jedis.setex(key, seconds, value);
//			}
//			return true;
//		} catch (Exception e) {
//			log.error("Redis缓存设置key值 出错！", e);
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
////    /**
////     * <p>设置key value并制定这个键值的有效期</p>
////     * @param key
////     * @param value
////     * @param seconds 单位:秒
////     * @return 成功返回OK 失败和异常返回null
////     */
////    public static String setex(String key,String value,int seconds){
////        Jedis jedis = null;
////        String res = null;
////        try {
////            jedis = jedisPool.getResource();
////            res = jedis.setex(key, seconds, value);
////        } catch (Exception e) {
////
////        	log.error(e.getMessage());
////        } finally {
////        	returnResource(jedis);
////        }
////        return res;
////    }
////	/**
////	 * 判断key是否存在
////	 */
////	public static boolean exists(String key) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis == null) {
////				return false;
////			} else {
////				return jedis.exists(key);
////			}
////		} catch (Exception e) {
////			log.error("Redis缓存判断key是否存在 出错！", e);
////			return false;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
//	/**
//	 * 删除缓存中的对象，根据key
//	 *
//	 * @param key
//	 * @return
//	 */
//	public static boolean del(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.del(key);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	// *******************key-value****************start
//
//	/**
//	 * 向缓存中设置对象
//	 *
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public static boolean set(String key, Object value) {
//		Jedis jedis = null;
//		try {
//			String objectJson = JSONObject.toJSONString(value);
//			jedis = getJedis();
//			if (jedis != null) {
//				jedis.set(key, objectJson);
//			}
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	/**
//	 * 根据key 获取内容
//	 *
//	 * @param key
//	 * @return
//	 */
//	public static Object get(String key) {
////		ShardedJedis jedis = null;
//		Jedis jedis = null;
//		try {
////			jedis = shardedJedisPool.getResource();
//			jedis = getJedis();
//			Object value = jedis.get(key);
//			return value;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
////			shardedJedisPool.returnResource(jedis);
//			returnResource(jedis);
//		}
//	}
//
//	/**
//	 * 根据key 获取对象
//	 *
//	 * @param key
//	 * @return
//	 */
////	@SuppressWarnings("unchecked")
////	public static <T> T get(String key, Class<T> clazz) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis != null) {
////				return (T) JSONObject.toBean(JSONObject.fromObject(jedis.get(key)), clazz);
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//	// *******************key-value****************end
//
//	// *************** 操作list****************start
//	/**
//	 * 向缓存中设置对象
//	 *
//	 * @param key
//	 * @param list
//	 *            T string calss
//	 * @return
//	 */
//	public static <T> boolean setList(String key, List<T> list) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			if (jedis != null) {
//				for (T vz : list) {
//					if (vz instanceof String) {
//						jedis.lpush(key, (String) vz);
//					} else {
//						String objectJson = JSONObject.toJSONString(vz);
//						jedis.lpush(key, objectJson);
//					}
//				}
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
////	@SuppressWarnings("unchecked")
////	public static <T> List<T> getListEntity(String key, Class<T> entityClass) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis != null) {
////				List<String> valueJson = jedis.lrange(key, 0, -1);
////				JSONArray json = new JSONArray();
////				json.addAll(valueJson);
////				JSONArray jsonArray = JSONArray.fromObject(json.toString());
////				return (List<T>) JSONArray.toCollection(jsonArray, entityClass);
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
////	public static List<String> getListString(String key) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis != null) {
////				return jedis.lrange(key, 0, -1);
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//	// *************** 操作list****************end
//
//	// *************** 操作map****************start
//	public static <K, V> boolean setMap(String key, Map<String, V> map) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			if (jedis != null) {
//				Set<Map.Entry<String, V>> entry = map.entrySet();
//				for (Iterator<Map.Entry<String, V>> ite = entry.iterator(); ite.hasNext();) {
//					Map.Entry<String, V> kv = ite.next();
//					if (kv.getValue() instanceof String) {
//						jedis.hset(key, kv.getKey(), (String) kv.getValue());
//					} else if (kv.getValue() instanceof List) {
//						jedis.hset(key, kv.getKey(), JSONArray.fromObject(kv.getValue()).toString());
//					} else {
//						jedis.hset(key, kv.getKey(), JSONObject.fromObject(kv.getValue()).toString());
//					}
//				}
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	public static boolean setMapKey(String key, String mapKey, Object value) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			if (jedis != null) {
//				if (value instanceof String) {
//					jedis.hset(key, mapKey, String.valueOf(value));
//				} else if (value instanceof List) {
//					jedis.hset(key, mapKey, JSONArray.fromObject(value).toString());
//				} else {
//					jedis.hset(key, mapKey, JSONObject.fromObject(value).toString());
//				}
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	/**
//	 * seconds key和value 保存的有效时间（单位：秒）
//	 *
//	 * @return
//	 */
//	public static boolean setMapKeyExpire(String key, String mapKey, Object value, int seconds) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			if (jedis != null) {
//				if (value instanceof String) {
//					jedis.hset(key, mapKey, String.valueOf(value));
//				} else if (value instanceof List) {
//					jedis.hset(key, mapKey, JSONArray.fromObject(value).toString());
//				} else {
//					jedis.hset(key, mapKey, JSONObject.fromObject(value).toString());
//				}
//				jedis.expire(key, seconds);
//				return true;
//			} else {
//				return false;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
////	@SuppressWarnings("unchecked")
////	public static <K, V> Map<String, V> getMap(String key) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis != null) {
////				Map<String, V> map = (Map<String, V>) jedis.hgetAll(key);
////				return map;
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
////	@SuppressWarnings("unchecked")
////	public static <K, V> Map<String, V> getMapEntityClass(String key, Class<V> clazz) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis != null) {
////				Map<String, V> map = (Map<String, V>) jedis.hgetAll(key);
////				Set<Map.Entry<String, V>> entry = map.entrySet();
////				for (Iterator<Map.Entry<String, V>> ite = entry.iterator(); ite.hasNext();) {
////					Map.Entry<String, V> kv = ite.next();
////					map.put(kv.getKey(), (V) JSONObject.toBean(JSONObject.fromObject(kv.getValue()), clazz));
////				}
////				return map;
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
////	@SuppressWarnings("unchecked")
////	public static <K, V> Map<String, List<V>> getMapList(String key, Class<V> clazz) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis != null) {
////				Map<String, V> map = (Map<String, V>) jedis.hgetAll(key);
////				Set<Map.Entry<String, V>> entry = map.entrySet();
////				for (Iterator<Map.Entry<String, V>> ite = entry.iterator(); ite.hasNext();) {
////					Map.Entry<String, V> kv = ite.next();
////					JSONArray jsonArray = JSONArray.fromObject(kv.getValue());
////					map.put(kv.getKey(), (V) JSONArray.toCollection(jsonArray, clazz));
////				}
////				return (Map<String, List<V>>) map;
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
////	@SuppressWarnings("unchecked")
////	public static <T> List<T> getMapKeyListEntity(String key, String mapKey, Class<T> entityClass) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis != null) {
////				String valueJson = jedis.hget(key, mapKey);
////				JSONArray jsonArray = JSONArray.fromObject(valueJson);
////				return (List<T>) JSONArray.toCollection(jsonArray, entityClass);
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
////	@SuppressWarnings("unchecked")
////	public static <T> T getMapKeyEntity(String key, String mapKey, Class<T> entityClass) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis != null) {
////				String valueJson = jedis.hget(key, mapKey);
////				return (T) JSONObject.toBean(JSONObject.fromObject(valueJson), entityClass);
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
////	public static Object getMapKey(String key, String mapKey) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = getShardedJedis();
////			if (jedis != null) {
////				return jedis.hget(key, mapKey);
////			} else {
////				return null;
////			}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
////
////	public static boolean delMapKey(String key, String mapKey) {
////		Jedis jedis = null;
////		try {
////			jedis = getJedis();
////			jedis.hdel(key, mapKey);
////			return true;
////		} catch (Exception e) {
////			e.printStackTrace();
////			return false;
////		} finally {
////			returnResource(jedis);
////		}
////	}
////
////	public static boolean hexists(String key, String mapKey) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = shardedJedisPool.getResource();
////			return jedis.hexists(key, mapKey);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return false;
////		} finally {
////			shardedJedisPool.returnResource(jedis);
////		}
////	}
//	// *************** 操作map****************end
//
//	// ***************计数器应用INCR,DECR****************begin
//	// Redis的命令都是原子性的，你可以轻松地利用INCR，DECR命令来构建计数器系统
//
//	/**
//	 * incr(key)：名称为key的string增1操作
//	 */
//	public static boolean incr(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.incr(key);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	/**
//	 * incrby(key, integer)：名称为key的string增加integer
//	 */
//	public static boolean incrBy(String key, int value) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.incrBy(key, value);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//
//	public static boolean hashSet(final String key, final String field, final String value){
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.hset(key, field, value);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//
//	}
//
//
//	/**
//	 * decr(key)：名称为key的string减1操作
//	 */
//	public static boolean decr(String key) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.decr(key);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	/**
//	 * decrby(key, integer)：名称为key的string减少integer
//	 */
//	public static boolean decrBy(String key, int value) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.decrBy(key, value);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//	// ***************计数器应用INCR,DECR****************end
//
//	// ***************使用sorted set(zset)甚至可以构建有优先级的队列系统***************begin
//	/**
//	 * 向名称为key的zset中添加元素member，score用于排序。 如果该元素已经存在，则根据score更新该元素的顺序
//	 */
//	public static boolean zadd(String key, double score, String member) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.zadd(key, score, member);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	/**
//	 * 删除名称为key的zset中的元素member
//	 */
//	public static boolean zrem(String key, String... members) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.zrem(key, members);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
////	/**
////	 * 返回集合中score在给定区间的元素
////	 */
////	public static Set<String> zrangeByScore(String key, double min, double max) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = shardedJedisPool.getResource();
////			return jedis.zrangeByScore(key, min, max);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//	// ***************使用sorted set(zset)甚至可以构建有优先级的队列系统***************end
//
//	// ***************sorted set 处理***************************************begin
//	// zset 处理
//	public static boolean zaddObject(String key, double score, Object value) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			String objectJson = JSONObject.fromObject(value).toString();
//			jedis.zadd(key, score, objectJson);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	/**
//	 * score值递减(从大到小)次序排列。
//	 *
//	 * @param key
//	 * @param max
//	 *            score
//	 * @param min
//	 *            score
//	 * @param entityClass
//	 * @return
//	 */
////	@SuppressWarnings("unchecked")
////	public static <T> List<T> zrevrangeByScore(String key, double max, double min, Class<T> entityClass) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = shardedJedisPool.getResource();
////			Set<String> set = jedis.zrevrangeByScore(key, max, min);
////			List<T> list = new ArrayList<T>();
////			for (String str : set) {
////				list.add((T) JSONObject.toBean(JSONObject.fromObject(str), entityClass));
////			}
////			return list;
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
//	/**
//	 * score值递减(从大到小)次序排列。
//	 *
//	 * @param key
//	 * @param max
//	 *            score
//	 * @param min
//	 *            score
//	 * @param offset
//	 *            count (类似mysql的 LIMIT)
//	 * @param entityClass
//	 * @return
//	 */
////	@SuppressWarnings("unchecked")
////	public static <T> List<T> zrevrangeByScore(String key, double max, double min, int offset, int count,
////			Class<T> entityClass) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = shardedJedisPool.getResource();
////			Set<String> set = jedis.zrevrangeByScore(key, max, min, offset, count);
////			List<T> list = new ArrayList<T>();
////			for (String str : set) {
////				list.add((T) JSONObject.toBean(JSONObject.fromObject(str), entityClass));
////			}
////			return list;
////		} catch (Exception e) {
////			e.printStackTrace();
////			return null;
////		} finally {
////			returnResource(jedis);
////		}
////	}
////
////	// 得到总记录数
////	public static long zcard(String key) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = shardedJedisPool.getResource();
////			return jedis.zcard(key);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return 0;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
//	// 删除 元素
//	public static boolean zremObject(String key, Object value) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			String objectJson = JSONObject.fromObject(value).toString();
//			jedis.zrem(key, objectJson);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
////	// 统计zset集合中score某个范围内（1-5），元素的个数
////	public static long zcount(String key, double min, double max) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = shardedJedisPool.getResource();
////			return jedis.zcount(key, min, max);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return 0;
////		} finally {
////			returnResource(jedis);
////		}
////	}
////
////	// 查看zset集合中元素的score
////	public static double zscore(String key, Object value) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = shardedJedisPool.getResource();
////			String objectJson = JSONObject.fromObject(value).toString();
////			return jedis.zscore(key, objectJson);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return 0;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//	// **************sorted set******************************************end
//
//	// ***********************Redis Set集合操作**************************begin
//	/**
//	 * sadd:向名称为Key的set中添加元素,同一集合中不能出现相同的元素值。（用法：sadd set集合名称 元素值）
//	 *
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public static boolean sadd(String key, String value) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.sadd(key, value);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	/**
//	 * srem:删除名称为key的set中的元素。（用法：srem set集合名称 要删除的元素值）
//	 *
//	 * @param key
//	 * @param value
//	 * @return
//	 */
//	public static boolean srem(String key, String value) {
//		Jedis jedis = null;
//		try {
//			jedis = getJedis();
//			jedis.srem(key, value);
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		} finally {
//			returnResource(jedis);
//		}
//	}
//
//	/**
//	 * sdiff:返回所有给定key与第一个key的差集。（用法：sdiff set集合1 set集合2）
//	 *
//	 * @param key1
//	 * @param key2
//	 * @return
//	 */
//	public static Set<String> sdiff(String key1, String key2) {
//		Jedis jedis = null;
//		Set<String> diffList = null;
//		try {
//			jedis = getJedis();
//			diffList = jedis.sdiff(key1, key2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			returnResource(jedis);
//		}
//		return diffList;
//	}
//
//	/**
//	 * sismember:判断某个值是否是集合的元素。（用法：sismember 集合1 指定的元素值）
//	 *
//	 * @param key
//	 * @param value
//	 * @return
//	 */
////	public static boolean sismember(String key, String value) {
////		ShardedJedis jedis = null;
////		try {
////			jedis = shardedJedisPool.getResource();
////			return jedis.sismember(key, value);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return false;
////		} finally {
////			returnResource(jedis);
////		}
////	}
//
//	/**
//	 * smembers(key) ：返回名称为key的set的所有元素
//	 *
//	 * @param key
//	 * @return
//	 */
//	public static Set<String> smembers(String key) {
//		Jedis jedis = null;
//		Set<String> list = null;
//		try {
//			jedis = getJedis();
//			list = jedis.smembers(key);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			returnResource(jedis);
//		}
//		return list;
//	}
//
//	// ***********************Redis Set集合操作****************************end
//}