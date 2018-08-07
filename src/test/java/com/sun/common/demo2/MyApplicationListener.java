/**
 * 
 */
package com.sun.common.demo2;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author sunchangjunn 2018年8月3日下午4:24:44
 */
@Component
public class MyApplicationListener {
	public void onApplicationEvent(MyApplicationEvent event) {
		System.out.println("接收到事件：" + event.getClass());
	}
}
