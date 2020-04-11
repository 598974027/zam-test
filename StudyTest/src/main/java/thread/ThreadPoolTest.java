package thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/5/6 17:40
 * @see
 **/
public class ThreadPoolTest {


    /**
     * 1 corePoolSize int 核心线程池大小
     * 2 maximumPoolSize int 最大线程池大小
     * 3 keepAliveTime long 线程最大空闲时间
     * 4 unit TimeUnit 时间单位
     * 5 workQueue BlockingQueue<Runnable> 线程等待队列
     * 6 threadFactory ThreadFactory 线程创建工厂
     * 7 handler RejectedExecutionHandler 拒绝策略
     * <p>
     * 线程安全 线程非安全
     * StringBuffer StringBuilder
     * Vector、SynchronizedList、CopyOnWriteArrayList ArrayList、LinkedList
     * HashTable、SynchronizedMap、ConcurrentHashMap HashMap、TreeMap
     * SynchronizedSet HashSet、TreeSet
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(10,
                30,
                30,
                TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(),
                new ThreadPoolExecutor.AbortPolicy());
        tpe.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aaaa");
            }
        });
        tpe.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("bbbbb");
            }
        });
        System.out.println(tpe.getPoolSize());
        tpe.shutdown();

    }

}
