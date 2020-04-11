package kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer {

    public static void main(String[] args) {
        Properties props = new Properties();
        //Kafka服务端的主机名和端口号
        props.put("bootstrap.servers", "192.168.2.110:9092");
        //制定consumer group
        props.put("group.id", "flume");
        //是否自动确认offset
        props.put("enable.auto.commit", "true");
        //自动确认offset的时间间隔
        props.put("auto.commit.interval.ms", "1000");
        //key的序列化类
        props.put("key.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
        //value的序列化类
        props.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        //定义consumer
        KafkaConsumer<Integer, byte[]> consumer = new KafkaConsumer<Integer, byte[]>(props);
        //消费订阅的topic，可同时订阅多个
        consumer.subscribe(Arrays.asList("testKafka"));
        while (true) {
            //读取数据，超时时间为100ms
            ConsumerRecords<Integer, byte[]> records = consumer.poll(100);
            for (ConsumerRecord<Integer, byte[]> record : records) {
                System.out.printf("parttitioner = %d, offset = %d, key = %s, value = %s%n", record.partition(), record.offset(), record.key(), new String(record.value()));
            }
        }
    }

}
