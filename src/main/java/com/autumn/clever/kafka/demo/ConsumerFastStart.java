package com.autumn.clever.kafka.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @Author: zhangqiuying
 * @Date: 2020/6/2 17:43
 */
public class ConsumerFastStart {
    public static final String brokerList = "172.19.163.135:9092";
    public static final String topic = "topic-little-demo";
    public static final String groupId = "group.little.demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers", brokerList);
        // 设置消费组的名称
        properties.put("group.id", groupId);
        // 创建一个消费组客户端实例
        KafkaConsumer consumer = new KafkaConsumer<>(properties);
        // 订阅主题
        consumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record.value());
                break;
            }
            break;
        }
    }
}
