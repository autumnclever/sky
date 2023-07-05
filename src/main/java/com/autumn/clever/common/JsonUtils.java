package com.autumn.clever.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

/**
 * @program: sky
 * @description: json 工具类
 * @author: zhangqiuying
 * @create: 2023-06-16 14:43
 **/
@Slf4j
public class JsonUtils {

    private static ObjectMapper objectMapper;

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
}
