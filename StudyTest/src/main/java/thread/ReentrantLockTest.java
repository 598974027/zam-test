package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述: ReentrantLock
 *
 * @author zhangam
 * @time 2019/4/22 13:49
 * @see
 **/
public class ReentrantLockTest {

    private static int i = 0;

    private Lock lock = new ReentrantLock();

    public void service() throws Exception {
        lock.lock();
        i++;
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "----" + i);
        lock.unlock();
    }

    public static void main(String[] args) {
        final ReentrantLockTest lt = new ReentrantLockTest();
        ExecutorService es = Executors.newFixedThreadPool(1);
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
