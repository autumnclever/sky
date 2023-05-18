package com.autumn.clever.goodcoder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-05-08 17:21
 **/
@Slf4j
public class JacksonUtil {

    private static JsonFactory factory = new JsonFactory();

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper(factory);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 如果存在未知属性，则忽略不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 日期转换
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }


    /**
     * Object to String
     *
     * @param obj 待转换对象
     * @return String
     */
    public static String toJSONString(Object obj) {
        try {
            if (obj == null) {
                return null;
            }
            return objectMapper.writeValueAsString(obj);
        } catch (Throwable e) {
            log.error("JacksonUtil_toJSONString_exception CocoException", e);
        }
        return null;
    }

    /**
     * String to Object
     *
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String value, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(value)) {
                return null;
            }
            return objectMapper.readValue(value.getBytes("UTF-8"), clazz);
        } catch (Throwable e) {
            log.error("JacksonUtil_parseObject_exception CocoException", e);
        }
        return null;
    }
}
