package com.kingstar.kafka.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @author xiayc
 * @date 2020/9/5 13:18
 */
public class MyThreadTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        TaskRunnable taskRunnable = new TaskRunnable();

        executorService.execute(taskRunnable);
        executorService.submit(taskRunnable);
        System.out.println("--------------");

        executorService.submit(taskRunnable);
        executorService.shutdown();
    }

    @Test
    public void test1() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 5000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(), namedThreadFactory);
        for (int i = 0; i < 10; i++) {
            threadPoolExecutor.execute(() -> System.out.println(Thread.currentThread().getName()));
        }

    }


    @Test
    public void test2() {

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));
        singleThreadPool.shutdown();


    }

    class TimerTaskThread extends Thread {
        public TimerTaskThread() {
            super.setName("TimerTaskThread");
        }

    }
}
