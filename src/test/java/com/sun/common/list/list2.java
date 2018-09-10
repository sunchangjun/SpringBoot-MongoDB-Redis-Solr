package com.sun.common.list;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class list2 {
    @Test
    public void test(){
        List<Integer> list=new ArrayList<>();
        test2(list);
        System.out.println(JSONObject.toJSONString(list));

    }

    public void test2(List<Integer> list){
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
    }
}
