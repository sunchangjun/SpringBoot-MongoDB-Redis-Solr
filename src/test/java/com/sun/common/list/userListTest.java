package com.sun.common.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class userListTest {

    /**
     * 测试新建对象下的list是null还是[]
     * 结果:null
     */
    //    @Test
    public  void  test(){
        User user =new  User();
        System.out.println(user.getList());
    }

    /**
     * 测试new 的新集合,取对象的id是否会异常
     * 结果[]
     */
//    @Test
    public void test2(){
        List<User> list =    new ArrayList<User>();
        List<Integer> fjydDissIdList = list.stream().map(User::getId).collect(Collectors.toList());
        System.out.println(fjydDissIdList);
    }

    /**
     * 测试对象下list不重新set,对象的list是否有数据
     * 结果:有
     */
    @Test
    public void test3(){
        User user =new  User();
        List<Integer> list=user.getList();
        if(null  == list){
            list = new ArrayList<>();
        }
        list.add(1);
        list.add(1);
        System.out.println(list);
    }
}
