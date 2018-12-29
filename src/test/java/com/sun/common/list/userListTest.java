package com.sun.common.list;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.sun.util.ListSortUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class userListTest {

    /**
     * 测试新建对象下的list是null还是[]
     * 结果:null
     */

    public  void  test(){
        User user =new  User();
        System.out.println(user.getList());
    }

    /**
     * 测试new 的新集合,取对象的id是否会异常
     * 结果[]
     */

    public void test2(){
        List<User> list =    new ArrayList<User>();
        List<Integer> fjydDissIdList = list.stream().map(User::getId).collect(Collectors.toList());
        System.out.println(fjydDissIdList);
    }

    /**
     * 测试对象下list不重新set,对象的list是否有数据
     * 结果:有
     */

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
    /**
      * 测试list根据对象属性排序
     */

    public void testListSort() {
    	 User user1 =new  User(9,"sun");
    	 User user2 =new  User(5,"chang");
    	 User user3 =new  User(7,"jun");
    	 User user4 =new  User(1,"ni");
    	 
    	 List<User> list =new  ArrayList<User>(Arrays.asList(user1,user2,user3,user4));
    	 System.out.println(JSONObject.toJSONString(list));
    	 //Lambda表达式
//    	 list.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
    	 
//    	 Collections.sort(list, Comparator.comparing(User::getId));
    	 
    	 Comparator<User> comparator = (t1, t2) -> t1.getId().compareTo(t2.getId());
    	 list.sort(comparator.reversed());
    	 
    	 Collections.sort(list, new Comparator<User>(){  
    		  
             /*  
              * int compare(Student o1, Student o2) 返回一个基本类型的整型，  
              * 返回负数表示：o1 小于o2  //默认，  
              * 返回0 表示：o1和o2相等，  
              * 返回正数表示：o1大于o2。  
              */  
             public int compare(User o1, User o2) {  
               
                 //按照学生的年龄进行升序排列  
                 if(o1.getId() > o2.getId()){  
                     return 1;  
                 }  
                 if(o1.getId() == o2.getId()){  
                     return 0;  
                 }  
                 return -1;  
             }  
         });   
         System.out.println("排序后："+JSONObject.toJSONString(list));  


         
    	 System.out.println(JSONObject.toJSONString(list));
    }
    
    @Test
    public void  testsortUtil() {
//   	 User user1 =new  User(9,"sun");
//	 User user2 =new  User(5,"chang");
//	 User user3 =new  User(7,"jun");
//	 User user4 =new  User(1,"ni");
//	
//	 List<User> list =new  ArrayList<>(Arrays.asList(user1,user2,user3,user4));
//	 ListSortUtil<User> comparatorUtil = new ListSortUtil<User>();
//	 comparatorUtil.sort(list, "id", "desc");
//   	System.out.println(JSONObject.toJSONString(list));
   List<Integer> ilist=	new ArrayList<Integer>(Arrays.asList(1,2,3,4));
   	List<Integer> songIdlist= JSONObject.parseArray(JSONObject.toJSONString(ilist), Integer.class);
  	System.out.println(JSONObject.toJSONString(songIdlist));
    }
}
