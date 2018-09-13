package com.sun.common.list;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * list非空判断
 *  list.removeAll(Collections.singleton(1));  去除集合中某个元素
 */
public class ListIsEmpTest {
//    @Test
    public void test(){
        List<Integer> list =new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(1);
       boolean bool= CollectionUtils.isEmpty(list);
       System.out.println(bool);
        System.out.println(list.isEmpty());
        //去除list中的null
       list.removeAll(Collections.singleton(1));
        System.out.println(list);




    }
    @Test
    public void test2(){
        List<Integer> list = null;
       System.out.println(CollectionUtils.isEmpty(list)); ;

    }
}
