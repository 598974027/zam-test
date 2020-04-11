package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/22 13:49
 * @see
 **/
public class LockTest {

    private static int i = 0;

    //可重入锁
    private Lock lock = new ReentrantLock();

    public void service() throws Exception {
        lock.lock();
        i++;
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "----" + i);
        lock.unlock();
    }

    public static void main(String[] args) {
        final LockTest lt = new LockTest();
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        lt.service();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        es.shutdown();
    }

}
