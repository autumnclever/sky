package com.autumn.clever.kafka.serializer;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

/**
 * @Author: zhangqiuying
 * @Date: 2020/7/14 21:36
 */
public class CompanyDeserializer implements Deserializer<Company> {
    private static final String ENCODE = "UTF-8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Company deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length < 8) {
            throw new SerializationException("size of data received by deserializer is shorter than expected!");
        }

        ByteBuffer buffer = ByteBuffer.wrap(data);
        int nameLen, addressLen;
        String name, address;

        nameLen = buffer.getInt();
        byte[] nameBytes = new byte[nameLen];
        buffer.get(nameBytes);
        addressLen = buffer.getInt();
        byte[] addressBytes = new byte[addressLen];
        buffer.get(addressBytes);
        try {
            name = new String(nameBytes, ENCODE);
            address = new String(addressBytes, ENCODE);
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("error occur when deserializing");
        }
        return new Company(name, address);
    }

    @Override
    public void close() {

    }
}
