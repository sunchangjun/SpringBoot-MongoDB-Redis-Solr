/**
 * 
 */
package com.sun.common.ThreadPool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;

/**
 * @author sunchangjunn 2018年8月7日上午11:03:30
 * Runnable和Callable的区别是，
    (1)Callable规定的方法是call(),Runnable规定的方法是run().
	(2)Callable的任务执行后可返回值，而Runnable的任务是不能返回值得
	(3)call方法可以抛出异常，run方法不可以
	(4)运行Callable任务可以拿到一个Future对象，Future 表示异步计算的结果。它提供了检查计算是否完成的方法，以等待计算的完成，并获取计算的结果。
	计算完成后只能使用 get 方法来获取结果，如果线程没有执行完，Future.get()方法可能会阻塞当前线程的执行；如果线程出现异常，Future.get()会throws InterruptedException或者
	ExecutionException；如果线程已经取消，会跑出CancellationException。取消由cancel 方法来执行。isDone确定任务是正常完成还是被取消了。一旦计算完成，就不能再取消计算。
	如果为了可取消性而使用 Future 但又不提供可用的结果，则可以声明Future<?> 形式类型、并返回 null 作为底层任务的结果
 */
public class MyCallableThread implements Callable<Object> {

	private Queue que;

	public MyCallableThread(Queue que) {
		super();
		this.que = que;
	}

	@Override
	public Object call() throws Exception {
		boolean bool=false;
		Object obj=que.poll();
		if(obj instanceof String) {
			System.out.println("if");
			bool=true;
		}else {
			if(obj.equals(2)) {
				System.out.println("err");
				throw new  Exception();
			}
			System.out.println("else");
		}
			System.out.println("id:"+Thread.currentThread().getId());
			System.out.println("name:"+Thread.currentThread().getName());


		return que.size();
	}

	

}
