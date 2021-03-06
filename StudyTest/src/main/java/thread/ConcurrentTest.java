package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2020/5/28 14:04
 * @see
 **/
public class ConcurrentTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread1 is finish");
                countDownLatch.countDown();
            }

        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread2 is finish");
                countDownLatch.countDown();
            }

        }).start();
        countDownLatch.await();
        System.out.println("all finish");

        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            @Override
            public void run() {
                System.out.println("龙珠已经集齐，开始召唤神龙啦");
            }
        });
        for (int i = 1; i <= 8; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("获得第" + finalI + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Semaphore semaphore = new Semaphore(5);
        for (int i = 1; i <= 8; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(finalI + "--号工人进来使用机器");
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(finalI + "号工人退出使用机器--");
                    semaphore.release();
                }
            }).start();
        }
    }
}
