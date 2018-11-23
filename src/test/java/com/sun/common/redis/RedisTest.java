/**
 * 
 */
package com.sun.common.redis;

import com.sun.common.jedis.RedisUtils;
import org.junit.Test;

import com.sun.common.jedis.JedisUtils;
import com.sun.common.jedis.JedisUtils2;

import redis.clients.jedis.Jedis;

/**
 * @author sunchangjunn
 * 2018年8月27日上午11:55:41
 */
public class RedisTest {
	
//	@Test
	public void  test() {
//		/*set*/
//		List<Album> list=new ArrayList<Album>();
//		Album album=new Album();
//		album.setAlbum_id(10086);
//		album.setAlbum_name("中国移动");
//		Album album2=new Album();
//		album2.setAlbum_id(10001);
//		album2.setAlbum_name("中国电信");
//		list.add(album);list.add(album2);
//		System.out.println("setList:"+JedisUtils.setList("list",list));;
//
//		Map map=new HashMap();
//		map.put("10086","中国移动");
//		map.put("10001","中国电信");
//		map.put("10010","中国联通");
//		System.out.println("setMap:"+	JedisUtils.setMap("map",map));
//
//
//		Map map2=new HashMap();
//		map2.put("album",album);
//		map2.put("album2",album2);
//		System.out.println("setClassMap:"+	JedisUtils.setMap("classMap",map2));
//		System.out.println(JedisUtils.set("key","value"));
		/*设置缓存并设置过期时间*/
//		JedisUtils.setex("love",100,"my love");
//		System.out.println(JedisUtils.zadd("zset",50,"SongId:10086"));
//		System.out.println(JedisUtils.zadd("zset",101,"SongId:10001"));
//		System.out.println(JedisUtils.zrevrank("zset","SongId:10086"));
//		System.out.println(JedisUtils.zrevrank("zset","SongId:10086"));
//		System.out.println(JedisUtils.sismember("zset","SongId:10086"));
		/*Get*/

//		System.out.println("GET:"+JedisUtils.get("123"));


//		/*统计*/
//		System.out.println("count:"+JedisUtils.incrBy("count",2));
//		System.out.println(JedisUtils.get("count"));




	}
//	@Test
	public void redis(){

		Jedis jedis=JedisUtils.getResource();
		String  key="zset";
		jedis.set(key, "");
		jedis.expire(key,1000);
		jedis.set("TABLE"+":"+"value","value");
		JedisUtils.returnResource(jedis);
	}
//	@Test
	public void testRedis2(){
		RedisUtils.set("redis2","redis22");
		JedisUtils.set("redis1","redis11");
	}
	
	@Test
	public void test3() {
		JedisUtils2  jedis2=new JedisUtils2("47.98.153.144", "scj19890606") ;
		jedis2.set("1111111111111", "22222222222222");
	}
}
