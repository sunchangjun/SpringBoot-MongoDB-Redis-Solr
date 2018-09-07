package com.sun.common.bean;

import com.sun.Mongodb.entity.MongoDiss;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取javaBean中某属性的值
 */
public class JavaBeanFildTest {


    @Test
    public void test(){
        List<MongoDiss> list = new ArrayList<MongoDiss>();

        MongoDiss mongoDiss1=new MongoDiss();
        mongoDiss1.setDiss_id(1L);
        mongoDiss1.setDiss_name("心情");

        MongoDiss mongoDiss2=new MongoDiss();
        mongoDiss2.setDiss_id(2L);
        mongoDiss2.setDiss_name("心情不好");

        MongoDiss mongoDiss3=new MongoDiss();
        mongoDiss3.setDiss_id(3L);
        mongoDiss3.setDiss_name("心情好");

        list.add(mongoDiss1);list.add(mongoDiss2);list.add(mongoDiss3);

        List<String> tableNames=list.stream().map(MongoDiss::getDiss_name).collect(Collectors.toList());
        System.out.println(tableNames);

        List<Long> tableId=list.stream().map(MongoDiss::getDiss_id).collect(Collectors.toList());
        System.out.println(tableId);


    }

}
