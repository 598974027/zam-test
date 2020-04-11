package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/12/23 17:24
 * @see
 **/
public class ThreadStatus {

    /**
     * java线程状态
     * <p>
     * NEW 未启动状态
     * <p>
     * RUNNABLE可运行状态 VisualVM:Running
     * <p>
     * BLOCKED 线程在等待monitor锁(synchronized关键字)
     * VisualVM:Monitor
     * <p>
     * WAITING 线程在无限等待唤醒
     * Object.wait()没有超时 VisualVM:Wait
     * Thread.join()没有超时
     * LockSupport.park()
     * <p>
     * TIMED_WAITING 线程在等待唤醒，但设置了时限
     * Thread.sleep() VisualVM:Sleeping
     * Object.wait()与超时 VisualVM:Wait
     * Thread.join()与超时
     * LockSupport.parkNanos()
     * LockSupport.parkUntil()
     * <p>
     * TERMINATED 终止状态
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ThreadGroup currentGroup = Thread.currentThread().getThreadGroup();
                    int noThreads = currentGroup.activeCount();
                    Thread[] lstThreads = new Thread[noThreads];
                    currentGroup.enumerate(lstThreads);
                    for (int i = 0; i < noThreads; i++) {
                        if (lstThreads[i] != null && lstThreads[i].getName() != null && lstThreads[i].getName().startsWith("Test线程")) {
                            System.out.println("线程名称：" + lstThreads[i].getName() + ";线程状态：" + lstThreads[i].getState());
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //RUNNABLE
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (int i = 0; i < 1000; i++) {
                    }
                }
            }
        }, "Test线程Running").start();

        //TIMED_WAITING (sleeping) 定时的，那个条件不到来，也将定时唤醒自己。
        final Object sleep = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1800 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Test线程Sleeping").start();

        //WAITING (on object monitor)
        //TIMED_WAITING (on object monitor)
        final Object wait = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (wait) {
                        try {
                            //长期等待，用notify()、notifyAll()唤醒
                            wait.wait();
                            //超时不等待
//                            wait.wait(5 * 1000);
                        } catch (InterruptedException e) {
                        }
                        try {
                            Thread.sleep(5 * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, "Test线程Wait").start();

        //WAITING (parking) 常见
        //TIMED_WAITING (parking)
        final Lock park = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    park.lock();
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    park.unlock();
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    park.lock();
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    park.unlock();
                    try {
                        Thread.sleep(5 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Test线程Park").start();

        //BLOCKED (on object monitor)
        final Object monitor = new Object();
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (monitor) {
                }
            }
        }, "Test线程Monitor").start();
        synchronized (monitor) {
            try {
                Thread.sleep(180 * 1000);
            } catch (InterruptedException e) {
            }
        }
    }

}
