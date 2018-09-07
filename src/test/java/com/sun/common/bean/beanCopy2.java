package com.sun.common.bean;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

public class beanCopy2 {

       class User{
        private  int id;
        private  String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }

      class User2{
        private long id;
        private  String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }

    @Test
    public void test(){
        beanCopy2.User user=new beanCopy2.User();
        user.setName("用户");
        beanCopy2.User2 u=new beanCopy2.User2();

        User user1=new User();


        BeanUtils.copyProperties(user,user1);
//        BeanUtils.copyProperties(user1,user);
        System.out.println(JSONObject.toJSONString(user1));

    }

}
