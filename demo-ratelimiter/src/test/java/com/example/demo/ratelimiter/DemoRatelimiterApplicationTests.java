package com.example.demo.ratelimiter;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import com.example.demo.ratelimiter.service.RedisRateLimiter;
import com.example.demo.ratelimiter.util.LuaScript;
import com.google.common.util.concurrent.*;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.jredis.JredisPool;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoRatelimiterApplicationTests {
    @Autowired
    private JedisPool jedisPool;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testRedisLuaScript1() throws InterruptedException {


        Jedis jedis = jedisPool.getResource();
        List<String> keys = new ArrayList<String>();
        keys.add("Test001");

        jedis.eval(LuaScript.LuaSecondsScript, keys, new ArrayList<String>());

        while (true) {
            System.out.println(jedis.get("Test001"));
            System.out.println(jedis.ttl("Test001"));
            Thread.sleep(1000);
        }


    }

    @Test
    public void testRedisLuaScript2() throws InterruptedException {
        Jedis jedis = jedisPool.getResource();
        List<String> keys = new ArrayList<String>();
        keys.add("Test001");
        List<String> values = new ArrayList<String>();
        values.add("1000");
        values.add("value1");
        values.add("21");

        jedis.eval(LuaScript.LuaPeriodScript, keys, values);

        while (true) {
            System.out.println(jedis.zcount("Test001", -1, 10000));
            System.out.println(jedis.ttl("Test001"));
            Thread.sleep(1000);
        }
    }

    /**
     * RateLimiter类似于JDK的信号量Semphore，他用来限制对资源并发访问的线程数
     */
    @Test
    public void testRateLimiter() {
        ListeningExecutorService executorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());
        RateLimiter limiter = RateLimiter.create(1.0); // 每秒不超过4个任务被提交

        for (int i = 0; i < 10; i++) {
            limiter.acquire(); // 请求RateLimiter, 超过permits会被阻塞
            final ListenableFuture<Integer> listenableFuture = executorService
                    .submit(new Task("is " + i));
        }

    }

    private static final MetricRegistry metrics = new MetricRegistry();
    private static ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
    private static final Meter requests = metrics.meter(MetricRegistry.name(DemoRatelimiterApplicationTests.class, "request"));
    private Timer timer = metrics.timer(MetricRegistry.name(DemoRatelimiterApplicationTests.class, "response-timer"));

    @Test
    public void testRedisRateLimit() throws InterruptedException {
        reporter.start(3, TimeUnit.SECONDS);
        RedisRateLimiter limiter = new RedisRateLimiter(jedisPool, TimeUnit.MINUTES, 300);
        while (true) {
            boolean flag = false;
            Timer.Context context = timer.time();
            if (limiter.acquire("testMKey1")) {
                flag = true;
            }
            context.stop();
            if (flag) {
                requests.mark();
            }
            Thread.sleep(1);
        }
    }

    @Test
    public void testListenableFuture() {

        ListeningExecutorService executorService = MoreExecutors
                .listeningDecorator(Executors.newCachedThreadPool());
        final ListenableFuture<Integer> listenableFuture = executorService
                .submit(new Task("testListenableFuture"));
        //同步获取调用结果
        try {
            System.out.println(listenableFuture.get());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }


        //第一种方式

        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("get listenable future's result "
                            + listenableFuture.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }, executorService);


        //第二种方式
        Futures.addCallback(listenableFuture, new FutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                System.out
                        .println("get listenable future's result with callback "
                                + result);

            }


            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }

        });

    }

}


class Task implements Callable<Integer> {
    String str;

    public Task(String str) {
        this.str = str;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("call execute.." + str);
        TimeUnit.SECONDS.sleep(1);
        return 7;

    }


}