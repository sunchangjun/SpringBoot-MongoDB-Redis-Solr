/**
 * 
 */
package com.sun.common.redis;

import com.sun.mysql.entity.Album;
import org.junit.Test;

import com.sun.common.jedis.JedisUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		/*设置缓存并设置过期时间*/
		JedisUtils.setex("love",1000,"my love");




		/*Get*/
//		Map map=JedisUtils.getMap("classMap");
//		System.out.println(map.get("album"));


		/*统计*/
		System.out.println("count:"+JedisUtils.incrBy("count",2));
		System.out.println(JedisUtils.get("count"));




	}
	@Test
	public void redis(){
		Jedis jedis=JedisUtils.getResource();
		String key="REQUEST_"+":"+"用户的url";
		jedis.set(key, "随意");
		jedis.expire(key,1000);
		jedis.set("TABLE"+":"+"value","value");
		JedisUtils.returnResource(jedis);
	}
}
