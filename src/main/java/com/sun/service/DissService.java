package com.sun.service;

import com.sun.Mongodb.entity.MongoDiss;

import java.util.List;

public interface DissService {

    List<MongoDiss> find(Integer id,String name);
//    String find(Integer id);
}
