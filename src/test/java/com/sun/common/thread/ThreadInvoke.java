package com.sun.common.thread;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池:阻塞调用多线程
 *模拟任务:打印10次i
 * 阻塞与非阻塞的区别在于,第一次任务列表没执行完,不会执行第二次列表
 */
public class ThreadInvoke {

    @Test
    public void  test(){
        /*1.创建线程池*/
        ExecutorService execu = Executors.newFixedThreadPool (10);

        List<ThreadInvoke.SongAluumThread> threadList   //创建内部类:外部类.内部类
                =new ArrayList<ThreadInvoke.SongAluumThread>();
        for (int page = 0; page < 5; page++) {

            //阻塞与非阻塞的区别是第一次下面的线程任务没有执行完,不会马上执行第二次
            for (int i = 0; i < 10; i++) {
                ThreadInvoke.SongAluumThread albumTask = new  ThreadInvoke.SongAluumThread(i);
                threadList.add(albumTask);
            }
            try {
                //
//                List<Future<boolean>> list =
                        execu.invokeAll(threadList);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("__________________________________分割线_______________________________________________");
        }




    }


    /*2.创建工作线程*/
    class SongAluumThread implements Callable<Integer> {
        private Integer alumId;
        public SongAluumThread(Integer alum_id) {
            this.alumId = alum_id;
        }



        @Override
        public Integer call() throws Exception {
            Thread.sleep(5000);
            System.out.println(alumId+"执行线程名称:"+Thread.currentThread().getName()+"__线程id:"+Thread.currentThread().getId());
            return null;
        }
    }



    /**/
    /**/
}
