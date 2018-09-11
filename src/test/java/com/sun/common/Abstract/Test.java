package com.sun.common.Abstract;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试子类父类关系
 * 结果:定义的父类类型,传入子类也行
 */
public class Test {
    @org.junit.Test
    public void test(){
        Admin admin =new Admin();

        List<Type> list =admin.getList();
        if(null == list){
            list = new ArrayList<>();
        }
        UserType userType =new UserType();
        list.add(userType);
        System.out.println(JSONObject.toJSONString(admin));
    }
}
