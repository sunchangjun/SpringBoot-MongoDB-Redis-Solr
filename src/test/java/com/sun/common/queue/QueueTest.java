package com.sun.common.queue;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class QueueTest {
    @Test
    public void test(){
        //从腾讯爬的数据
        QueueClass.getQueue().offer("id和所属");
        //然后我从队列里取出
        QueueClass.getQueue().poll();
        //检查数据,需要增加的给涛子
        //server
        QueueClass.getQueue().offer("id");//
        //



        QueueClass queueClass =new  QueueClass();
        queueClass.getQueue().add(10);
        System.out.println(JSONObject.toJSONString(queueClass.getQueue()));
        QueueClass queueClass1 =new  QueueClass();
        queueClass1.getQueue().add(20);
        for (int i = 0; i < 10; i++) {
            try {
                QueueClass.getQueue().put(i);
                System.out.println("检查:"+  QueueClass.getQueue().peek());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println( "移除:"+QueueClass.getQueue().poll());
            System.out.println("剩余:"+ QueueClass.getQueue());
            System.out.println("检查:"+  QueueClass.getQueue().peek());
        }


        System.out.println(JSONObject.toJSONString(QueueClass.getQueue()));;
    }
}
