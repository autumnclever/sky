package com.autumn.clever.kafka.serializer;

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
    public static final String brokerList = "172.19.163.135:9092";
    public static final String topic = "topic-serializer";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CompanySerializer.class.getName());
        properties.put("bootstrap.servers", brokerList);
        // 配置生产者客户端参数并创建 KafkaProducer 实例
        KafkaProducer<String, Company> producer = new KafkaProducer<>(properties);

        Company company = Company.builder().name("maoyanyule").address("beijing").build();
        // 构建所需要发送的消息
        ProducerRecord<String, Company> record = new ProducerRecord<>(topic, company);
        // 发送消息
        try {
            producer.send(record).get();
        } catch (Exception e) {
            System.out.println(e);
        }
        // 关闭生产者客户端示例
        producer.close();
    }
}
