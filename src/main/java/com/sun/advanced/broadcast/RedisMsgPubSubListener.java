/**
 * 
 */
package com.sun.advanced.broadcast;


import redis.clients.jedis.JedisPubSub;
 
/**
 * Created by denglinjie on 2016/6/29.
 */
public class RedisMsgPubSubListener extends JedisPubSub {
    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }
 
    @Override
    public void unsubscribe(String... channels) {
        super.unsubscribe(channels);
    }
 
    @Override
    public void subscribe(String... channels) {
        super.subscribe(channels);
    }
 
    @Override
    public void psubscribe(String... patterns) {
        super.psubscribe(patterns);
    }
 
    @Override
    public void punsubscribe() {
        super.punsubscribe();
    }
 
    @Override
    public void punsubscribe(String... patterns) {
        super.punsubscribe(patterns);
    }
 
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("channel:" + channel + " receives message :" + message);
        try {
			test(message);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println();
        //注意：subscribe是一个阻塞的方法，在取消订阅该频道前，会一直阻塞在这，无法执行后续的代码
//        this.unsubscribe();
    }
    
    public void  test(String str) throws InterruptedException {
    	
    	Thread.sleep(5000);
    	System.out.println("睡眠5秒执行代码"+str);
    }
 
    @Override
    public void onPMessage(String pattern, String channel, String message) {
 
    }
 
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been subscribed:" + subscribedChannels);
    }
 
    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
 
    }
 
    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
 
    }
 
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println("channel:" + channel + "is been unsubscribed:" + subscribedChannels);
    }


}
