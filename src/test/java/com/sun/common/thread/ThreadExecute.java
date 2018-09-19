package com.sun.common.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步非阻塞:
 * 
 * 提交任务后，任务将被异步执行
 */
public class ThreadExecute {

    @Test
    public void test() {
        /*1.创建线程池*/
        List<Integer> diList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        ExecutorService es = Executors.newFixedThreadPool(diList.size());

        /*3.线程池调用线程执行*/
        for (int i = 0; i < 2; i++) {
            for (Integer integer : diList) {
                try {
                    es.execute(new DealSongThead(integer));
                } catch (Exception e) {
                    e.printStackTrace();
//                    es.shutdownNow();
                }

            }
            System.out.println("_____________________________________");


        }
        try {
            Thread.sleep(100000);

        } catch (Exception e) {

        }

    }

    /*2.创建调用线程*/
    public class DealSongThead implements Runnable {
        private Integer startId;

        DealSongThead(Integer startId) {
            this.startId = startId;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(startId+"_线程名称:"+Thread.currentThread().getName()+"_线程id:"+Thread.currentThread().getId());
            } catch (Exception e) {

            }


        }
    }
}