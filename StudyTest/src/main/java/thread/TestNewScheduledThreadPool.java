/*
 * 文 件 名: TestNewScheduledThreadPool.java
 * 创 建 人: zhangam
 * 创建时间:
 * 描    述:
 *
 * 修 改 人:
 * 修改时间:
 * 修改内容:
 */
package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time
 * @version
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TestNewScheduledThreadPool {

	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors
				.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);

		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out
						.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}

}
