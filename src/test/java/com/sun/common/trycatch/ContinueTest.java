package com.sun.common.trycatch;

import org.junit.Test;

public class ContinueTest {
    @Test
    public void test(){
        Boolean bool=false;
        for (int i = 0; i <5 ; i++) {
            System.out.println("I:"+i);
            System.out.println("跳过1");
            if (i == 3){
                continue;

            }
            for (int j = 0; j <=2 ; j++) {
                if (j == 2){
                    continue;
                }
                System.out.println("投诉");
            }

            System.out.println("跳过2");

        }

        System.out.println("2");
    }
}
