/**
 * 
 */
package com.sun.common.bean;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

/**
 * @author sunchangjunn
 * 2018年8月9日下午4:43:08
 */
public class beanCopy {
	public void test() {
		/*
		 * 拷贝属性:第一个是目标对象,第二个是数据源
		 */
		BeanUtils.copyProperties(new Object(), new Object());
		
	}

	   static class User1{
		public int id;
			public String name;

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
		long id;
		String name;
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
	public void test2(){
		User1 user1=new User1();
		User2 user2=new User2();
		user1.setId(1);
		user1.setName("用户");
		BeanUtils.copyProperties(user2,user1);
		System.out.println(user1.name);
		System.out.println(JSONObject.toJSONString(user2));

	}
}
