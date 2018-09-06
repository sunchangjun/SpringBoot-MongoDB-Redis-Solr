package com.sun.common.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueClass {


    public static final ArrayBlockingQueue queue =new ArrayBlockingQueue(10);

    public static final Queue queue1 =new ArrayBlockingQueue(10);

    public static Queue getQueue1() {
        return queue1;
    }

    public static ArrayBlockingQueue getQueue() {
        return queue;
    }


}
