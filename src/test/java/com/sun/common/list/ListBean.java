package com.sun.common.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListBean {

    @Test
    public void test(){
        List<Integer> list =new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));
        CopyOnWriteArrayList<Integer> cwList=new CopyOnWriteArrayList<>(list);
//        cwList.addAll(list);
//        for (Integer integer:cwList) {
//            cwList.remove(integer);
//
//        }

        System.out.println(cwList);
    }

}
