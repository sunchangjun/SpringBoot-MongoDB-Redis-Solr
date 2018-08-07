/**
 * 
 */
package com.sun.common.demo2;

import org.springframework.context.ApplicationEvent;

/**
 * @author sunchangjunn
 * 2018年8月3日下午4:26:31
 */
public class MyApplicationEvent extends ApplicationEvent {
	  private static final long serialVersionUID = 1L;

	    public MyApplicationEvent(Object source) {
	        super(source);
	    }
}
