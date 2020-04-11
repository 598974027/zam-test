package kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class Productor {

    public static void main(String[] args) {
        Properties pros = new Properties();
        //Kafka服务端的主机名和端口号
        pros.put("bootstrap.servers", "192.168.2.110:9092");
        //等待所有副本节点的应答
        pros.put("acks", "all");
        //消息发送最大尝试次数
        pros.put("retries", 0);
        //一批消息处理大小
        pros.put("batch.size", 16384);
        //请求延时
        pros.put("linger.ms", 1);
        //发送给缓存区内存大小
        pros.put("buffer.memory", 33554432);
        //key序列化
        pros.put("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        //value序列化
        pros.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        //设置分区
        pros.put("partitioner.class", "kafka.MyPartitioner");
        //指定分区数
        pros.put("num.pratitions", "10");
        //创建kafka的生产者类
        KafkaProducer<Integer, byte[]> producer = new KafkaProducer<Integer, byte[]>(pros);
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            producer.send(new ProducerRecord<Integer, byte[]>("testKafka", random.nextInt(10000), "hello".getBytes()));
        }
        producer.close();
    }

}  
