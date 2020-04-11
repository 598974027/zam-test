/*
 * 文 件 名: TestNewFixedThreadPool.java
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
 * 功能描述: 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 *
 * @author zhangam
 * @time
 * @version
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TestNewFixedThreadPool {

	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
//					try {
					System.out.println(index);
//						Thread.sleep(5000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
				}
			});
		}
	}

}
