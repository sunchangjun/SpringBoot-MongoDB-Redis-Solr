/**
 * 
 */
package com.sun.common.bean;

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
}
