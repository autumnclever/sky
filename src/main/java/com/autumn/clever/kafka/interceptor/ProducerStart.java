package com.autumn.clever.kafka.interceptor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @Author: zhangqiuying
 * @Date: 2020/6/2 17:21
 */
public class ProducerStart {
    public static final String brokerList = "192.168.1.11:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerInterceptorPrefix.class.getName());
        properties.put("bootstrap.servers", brokerList);
        // 配置生产者客户端参数并创建 KafkaProducer 实例
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        // 发送消息
        try {
            for (int i = 0; i < 10; i++) {
                // 构建所需要发送的消息
                ProducerRecord<String, String> record = new ProducerRecord<>(topic, "秋颖真漂亮");
                producer.send(record);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        // 关闭生产者客户端示例
        producer.close();
    }
}
