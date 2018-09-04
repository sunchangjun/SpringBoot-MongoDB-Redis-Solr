package com.sun.controller;

import com.sun.common.jedis.JedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/redis")
public class RedisController {
    /*设置缓存*/
    @RequestMapping("/set")
    public String  set(String key,String value){
       String result= JedisUtils.set(key,value);
        return result;
    }
    /*获取缓存*/
    @RequestMapping("/get")
    public String  get(String key){
       String result= JedisUtils.get(key);
       return  result;
    }

    /*向链表尾部插入元素*/
    @RequestMapping("/rpush")
    public void rpush(String key,String value){
        JedisUtils.rpush(key,value);
    }
    /*从链表头部取出并删除元素*/
    @RequestMapping("/lpop")
    public String lpop(String key){
        return JedisUtils.lpop(key);
    }


}
