package com.autumn.clever.baidu;

import org.apache.commons.codec.Charsets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-10-01 19:37
 **/
public class StringTools {
    private static Pattern pattern = Pattern.compile("\\d+$");
    private static String URL_PARAM_SEPARATOR = "&";
    private static String URL_PARAM_KV_SEPARATOR = "=";
    private static final Logger logger = LoggerFactory.getLogger(StringTools.class);


    public static String replaceIgnoreFields(String value, List<String> fields) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        if (CollectionUtils.isEmpty(fields)) {
            return value;
        }
        for (String field : fields) {
            value = value.replaceAll(field + "(\\\\)*\"(:|=)[^,]*(?=,)", field + "\":**");
        }
        return value;
    }

    /**
     * 字符串敏感信息字段处理 v2，解决v1版本屏蔽数据丢失"}"问题
     *
     * @param value  待处理数据
     * @param fields 敏感字段
     * @return 处理结果
     */
    public static String replaceIgnoreFieldsV2(String value, List<String> fields) {
        if (StringUtils.isBlank(value) || CollectionUtils.isEmpty(fields)) {
            return value;
        }
        for (String field : fields) {
            value = replaceValue(value, field);
        }
        return value;
    }

    /**
     * 正则匹配替换指定字段
     *
     * @param tempMsg 待处理数据
     * @param key     指定字段
     * @return 处理结果
     */
    private static String replaceValue(String tempMsg, String key) {
        String reg = "([^0-9a-zA-Z]+" + key + "[^0-9a-zA-Z]?[:=][\"|']?)(.*?)([,|}|\"|'])";
        return tempMsg.replaceAll(reg, "$1****$3");
    }

    public static String replaceIndex(String text, int start, int end, String placeHolder) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        if (placeHolder == null) {
            return text;
        }
        if (start < 0 || end < 0) {
            throw new IllegalArgumentException("start value error, " + start + ", end value " + end);
        }
        if (start > end) {
            throw new IllegalArgumentException("start value error, " + start + ", end value " + end);
        }
        int len = text.length();
        if (start > len || end > len) {
            throw new IllegalArgumentException("start value error, " + start + ", end value " + end);
        }

        StringBuilder builder = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            if (i >= start && i <= end) {
                builder.append(placeHolder);
                continue;
            }
            builder.append(text.charAt(i));

        }

        return builder.toString();
    }

    public static boolean endWithNumber(String text) {
        if (StringUtils.isBlank(text)) {
            return false;
        }

        return pattern.matcher(text).find();
    }

    public static boolean equalsReg(String text, String regex) {
        if (StringUtils.isBlank(text)) {
            return false;
        }
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text).matches();
    }

    /**
     * url参数转Map格式, 支持参数转义
     *
     * @param urlParams url参数字符串 k1=v1&k2=v2&k3=v3
     * @return 参数map
     */
    public static Map<String, String> urlParamsToMap(String urlParams) {
        Map<String, String> reqMap = new HashMap<>();
        try {
            String[] pairs = org.springframework.util.StringUtils.tokenizeToStringArray(urlParams, URL_PARAM_SEPARATOR);
            for (String pair : pairs) {
                int index = pair.indexOf(URL_PARAM_KV_SEPARATOR);
                if (-1 == index) {
                    reqMap.put(URLDecoder.decode(pair, Charsets.UTF_8.name()), null);
                } else {
                    String name = URLDecoder.decode(pair.substring(0, index), Charsets.UTF_8.name());
                    String value = URLDecoder.decode(pair.substring(index + 1), Charsets.UTF_8.name());
                    reqMap.put(name, value);
                }
            }
        } catch (Exception e) {
            logger.error("StringTools_urlParamsToMap_error urlParams:{}", urlParams, e);
        }
        return reqMap;
    }
}
