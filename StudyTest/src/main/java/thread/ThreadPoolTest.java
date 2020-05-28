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
     * 1、corePoolSize：核心线程数
     *         * 核心线程会一直存活，及时没有任务需要执行
     *         * 当线程数小于核心线程数时，即使有线程空闲，线程池也会优先创建新线程处理
     *         * 设置allowCoreThreadTimeout=true（默认false）时，核心线程会超时关闭
     *
     * 2、maxPoolSize：最大线程数
     *         * 当线程数>=corePoolSize，且任务队列已满时。线程池会创建新线程来处理任务
     *         * 当线程数=maxPoolSize，且任务队列已满时，线程池会拒绝处理任务而抛出异常
     *
     * 3、 keepAliveTime：线程空闲时间
     *         * 当线程空闲时间达到keepAliveTime时，线程会退出，直到线程数量=corePoolSize
     *         * 如果allowCoreThreadTimeout=true，则会直到线程数量=0
     *
     * 4、allowCoreThreadTimeout：允许核心线程超时
     *
     * 5、rejectedExecutionHandler：任务拒绝处理器
     *         * 两种情况会拒绝处理任务：
     *             - 当线程数已经达到maxPoolSize，切队列已满，会拒绝新任务
     *             - 当线程池被调用shutdown()后，会等待线程池里的任务执行完毕，再shutdown。如果在调用shutdown()和线程池真正shutdown之间提交任务，会拒绝新任务
     *         * 线程池会调用rejectedExecutionHandler来处理这个任务。如果没有设置默认是AbortPolicy，会抛出异常
     *         * ThreadPoolExecutor类有几个内部实现类来处理这类情况：
     *             - AbortPolicy 丢弃任务，抛运行时异常
     *             - CallerRunsPolicy 执行任务
     *             - DiscardPolicy 忽视，什么都不会发生
     *             - DiscardOldestPolicy 从队列中踢出最先进入队列（最后一个执行）的任务
     *         * 实现RejectedExecutionHandler接口，可自定义处理器
     *
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 10, 30, TimeUnit.MINUTES,
                new LinkedBlockingDeque<>(2), new ThreadPoolExecutor.AbortPolicy());
        tpe.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("aaaa" + tpe.getPoolSize());
                while (true) {
                }
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
                System.out.println("bbbb" + tpe.getPoolSize());
                while (true) {
                }
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
                System.out.println("cccc" + tpe.getPoolSize());
                while (true) {
                }
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
                System.out.println("dddd" + tpe.getPoolSize());
                while (true) {
                }
            }
        });
//        tpe.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("eeee" + tpe.getPoolSize());
//                while (true) {
//                }
//            }
//        });
        tpe.shutdown();

    }

}
