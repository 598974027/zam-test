package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/22 10:00
 * @see
 **/
public class SynchronizedTest {

    private static int i = 0;

    /**
     * 普通实例方法
     */
    public int getI() throws Exception {
        i++;
        Thread.sleep(100);
        return i;
    }

    /**
     * 修饰实例方法，作用于当前实例加锁，进入同步代码前要获得当前实例的锁
     */
    public synchronized int getI1() throws Exception {
        i++;
        Thread.sleep(100);
        return i;
    }

    /**
     * 修饰静态方法，作用于当前类对象加锁，进入同步代码前要获得当前类对象的锁
     */
    public synchronized static int getI2() throws Exception {
        i++;
        Thread.sleep(100);
        return i;
    }

    /**
     * 修饰代码块，指定加锁对象，对给定对象加锁，进入同步代码库前要获得给定对象的锁。
     */
    public int getI3(Object o) throws Exception {
        synchronized (o) {
            i++;
            Thread.sleep(100);
            return i;
        }
    }

    public static void main(String[] args) {
        //实例化本类实例，调用普通方法是用到。
        final SynchronizedTest syt = new SynchronizedTest();
        //创建了一个最大容量为5的线程池
        ExecutorService es = Executors.newFixedThreadPool(5);
        //这里的lock对象，是用作锁代码块是用的
        //做为锁对象，别用字符串常量做锁；锁对象引用改变，会引发change锁事件，即立即释放锁
        final Object lock = new Object();

        for (int i = 1; i <= 10; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        System.out.println(Thread.currentThread().getName() + "--" + syt.getI());//调用非安全的方法
//                        System.out.println(Thread.currentThread().getName() + "--" + syt.getI1());//调用加锁了的对象方法
                        System.out.println(Thread.currentThread().getName() + "--" + SynchronizedTest.getI2());//调用加锁的静态方法
//                        System.out.println(Thread.currentThread().getName() + "--" + syt.getI3(lock));//调用调用锁代码块的方法
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        es.shutdown();
    }

}
