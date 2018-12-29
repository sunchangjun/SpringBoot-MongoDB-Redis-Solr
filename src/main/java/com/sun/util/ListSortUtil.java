package com.sun.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.poi.ss.formula.functions.T;


import java.lang.reflect.Method;  
/**
 * @author hehaifeng
 * @date 2018-6-6 19:43:55
 * @version 1.0
 *作用：用于对list里面的元素按照该元素的某一个属性进行ASCII编码排序
 */
public class ListSortUtil<T>  {

    /** 
     * @param list 目标排序List 
     * @param sortField 排序字段(实体类属性名) 
     * @param sortMode 排序方式（asc or  desc） 
     */  
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public  void sort(List<T> list, final String sortField, final String sortMode) {  
      
        Collections.sort(list, new Comparator() {  
            public int compare(Object obj1, Object obj2) {   
                int retVal = 0;  
                try {  
                    //首字母转大写  
                    String newStr=sortField.substring(0, 1).toUpperCase()+sortField.replaceFirst("\\w","");   
                    String methodStr="get"+newStr;  
                      
                    Method method1 = ((T)obj1).getClass().getMethod(methodStr, null);  
                    Method method2 = ((T)obj2).getClass().getMethod(methodStr, null);  
                    if (sortMode != null && "desc".equals(sortMode)) {  
                        retVal = method2.invoke(((T) obj2), null).toString().compareTo(method1.invoke(((T) obj1), null).toString()); // 倒序  
                    } else {  
                        retVal = method1.invoke(((T) obj1), null).toString().compareTo(method2.invoke(((T) obj2), null).toString()); // 正序  
                    }  
                } catch (Exception e) {  
                    throw new RuntimeException();  
                }  
                return retVal;  
            }  
        });  
    }  

    
    /**
     * java8表达式
     */
    public void test() {
//    	 User user1 =new  User(9,"sun");
//    	 User user2 =new  User(5,"chang");
//    	 User user3 =new  User(7,"jun");
//    	 User user4 =new  User(1,"ni");
//    	 
//    	 List<User> list =new  ArrayList<User>(Arrays.asList(user1,user2,user3,user4));
//   	 //Lambda表达式
//   	 list.sort((s1, s2) -> s1.getId().compareTo(s2.getId()));
    	//Comparator<User> comparator = (t1, t2) -> t1.getId().compareTo(t2.getId());
//   	 
//   	 Collections.sort(list, Comparator.comparing(User::getId));
    }
}

