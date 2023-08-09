package com.autumn.clever.common;

import com.autumn.clever.goodcoder.JacksonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @program: sky
 * @description: json 工具类
 * @author: zhangqiuying
 * @create: 2023-06-16 14:43
 **/
@Slf4j
public class JsonUtils {

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

    public static <T> T jsonToObject(String jsonStr, Class<T> c) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonStr.getBytes("UTF-8"), c);
        } catch (Exception e) {
            log.warn("json转换对象异常:{}", jsonStr, e);
        }
        return null;
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("对象转换json异常", e);
        }
        return null;
    }

    /**
     * String to JsonNode
     *
     * @param jsonStr 待转换json
     * @return jsonNode
     */
    public static JsonNode parseObject(String jsonStr) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        try {
            return objectMapper.readTree(jsonStr.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("JacksonUtil_parseObject_exception", e);
        }
        return null;
    }

    /**
     * 获取String 属性值
     *
     * @param jsonNode json
     * @param key      key
     * @return value
     */
    public static String getString(JsonNode jsonNode, String key) {
        if (jsonNode == null) {
            return null;
        }
        JsonNode value = jsonNode.get(key);
        if (value == null) {
            return null;
        }
        if (value.isObject()) {
            return JacksonUtil.toJSONString(value);
        } else {
            return value.asText();
        }
    }

    /**
     * String to List
     *
     * @param jsonStr 待转换json
     * @param clazz to List class type
     * @param <T>   泛型
     * @return List
     */
    public static <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
        if (StringUtils.isBlank(jsonStr)) {
            return null;
        }
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(
                    List.class, clazz);
            return objectMapper.readValue(jsonStr, javaType);
        } catch (Exception e) {
            log.error("JacksonUtil_parseArray_exception", e);
        }
        return null;
    }
}
