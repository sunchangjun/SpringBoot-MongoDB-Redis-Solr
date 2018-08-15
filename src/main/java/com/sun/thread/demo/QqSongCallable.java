package com.sun.thread.demo;

import java.util.Random;
import java.util.concurrent.Callable;

public class QqSongCallable  implements Callable<Long>{
	
	private Long song_id;
	
	public QqSongCallable(Long song_id) {
		this.song_id = song_id;
	}

	@Override
	public Long call() throws Exception {
		try{
			//在这里获取歌曲,下载等
			System.out.println(Thread.currentThread().getId()+" started");
			Random random = new Random();
			int sec = random.nextInt(10);
			Thread.sleep(sec*1000);
			System.out.println(Thread.currentThread().getId()+" finished");
		}catch(Exception e){//捕捉所有的异常
			return null;//下载中出现错误时,返回null
		}
		return song_id;//正常返回song_id
	}
}
