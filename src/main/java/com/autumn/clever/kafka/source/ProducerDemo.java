package com.autumn.clever.kafka.source;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: zhangqiuying
 * @Date: 2020/7/16 10:49
 */
public class ProducerDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        // 指定连接 Kafka 集群所需的 broker 地址清单，多个用逗号分割。
        props.put("bootstrap.servers", "172.19.163.135:9092,172.19.163.135:9092");
        // 消息累加器 - RecordAccumulator，用于缓存消息的缓冲区大小，32MB
        props.put("buffer.memory", 33554432);
        // 用于指定 ProducerBatch 可以复用内存区域的大小，适当调节增加吞吐量，16KB。
        props.put("batch.size", 16384);
        // 用来指定分区中必须要有多少个副本收到这条消息，之后生产者才会认为这条消息是成功写入的。
        // 这是一个非常重要的参数，涉及到消息的可靠性和吞吐量之间的权衡。有 3 种类型的值，注意：ack 的值是字符串类型。
        // ack = 1: 默认即为 1。只要分区的 leader 副本成功写入消息，就会收到来自服务端的成功响应。
        //          leader 副本无法写入，leader 崩溃、重新选举新 leader，生产者会收到一个错误的响应，避免消息丢失，生产者可以选择重新发送消息。
        //          leader 副本写入成功，follower 写入失败，此时 leader 宕机，那么这个消息就会丢失。
        // ack = 0: 生产者消息发送消息之后，不需要等待任何服务器的响应，就认为发送成功。吞吐量大，可靠性低。
        // ack = -1 或 all: leader 需要等待 ISR(所有 follower) 成功写入消息，才认为消息发送成功。最强的可靠性。
        props.put("ack", "all");
        // 用来配置生产者发送消息失败的情况下的重试次数
        props.put("retries", 0);
        // 用来配置两次重试之间的时间间隔，避免无效的重试
        props.put("retry.backoff.ms", 100);
        // 指定消息的压缩方式，默认为 none，即不会被压缩。有 "gzip"、"snappy" 和 "lz4"。采取消息压缩的方式，可以减少网络传输量、降低网络 I/O，使用时间换空间的优化方式。
        props.put("compression.type", "none");
        // 用来指定在多久之后关闭闲置连接，默认 9 min
        props.put("connections.max.idle.ms", 540000);
        // 指定生产者发送 ProducerBatch 之前等待更多消息（ProducerRecord）加入 ProducerBatch 的时间。
        // 即生产者在 ProducerBatch 被填满或者超过等待时间 linger.ms 值时，将消息发送出去。默认值为 0。
        props.put("linger.ms", 0);
        // 限制每个连接，即客户端与 Node 之间的连接，最多的缓存请求数。
        props.put("max.in.flight.requests.per.connection", 5);
        // 用来设定 KafkaProducer 对应的客户端 id
        props.put("client.id", "");
        // 如果在这个时间内元数据没有更新，会被强制更新。默认值为 5 min。
        props.put("metadata.max.age.ms", 300000);
        // 拦截器
        props.put("interceptor.classes", "");
        // 分区器
        props.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
        // key 序列化器
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // value 序列化器
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建 KafkaProducer 对象，创建时会启动 Sender 线程
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            // 往 RecordAccumulator 中写消息
            Future<RecordMetadata> result = producer.send(new ProducerRecord<>(args[1], Integer.toString(i), Integer.toString(i)));
            RecordMetadata rm = result.get();
            System.out.println("topic: " + rm.topic() + ", partition: " + rm.partition() + ", offset: " + rm.offset());
        }
        producer.close();
    }
}
