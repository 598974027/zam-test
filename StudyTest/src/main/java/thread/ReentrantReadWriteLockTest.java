package thread;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 功能描述: 读读共享、谢谢互斥、读写互斥
 *
 * @author zhangam
 * @time 2019/4/22 13:49
 * @see
 **/
public class ReentrantReadWriteLockTest {

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    private ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();

    private ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {

        final ReentrantReadWriteLockTest reentrantReadWriteLockTest = new ReentrantReadWriteLockTest();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLockTest.read();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLockTest.read();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLockTest.write();
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantReadWriteLockTest.write();
            }
        }, "t4");
//        t1.start();
//        t2.start();

//        t1.start();
//        t3.start();

        t3.start();
        t4.start();
    }

}
