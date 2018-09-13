package com.sun.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.sun.Mongodb.entity.MongoDiss;
import com.sun.service.DissService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DissServiceImpl  implements DissService {

    @Autowired
    protected MongoTemplate mongoTemplate;



    @Cacheable(value="diss")
    public  List<MongoDiss>  find(Integer id,String name){
        Query query = new Query(Criteria.where("song_list.$id").is(1280297));
        List<MongoDiss> dissList=mongoTemplate.find(query, MongoDiss.class);
        System.out.println(JSONObject.toJSONString(dissList));
        return dissList;
    }

}
