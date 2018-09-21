package com.sun.common.bean;

import org.junit.Test;

public class ClassPrent {
    private String  outName="outNames";
    public void  test1(){


    }
    public void print(){
        System.out.println("外部类打印print()");
    }
    class  InClass{
        private   String inName ="内部类名称";
         public void  test(){
             System.out.println("内部类打印"+outName);
             print();
         }

        public String getInName() {
            return inName;
        }

        public void setInName(String inName) {
            this.inName = inName;
        }
    }
    @Test
    public void  test(){
        System.out.println(outName);
        new ClassPrent.InClass();

        System.out.println();
        print();
    }

}
