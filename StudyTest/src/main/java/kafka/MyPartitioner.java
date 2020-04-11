package kafka;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

//设定依据key将当前这条消息发送到哪个partition的规则
public class MyPartitioner implements Partitioner {

    public MyPartitioner() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void configure(Map<String, ?> arg0) {
        // TODO Auto-generated method stub
    }


    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public int partition(String arg0, Object arg1, byte[] arg2, Object arg3, byte[] arg4, Cluster cluster) {
        return arg1.hashCode() % cluster.partitionsForTopic(arg0).size();
    }

}
