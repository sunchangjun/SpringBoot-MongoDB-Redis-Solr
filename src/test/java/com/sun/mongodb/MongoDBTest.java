package com.sun.mongodb;

import com.alibaba.fastjson.JSONObject;
import com.sun.Mongodb.entity.MongoReco;
import com.sun.base.BaseJunitTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class MongoDBTest extends BaseJunitTest {

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Test
    public void test(){
        Query query = new Query(Criteria.where("version").is(133));
        MongoReco mongoReco=   mongoTemplate.findOne(query, MongoReco.class);
        System.out.println(JSONObject.toJSONString(mongoReco));

    }
}
