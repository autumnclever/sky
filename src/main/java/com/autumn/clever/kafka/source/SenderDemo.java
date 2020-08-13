package com.autumn.clever.kafka.source;

/**
 * @Author: zhangqiuying
 * @Date: 2020/7/16 18:11
 */
public class SenderDemo {
//    public void run() {
//        log.debug("Starting Kafka producer I/O thread.");
//
//        // main loop, runs until close is called
//        while (running) {
//            try {
//                runOnce();
//            } catch (Exception e) {
//                log.error("Uncaught error in kafka producer I/O thread: ", e);
//            }
//        }
//
//        log.debug("Beginning shutdown of Kafka producer I/O thread, sending remaining records.");
//
//        // okay we stopped accepting requests but there may still be
//        // requests in the transaction manager, accumulator or waiting for acknowledgment,
//        // wait until these are completed.
//        while (!forceClose && ((this.accumulator.hasUndrained() || this.client.inFlightRequestCount() > 0) || hasPendingTransactionalRequests())) {
//            try {
//                runOnce();
//            } catch (Exception e) {
//                log.error("Uncaught error in kafka producer I/O thread: ", e);
//            }
//        }
//
//        // Abort the transaction if any commit or abort didn't go through the transaction manager's queue
//        while (!forceClose && transactionManager != null && transactionManager.hasOngoingTransaction()) {
//            if (!transactionManager.isCompleting()) {
//                log.info("Aborting incomplete transaction due to shutdown");
//                transactionManager.beginAbort();
//            }
//            try {
//                runOnce();
//            } catch (Exception e) {
//                log.error("Uncaught error in kafka producer I/O thread: ", e);
//            }
//        }
//    }
//
//    private long sendProducerData(long now) {
//        // 获取集群信息
//        Cluster cluster = metadata.fetch();
//        // 获取可以发送消息的分区且已经获取到了 leader 分区的节点
//        RecordAccumulator.ReadyCheckResult result = this.accumulator.ready(cluster, now);
//
//        // 如果存在分区，不知道 leader 是谁，强制更新元数据
//        if (!result.unknownLeaderTopics.isEmpty()) {
//            for (String topic : result.unknownLeaderTopics)
//                this.metadata.add(topic, now);
//
//            log.debug("Requesting metadata update due to unknown leader topics from the batched records: {}", result.unknownLeaderTopics);
//            this.metadata.requestUpdate();
//        }
//
//        // 检查 node，如果我们没准备好发送，移除
//        Iterator<Node> iter = result.readyNodes.iterator();
//        long notReadyTimeout = Long.MAX_VALUE;
//        while (iter.hasNext()) {
//            Node node = iter.next();
//            if (!this.client.ready(node, now)) {
//                iter.remove();
//                notReadyTimeout = Math.min(notReadyTimeout, this.client.pollDelayMs(node, now));
//            }
//        }
//
//        // 根据准备好的节点信息从缓冲区中获取 topicPartition 对应的 Deque 队列中取出 ProducerBatch 信息，创建 produce request
//        Map<Integer, List<ProducerBatch>> batches = this.accumulator.drain(cluster, result.readyNodes, this.maxRequestSize, now);
//        // 添加到 Map<TopicPartition, List<ProducerBatch>> inFlightBatches 中去，
//        addToInflightBatches(batches);
//        if (guaranteeMessageOrder) {
//            // Mute all the partitions drained
//            for (List<ProducerBatch> batchList : batches.values()) {
//                for (ProducerBatch batch : batchList)
//                    this.accumulator.mutePartition(batch.topicPartition);
//            }
//        }
//
//        accumulator.resetNextBatchExpiryTime();
//        List<ProducerBatch> expiredInflightBatches = getExpiredInflightBatches(now);
//        List<ProducerBatch> expiredBatches = this.accumulator.expiredBatches(now);
//        expiredBatches.addAll(expiredInflightBatches);
//
//        // Reset the producer id if an expired batch has previously been sent to the broker. Also update the metrics
//        // for expired batches. see the documentation of @TransactionState.resetIdempotentProducerId to understand why
//        // we need to reset the producer id here.
//        if (!expiredBatches.isEmpty())
//            log.trace("Expired {} batches in accumulator", expiredBatches.size());
//        for (ProducerBatch expiredBatch : expiredBatches) {
//            String errorMessage = "Expiring " + expiredBatch.recordCount + " record(s) for " + expiredBatch.topicPartition
//                    + ":" + (now - expiredBatch.createdMs) + " ms has passed since batch creation";
//            failBatch(expiredBatch, -1, NO_TIMESTAMP, new TimeoutException(errorMessage), false);
//            if (transactionManager != null && expiredBatch.inRetry()) {
//                // This ensures that no new batches are drained until the current in flight batches are fully resolved.
//                transactionManager.markSequenceUnresolved(expiredBatch);
//            }
//        }
//        sensors.updateProduceRequestMetrics(batches);
//
//        // If we have any nodes that are ready to send + have sendable data, poll with 0 timeout so this can immediately
//        // loop and try sending more data. Otherwise, the timeout will be the smaller value between next batch expiry
//        // time, and the delay time for checking data availability. Note that the nodes may have data that isn't yet
//        // sendable due to lingering, backing off, etc. This specifically does not include nodes with sendable data
//        // that aren't ready to send since they would cause busy looping.
//        long pollTimeout = Math.min(result.nextReadyCheckDelayMs, notReadyTimeout);
//        pollTimeout = Math.min(pollTimeout, this.accumulator.nextExpiryTimeMs() - now);
//        pollTimeout = Math.max(pollTimeout, 0);
//        if (!result.readyNodes.isEmpty()) {
//            log.trace("Nodes with data ready to send: {}", result.readyNodes);
//            // if some partitions are already ready to be sent, the select time would be 0;
//            // otherwise if some partition already has some data accumulated but not ready yet,
//            // the select time will be the time difference between now and its linger expiry time;
//            // otherwise the select time will be the time difference between now and the metadata expiry time;
//            pollTimeout = 0;
//        }
//
//        // 将消息转移到每个节点的生产请求队列中
//        sendProduceRequests(batches, now);
//        return pollTimeout;
//    }
//
//    if (!result.unknownLeaderTopics.isEmpty()) {
//        for (String topic : result.unknownLeaderTopics)
//            this.metadata.add(topic, now);
//        // 请求更新 metadata 信息
//        this.metadata.requestUpdate();
//    }
}
