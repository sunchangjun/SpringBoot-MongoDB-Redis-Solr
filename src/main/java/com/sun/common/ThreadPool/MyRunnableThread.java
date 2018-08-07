/**
 * 
 */
package com.sun.common.ThreadPool;

import java.util.Queue;

/**
 * @author sunchangjunn
 * 2018年8月7日上午11:57:15
 */
public class MyRunnableThread implements Runnable{

	private Queue que;
	
	public MyRunnableThread(Queue que) {
		super();
		this.que = que;
	}

	@Override
	public void run() {
		System.out.println(que.poll().toString());
		
	}
	

}
