package thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * 功能描述:
 * CountDownLatch
 * 主进程被其他的多个线程阻塞，只有其他的线程全部都做了l.countDown()操作，主线程才会继续。 countDown()之后的，线程会继续执行线程内的任务。
 * 对简单的说是，一个线程等待其他线程执行l.countDown(); 阻塞的是单个进程。对执行countDown的线程几乎没有什么影响
 * CyclicBarrier
 * 可以理解为，多个线程之前同步等待，只有所有的线程都到达这个临界点，所有的线程才会继续向下执行。
 * 同时，CyclicBarrier 接受一个runnable线程参数，当所有的线程都释放锁的时候，会优先执行这个runnable线程，然后继续做自己的任务。
 * 简单的说，多个线程之间互相等待，然后一起同时继续执行
 * Semaphore
 * 这个比较特殊，他没有控制不同线程之间的关系。它控制的是，对某一段业务的并发数目, 简单的说，就是控制 并发执行一段代码的 线程数
 *
 * @author zhangam
 * @time 2020/5/28 14:04
 * @see
 **/
public class ConcurrentTest {


    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(2);
//        Thread thread1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(100l);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("Thread1 is finish");
//                countDownLatch.countDown();
//            }
//
//        });
//        Thread thread2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Thread2 is finish");
//                countDownLatch.countDown();
//            }
//
//        });
//        thread1.start();
//        thread2.start();
//        countDownLatch.await();
//        System.out.println("all parser finish");

//        Diskt diskt = new Diskt();
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("总：" + diskt.countTotal());
//            }
//        });
//        new Thread(new Jobt(cyclicBarrier, diskt, "c")).start();
//        new Thread(new Jobt(cyclicBarrier, diskt, "d")).start();
//        new Thread(new Jobt(cyclicBarrier, diskt, "e")).start();
//        new Thread(new Jobt(cyclicBarrier, diskt, "f")).start();

        DiskService diskService = new DiskService();
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 20; i++) {
            new Thread(new Jobc(semaphore, diskService, "c")).start();
        }
    }

}

class Jobt implements Runnable {
    public CyclicBarrier l;
    public Diskt d;
    public String type;

    public Jobt(CyclicBarrier ll, Diskt d, String type) {
        this.l = ll;
        this.d = d;
        this.type = type;
    }

    @Override
    public void run() {
        if ("c".equals(type)) {
            sleep(1);
            d.setC(1);
            System.out.println("success  c");
        } else if ("d".equals(type)) {
            sleep(2);
            d.setD(2);
            System.out.println("success  d");
        } else if ("e".equals(type)) {
            sleep(3);
            d.setE(3);
            System.out.println("success  e");
        } else if ("f".equals(type)) {
            sleep(4);
            d.setF(4);
            System.out.println("success  f");
        } else {
        }
        try {
            l.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(type + ":执行完成");
    }

    public void sleep(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

class Diskt {
    public int c;
    public int d;
    public int e;
    public int f;
    public int total;

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getE() {
        return e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int countTotal() {
        return c + d + e + f;
    }
}

class Jobc implements Runnable {
    public Semaphore l;
    public DiskService d;
    public String type;

    public Jobc(Semaphore ll, DiskService d, String type) {
        this.l = ll;
        this.d = d;
        this.type = type;
    }

    @Override
    public void run() {
        try {
            l.acquire(2);
            d.excute();
            sleep(2);
            l.release(2);
            System.out.println(type + ":执行完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleep(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (Exception e) {
        }
    }
}

class DiskService {
    public void excute() {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(d.format(new Date()));
    }
}
