/**
 * 
 */
package com.sun.common.extendAndimplement;

import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年12月26日上午11:21:26
 */
public class ClassB extends ClassA{
	@Test
	public void test() {
		System.out.println("2");
		print();
		super.println(9);
		println(9);
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
//		super.print();
		System.out.println("3");
	}

	@Override
	public void println(Integer i) {
	    i=8;
		super.println(i);
	}
	
	
	
	

}
