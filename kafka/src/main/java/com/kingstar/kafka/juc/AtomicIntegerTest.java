package com.kingstar.kafka.juc;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiayc
 * @date 2020/9/29 13:24
 */
public class AtomicIntegerTest {
    @Test
    public void test1() throws InterruptedException {

        Map<String, AtomicInteger> map = new ConcurrentHashMap<>(16);
        map.put("first", new AtomicInteger(0));

        AtomicInteger atomicInteger = new AtomicInteger(0);

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(100, 100,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        for (int j = 0; j < 100; j++) {
            singleThreadPool.execute(() -> {
                for (int i = 0; i < 100; i++) {
                    map.computeIfPresent("first", (k, v) -> new AtomicInteger(v.addAndGet(2)));
                    System.out.println(Thread.currentThread().getName() + "-------------" + map.get("first"));
//                int i1 = atomicInteger.incrementAndGet();
//                System.out.println(Thread.currentThread().getName() + "-------------" + i1);
                }
            });
        }


        singleThreadPool.shutdown();

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("first"));
    }

}

