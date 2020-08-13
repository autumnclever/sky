package com.autumn.clever.kafka.source;

/**
 * @Author: zhangqiuying
 * @Date: 2020/7/16 13:25
 */
public class RecordAccumulatorDemo {
//
//    /**
//     * 将 ProducerRecord 添加到 accumulator，返回添加结果
//     * 添加结果：元数据信息，被添加的 batch 是否满了，或者是否是新创建的 batch
//     */
//    public RecordAccumulator.RecordAppendResult append(TopicPartition tp, long timestamp, byte[] key, byte[] value,
//                                                       Header[] headers, Callback callback, long maxTimeToBlock,
//                                                       boolean abortOnNewBatch, long nowMs) throws InterruptedException {
//        // 记录进行 append 的线程数量
//        appendsInProgress.incrementAndGet();
//        ByteBuffer buffer = null;
//        if (headers == null) headers = Record.EMPTY_HEADERS;
//        try {
//            // 根据 TopicPartition 获取或新建 Deque 双端队列
//            Deque<ProducerBatch> dq = getOrCreateDeque(tp);
//
//            // 尝试将消息加入到缓冲区中
//            // 加锁保证同一个 TopicPartition 写入有序
//            synchronized (dq) {
//                if (closed)
//                    throw new KafkaException("Producer closed while send in progress");
//                // 尝试写入
//                RecordAccumulator.RecordAppendResult appendResult = tryAppend(timestamp, key, value, headers, callback, dq, nowMs);
//                if (appendResult != null)
//                    return appendResult;
//            }
//
//            // we don't have an in-progress record batch try to allocate a new batch
//            if (abortOnNewBatch) {
//                // Return a result that will cause another call to append.
//                return new RecordAccumulator.RecordAppendResult(null, false, false, true);
//            }
//
//            byte maxUsableMagic = apiVersions.maxUsableProduceMagic();
//            int size = Math.max(this.batchSize, AbstractRecords.estimateSizeInBytesUpperBound(maxUsableMagic, compression, key, value, headers));
//            log.trace("Allocating a new {} byte message buffer for topic {} partition {}", size, tp.topic(), tp.partition());
//            // 尝试 append 失败（返回 null ），会走到这里。如果 tryAppend 成功直接返回了
//            // 从 BufferPool 中申请内存空间，用于创建新的 ProducerBatch
//            buffer = free.allocate(size, maxTimeToBlock);
//
//            // Update the current time in case the buffer allocation blocked above.
//            nowMs = time.milliseconds();
//            synchronized (dq) {
//                // Need to check if producer is closed again after grabbing the dequeue lock.
//                if (closed)
//                    throw new KafkaException("Producer closed while send in progress");
//
//                // 注意这里，前面已经尝试添加失败了，且已经分配了内存，为何还要尝试添加？
//                // 因为可能已经有其他线程创建了 ProducerBatch 或者之前的 ProducerBatch 已经被 Sender 线程释放了一些空间，所以在尝试添加一次。这里如果添加成功，后面会在 finally 中释放申请的空间
//                RecordAccumulator.RecordAppendResult appendResult = tryAppend(timestamp, key, value, headers, callback, dq, nowMs);
//                if (appendResult != null) {
//                    // Somebody else found us a batch, return the one we waited for! Hopefully this doesn't happen often...
//                    return appendResult;
//                }
//
//                // 尝试添加失败了，新建 ProducerBatch
//                MemoryRecordsBuilder recordsBuilder = recordsBuilder(buffer, maxUsableMagic);
//                ProducerBatch batch = new ProducerBatch(tp, recordsBuilder, nowMs);
//                FutureRecordMetadata future = Objects.requireNonNull(batch.tryAppend(timestamp, key, value, headers, callback, nowMs));
//
//                dq.addLast(batch);
//                incomplete.add(batch);
//
//                // 将 buffer 置为 null，避免在 finally 里面释放空间
//                buffer = null;
//                return new RecordAccumulator.RecordAppendResult(future, dq.size() > 1 || batch.isFull(), true, false);
//            }
//        } finally {
//            // 最后如果再次尝试添加成功，会释放之前申请的内存（为了新建ProducerBatch）
//            if (buffer != null)
//                free.deallocate(buffer);
//            appendsInProgress.decrementAndGet();
//        }
//    }
//
//    // 根据 TopicPartition 获取双端队列，有就直接返回，没有的话创建返回
//    private Deque<ProducerBatch> getOrCreateDeque(TopicPartition tp) {
//        Deque<ProducerBatch> d = this.batches.get(tp);
//        if (d != null)
//            return d;
//        d = new ArrayDeque<>();
//        Deque<ProducerBatch> previous = this.batches.putIfAbsent(tp, d);
//        if (previous == null)
//            return d;
//        else
//            return previous;
//    }
//
//
//    // 尝试添加 ProducerBatch，如果满了，返回 null，并创建一个新的 batch。
//    private RecordAccumulator.RecordAppendResult tryAppend(long timestamp, byte[] key, byte[] value, Header[] headers,
//                                                           Callback callback, Deque<ProducerBatch> deque, long nowMs) {
//        // 从双端队列的尾部取出 ProducerBatch
//        ProducerBatch last = deque.peekLast();
//        if (last != null) {
//            // 取到了，尝试添加消息
//            FutureRecordMetadata future = last.tryAppend(timestamp, key, value, headers, callback, nowMs);
//            if (future == null)
//                // 空间不够，返回null
//                last.closeForRecordAppends();
//            else
//                return new RecordAccumulator.RecordAppendResult(future, deque.size() > 1 || last.isFull(), false, false);
//        }
//        // 取不到返回null
//        return null;
//    }
//
//    public FutureRecordMetadata tryAppend(long timestamp, byte[] key, byte[] value, Header[] headers, Callback callback, long now) {
//        if (!recordsBuilder.hasRoomFor(timestamp, key, value, headers)) {
//            // 空间不够，返回null
//            return null;
//        } else {
//            // 真正添加消息
//            Long checksum = this.recordsBuilder.append(timestamp, key, value, headers);
//            this.maxRecordSize = Math.max(this.maxRecordSize, AbstractRecords.estimateSizeInBytesUpperBound(magic(),
//                    recordsBuilder.compressionType(), key, value, headers));
//            this.lastAppendTime = now;
//            FutureRecordMetadata future = new FutureRecordMetadata(this.produceFuture, this.recordCount,
//                    timestamp, checksum,
//                    key == null ? -1 : key.length,
//                    value == null ? -1 : value.length,
//                    Time.SYSTEM);
//            // future 和回调 callback 进行关联
//            thunks.add(new ProducerBatch.Thunk(callback, future));
//            this.recordCount++;
//            return future;
//        }
//    }
}
