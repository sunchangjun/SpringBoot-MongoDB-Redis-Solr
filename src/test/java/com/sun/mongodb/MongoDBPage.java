package com.sun.mongodb;

import com.alibaba.fastjson.JSONObject;
import com.sun.Mongodb.entity.MongoDiss;
import com.sun.base.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class MongoDBPage extends BaseJunitTest {
    @Autowired
    protected MongoTemplate mongoTemplate;

    @Test
    public  void test(){
        Query query = new Query();
        query.limit(100).skip(60000);

        List<MongoDiss> mongoReco=   mongoTemplate.find(query, MongoDiss.class);

        System.out.println(JSONObject.toJSONString(mongoReco));
//        System.out.println(JSONObject.toJSONString(idList.size()));


    }
}
