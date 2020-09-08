package com.kingstar.kafka.thread;

/**
 * @author xiayc
 * @date 2020/9/5 13:24
 */
public class TaskRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("执行了%s遍");
    }
}
