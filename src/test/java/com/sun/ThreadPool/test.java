/**
 * 
 */
package com.sun.ThreadPool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.sun.common.ThreadPool.MyCallableThread;
import com.sun.common.ThreadPool.MyRunnableThread;

/**
 * @author sunchangjunn
 * 2018年8月7日上午10:42:33
 */
public class test {
	
	@Test
	public void test1() throws InterruptedException, ExecutionException {
		Queue<Object> que=new ConcurrentLinkedQueue<>();
		que.offer("CCC");
		que.offer(1);
		que.offer(2);
		que.offer("aC");
		que.offer("CbC");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (Object object : que) {
			try {
//				Future<Object> r2=executor.submit( new  MyCallableThread(que));
				Future<Object> r2=executor.submit(new Callable<Object>() {

					@Override
					public Object call() throws Exception {
						System.out.println("id:"+Thread.currentThread().getId());
						System.out.println("name:"+Thread.currentThread().getName());
//						testException.test(que);
//						Object obj=que.poll();
//						if(obj.equals(2)) {
//							throw new Exception("cuowuma:"+obj.toString());
//						}
					
						return que.size();
					}
				});
				 System.out.println(JSONObject.toJSONString("return:"+r2.get()));
			} catch (Exception e) {
				System.out.println("cuowu:"+e.getMessage());
			}
			
			
		}
		
//		
//		executor.submit(new MyRunnableThread(que));
		 
		
	}
	

}
