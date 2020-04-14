package zookeeper;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2020/4/13 10:39
 * @see
 **/
public class ZooKeeperTest implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("127.0.0.1:2181", 5000, new ZooKeeperTest());
        String path = "/zktest1";
        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(zk.getSessionId());
        //System.out.println(new String(zk.getData(path, false, null)));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ZooKeeper session established");
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        // TODO Auto-generated method stub
        System.out.println("Watchedevent is:" + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }

}
