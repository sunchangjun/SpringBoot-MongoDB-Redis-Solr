package com.sun.MongoDB;

import com.alibaba.fastjson.JSONObject;

import com.sun.Mongodb.entity.MongoSong;
import com.sun.base.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Map.Entry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MongoDBTest extends BaseJunitTest {

    @Autowired
    protected MongoTemplate mongoTemplate;

    /**
     * 查询225在字段数组中radio_ids[225,330]
     */
//    @Test
    public void test(){
        Query query = new Query(Criteria.where("radio_ids").is(225));
        List<MongoSong> mongoReco=   mongoTemplate.find(query, MongoSong.class);
        System.out.println(JSONObject.toJSONString(mongoReco));

    }

    /**
     * 查询排序分页
     */
//     @Test
    public void test2(){
        //查询
        Query query = new Query(Criteria.where("radio_ids").is(225));
        //排序

         query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"_id")));
//         query.with(new Sort(new Sort.Order(Sort.Direction.ASC,"song_id")));
        //分页
        query.limit(1);
      MongoSong mongoReco=   mongoTemplate.findOne(query, MongoSong.class);
        System.out.println(JSONObject.toJSONString(mongoReco));
    }
//    @Test
    public void test3(){
        //查询
//        Criteria.where("radio_ids").is(225).and("singer_id").is(6365)

        Criteria ca =new Criteria();
//        ca.and("radio_ids").is(225);
//        ca.and("singer_id").is(6365);
        Query query = new Query(ca);

        query.limit(10);
        List<MongoSong> mongoReco=   mongoTemplate.find(query, MongoSong.class);
        System.out.println(JSONObject.toJSONString(mongoReco));
    }
    @Test
    public void test4(){
        Map<String,Object> map =new HashMap<String,Object>();
        map.put("radio_ids",225);
        map.put("singer_id",6365);
        Criteria ca =new Criteria();
        if (!map.isEmpty()) {
            for (Entry<String, Object> entry: map.entrySet()) {
             ca.and(entry.getKey()).is(entry.getValue());
            }
        }
        Query query = new Query(ca);

        List<MongoSong> mongoReco=  mongoTemplate.find(query, MongoSong.class);
        System.out.println(JSONObject.toJSONString(mongoReco));
    }

}
