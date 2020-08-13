package com.autumn.clever.kafka.demo;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @Author: zhangqiuying
 * @Date: 2020/6/2 17:21
 */
public class ProducerFastStart {
    public static final String brokerList = "172.19.163.135:9092";
    public static final String topic = "topic-little-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();

        /**
         * 指定生产者客户端连接 Kafka 集群所需的 broker 地址清单，具体的内容格式为 host1:port,host2:port，
         * 可以设置一个或多个地址，中间以逗号隔开，此参数的默认值为""。
         * 注意这里并非需要所有的 broker 地址，因为生产者会从给定的 broker 里查到其他 broker 的信息。
         */
        properties.put("bootstrap.servers", brokerList);

        /**
         * broker 端接收的消息必须以字节数组 (byte[]) 的形式存在。key.serializer 和
         * value.serializer 这两个参数分别用来指定 key 和 value 序列化操作的序列化器，这两个参数无默认值。
         * 注意这里必须填写序列化器的全限定名。
         */
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        /**
         * 每个参数在 ProducerConfig 中都有对应的名称，上方代码对应的写法参考如下：
         */
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        /**
         * 配置生产者客户端参数并创建 KafkaProducer 实例。
         * KafkaProducer 是线程安全的，可以在多个线程中共享单个 KafkaProducer 实例，
         * 也可以将 KafkaProducer 实例进行池化来供其他线程调用。
         */
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        /**
         * 构建所需要发送的消息 -> 构建 ProducerRecord 对象。
         * 其中 topic 属性和 value 属性，是必填项，其他是选填项。
         */
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "秋颖漂亮漂亮真漂亮");

        // 发送消息
        try {
            producer.send(record);
        } catch (Exception e) {
            System.out.println(e);
        }
        // 关闭生产者客户端示例
        producer.close();
    }
}
