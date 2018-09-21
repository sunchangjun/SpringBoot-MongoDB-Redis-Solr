package com.sun.Mongodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

public  abstract class BaseRepository<T>  {
    @Autowired
    protected MongoTemplate mongoTemplate;
}
