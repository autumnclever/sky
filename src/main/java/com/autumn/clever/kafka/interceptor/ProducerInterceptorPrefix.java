package com.autumn.clever.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 拦截器早在 Kafka 0.10.0.0 中就已经引入的功能，Kafka 一共有两种拦截器：
 * 生产者拦截器和消费者拦截器。本测试主要实现了生产者拦截器相关内容。
 * 生产者拦截器既可以用来在消费发送前做一些准备工作，比如按照某个规则个过滤不符合要求的消息、
 * 修改消息的内容等，也可以用来在发送回调逻辑前做一些定制化的要求，比如统计类工作。
 * <p>
 * PS：这三个方法中抛出的一场都会被捕捉并记录到日志中。
 *
 * @Author: zhangqiuying
 * @Date: 2020/7/14 22:23
 */
public class ProducerInterceptorPrefix implements ProducerInterceptor<String, String> {
    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;

    /**
     * 消息序列化和计算分区之前调用生产者拦截器的 onSend 方法，来对消息进行相应的定制化操作。
     * 一般来说不最好不要修改消息的 ProducerRecord 的 topic、key 和 partition 等消息，如果要修改，则需确保对其有准确的判断。
     *
     * @param record
     * @return
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String modifiedValue = "prefix1-" + record.value();

        return new ProducerRecord<>(record.topic(), record.partition(),
                record.timestamp(), record.key(), modifiedValue, record.headers());
    }

    /**
     * KafkaProducer 会在消息被应答（Acknowledgement）之前或消息发送失败时，
     * 调用生产者烂机器的 onAcknowledgement 方法，优先于用户设定的 Callback 之前执行。
     * 这个方法运行在 Producer 的 I/O 线程中，所以这个方法中实现的代码逻辑越简单越好，否则会影响消息的发送速度。
     *
     * @param recordMetadata
     * @param e
     */
    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
            sendSuccess++;
        } else {
            sendFailure++;
        }
    }

    /**
     * 主要用于在关闭拦截器时，执行一些资源的清理工作。
     */
    @Override
    public void close() {
        double successRatio = (double) sendSuccess / (sendFailure + sendSuccess);
        System.out.println("发送成功率=" + String.format("%f", successRatio * 100) + "%");
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
