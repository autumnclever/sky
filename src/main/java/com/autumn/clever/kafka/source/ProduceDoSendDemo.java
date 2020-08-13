package com.autumn.clever.kafka.source;

/**
 * @Author: zhangqiuying
 * @Date: 2020/7/16 12:48
 */
public class ProduceDoSendDemo {

//    private Future<RecordMetadata> doSend(ProducerRecord<K, V> record, Callback callback) {
//        ...
//
//        // 1.获取集群元数据信息
//        long nowMs = time.milliseconds();
//        KafkaProducer.ClusterAndWaitTime clusterAndWaitTime;
//        clusterAndWaitTime = waitOnMetadata(record.topic(), record.partition(), nowMs, maxBlockTimeMs);
//
//        // 2.key 序列化
//        byte[] serializedKey;
//        serializedKey = keySerializer.serialize(record.topic(), record.headers(), record.key());
//
//        // 3.value 序列化
//        byte[] serializedValue;
//        serializedValue = valueSerializer.serialize(record.topic(), record.headers(), record.value());
//
//        // 4.分区
//        int partition = partition(record, serializedKey, serializedValue, cluster);
//
//        // 5.创建 TopicPartition 对象，记录消息的 topic 和 partition 信息
//        TopicPartition tp = new TopicPartition(record.topic(), partition);
//
//        // 6.写入消息到缓冲区
//        RecordAccumulator.RecordAppendResult result = accumulator.append(tp, timestamp, serializedKey,
//                serializedValue, headers, interceptCallback, remainingWaitMs, true, nowMs);
//
//        // 7.由于需要新创建Batch append没有成功
//        if (result.abortForNewBatch) {
//            int prevPartition = partition;
//            // 触发新的分区
//            partitioner.onNewBatch(record.topic(), cluster, prevPartition);
//            // 再次获取新的分区值
//            partition = partition(record, serializedKey, serializedValue, cluster);
//            // 封装TopicPartition
//            tp = new TopicPartition(record.topic(), partition);
//            if (log.isTraceEnabled()) {
//                log.trace("Retrying append due to new batch creation for topic {} partition {}. The old partition was {}", record.topic(), partition, prevPartition);
//            }
//            // producer callback will make sure to call both 'callback' and interceptor callback
//            interceptCallback = new KafkaProducer.InterceptorCallback<>(callback, this.interceptors, tp);
//            // 再次append消息
//            result = accumulator.append(tp, timestamp, serializedKey,
//                    serializedValue, headers, interceptCallback, remainingWaitMs, false, nowMs);
//        }
//
//        // 8.唤醒 sender 线程
//        this.sender.wakeup();
//        ...
//    }
}
