package com.sun.common.bean;

/**
 * javan内部类
 * 内部类能随意访问外部类
 * 外部类需要:外部类.内部类创建
 */
public class JavaInternalClass {


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
}
