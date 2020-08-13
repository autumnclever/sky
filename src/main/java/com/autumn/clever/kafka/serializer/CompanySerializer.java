package com.autumn.clever.kafka.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * 生产者：通过序列化器，把对象转换成字节数组通过网络发送给 Kafka
 *
 * @Author: zhangqiuying
 * @Date: 2020/7/14 20:59
 */
public class CompanySerializer implements Serializer<Company> {
    private static final String ENCODE = "UTF-8";

    /**
     * 配置当前类
     *
     * @param configs
     * @param isKey
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    /**
     * 执行序列化操作
     *
     * @param topic
     * @param data
     * @return
     */
    @Override
    public byte[] serialize(String topic, Company data) {
        if (data == null) {
            return null;
        }

        byte[] name, address;
        try {
            if (data.getName() != null) {
                name = data.getName().getBytes(ENCODE);
            } else {
                name = new byte[0];
            }
            if (data.getAddress() != null) {
                address = data.getAddress().getBytes(ENCODE);
            } else {
                address = new byte[0];
            }
            ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + name.length + address.length);
            buffer.putInt(name.length);
            buffer.put(name);
            buffer.putInt(address.length);
            buffer.put(address);
            return buffer.array();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 关闭当前的序列化器
     */
    @Override
    public void close() {

    }
}
