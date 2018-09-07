package com.sun.common.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListForEach {
    @Test
    public void test(){
        List<Integer> integerList=new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6));

        List<Integer> intList=new ArrayList<Integer>(Arrays.asList(2,3,4,5,6));

        for (int i = 0; i < intList.size(); i++) {
            System.out.println(intList.get(i));
            System.out.println("i:"+ i);
            if (integerList.contains(intList.get(i))){
                intList.remove(i);
            }

        }
//        for (Integer integer: intList) {
//            if (integerList.contains(integer)){
//                intList.remove(integer);
//                break;
//            }
//
//        }

        System.out.println(intList);

    }
}
