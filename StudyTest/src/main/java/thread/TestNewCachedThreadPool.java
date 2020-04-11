/*
 * 文 件 名: TestNewCachedThreadPool.java
 * 创 建 人: zhangam
 * 创建时间:
 * 描    述:
 *
 * 修 改 人:
 * 修改时间:
 * 修改内容:
 */
package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述: 线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
 *
 * @author zhangam
 * @time
 * @version
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TestNewCachedThreadPool {

	public static void main(String[] args) {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int index = i;
//			try {
//				Thread.sleep(index * 1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
					System.out.println(index);
				}
			});
		}
	}

}
