package com.autumn.clever.kafka.serializer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @Author: zhangqiuying
 * @Date: 2020/6/2 17:43
 */
public class ConsumerStart {
    public static final String brokerList = "172.19.163.135:9092";
    public static final String topic = "topic-serializer";
    public static final String groupId = "group.topic.serializer";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CompanyDeserializer.class.getName());
        properties.put("bootstrap.servers", brokerList);
        // 设置消费组的名称
        properties.put("group.id", groupId);
        // 创建一个消费组客户端实例
        KafkaConsumer consumer = new KafkaConsumer<>(properties);
        // 订阅主题
        consumer.subscribe(Collections.singletonList(topic));

        int i = 0;
        while (i++ < 10) {
            ConsumerRecords<String, Company> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, Company> record : records) {
                if (record.value() != null) {
                    System.out.println(record.value().getName());
                    System.out.println(record.value().getAddress());
                }
                break;
            }
            break;
        }
    }
}
