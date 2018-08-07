/**
 * 
 */
package com.sun.common.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sun.SpringBootProjectApplication;


/**
 * @author sunchangjunn 2018年8月3日下午4:23:28
 */
//@SpringBootApplication
public class Listener {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(Listener.class);
        ConfigurableApplicationContext context =application.run(args);
        //发布事件
        context.publishEvent(new MyApplicationEvent(11111));
        context.close();


	}
}
