/**
 * 
 */
package com.sun.service.Impl;

import java.util.Date;

import com.sun.service.WebServicedemo;

/**
 * @author sunchangjunn
 * 2018年11月26日上午10:18:33
 */
public class WebServicedemoImpl implements WebServicedemo {
	   @Override
	    public String sayHello(String user) {
	        return user+":hello"+"("+new Date()+")";
	    }
}
