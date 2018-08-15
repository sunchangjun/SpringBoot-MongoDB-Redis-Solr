/**
 * 
 */
package com.sun.common.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * @author sunchangjunn
 * 2018年8月10日下午5:20:50
 */
public class ThreadT {
	 class QqSongCallable  implements Callable<Integer>{
		 
		 private int song_id;
		 
		 public QqSongCallable(int i) {
				this.song_id = i;
			}
		
		@Override
		public Integer call() throws Exception {
			try{
				//在这里获取歌曲,下载等
				System.out.println(song_id);
				System.out.println(Thread.currentThread().getId()+" started");
				Random random = new Random();
				int sec = random.nextInt(10);
				Thread.sleep(sec*1000);
				System.out.println(Thread.currentThread().getId()+" finished");
			}catch(Exception e){//捕捉所有的异常
				return null;//下载中出现错误时,返回null
			}
			return song_id;
		}
		 	 
	 }
	
	private static ExecutorService executor = Executors.newFixedThreadPool(5);
	@Test
	public  void  test() {
		
		for (int count = 0; count <2; count++) {
			System.out.println("第"+count);
			List<QqSongCallable> list=new  ArrayList<QqSongCallable>();
			for (int i = 0; i <10; i++) {				
				QqSongCallable task=new QqSongCallable(i);
				list.add(task);
			}			
			try {
				
				List<Future<Integer>> rets=executor.invokeAll(list);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		
	}


}
