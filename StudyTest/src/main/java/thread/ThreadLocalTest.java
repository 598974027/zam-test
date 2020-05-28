package thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述:
 * 通常情况下，我们创建的变量是可以被任何一个线程访问并修改的。如果想实现每一个线程都有自己的专属本地变量该如何解决呢？
 * JDK中提供的ThreadLocal类正是为了解决这样的问题。
 * ThreadLocal类主要解决的就是让每个线程绑定自己的值，可以将ThreadLocal类形象的比喻成存放数据的盒子，盒子中可以存储每个线程的私有数据。
 *
 * @author zhangam
 * @time 2019/4/22 13:49
 * @see
 **/
public class ThreadLocalTest {

    private static ThreadLocal zam = new ThreadLocal();

    public static void main(String[] args) {
        ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        zam.set(Thread.currentThread().getName() + "zam" + finalI);
                        while (true) {
                            Thread.sleep(1000);
                            System.out.println(Thread.currentThread().getName() + "：" + zam.get());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
