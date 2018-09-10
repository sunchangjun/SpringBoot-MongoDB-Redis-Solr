package com.sun.controller.mongodbController;

import com.alibaba.fastjson.JSONObject;
import com.sun.Mongodb.entity.MongoReco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reco")
public class MongoRecoController {


    @Autowired
    protected MongoTemplate mongoTemplate;

    @RequestMapping("/findReco")
    public void test(){
        Query query = new Query(Criteria.where("version").is(133));
        MongoReco mongoReco=   mongoTemplate.findOne(query, MongoReco.class);
        System.out.println(JSONObject.toJSONString(mongoReco));

    }

    @RequestMapping("/max")
    public void test1(){
        //查询条件
        Query query = new Query(Criteria.where("version").is(133));
        //排序
        query.with(new Sort(new Sort.Order(Sort.Direction.DESC,"id")));
        //分页
        List<MongoReco> mongoReco=   mongoTemplate.find(query.limit(1), MongoReco.class);

        System.out.println(JSONObject.toJSONString(mongoReco.get(0)));

    }
}
