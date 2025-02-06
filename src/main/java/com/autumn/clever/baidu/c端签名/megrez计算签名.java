/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.c端签名;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * FileName: megrez计算签名
 * Author:   zhangqiuying
 * Date:     2024/9/3 18:30
 * Description:
 */
public class megrez计算签名 {

    public static void main(String[] args) {

        Random Random = new Random();
        Integer nonce = Random.nextInt(1000000);
        System.out.println(nonce);
        String signoffline = computeSign("82c02d109ea97863eb9ef75a3f7d51e4", 1731491158L, String.valueOf(nonce));
        System.out.println("线下环境签名: " + signoffline);

        String signonline = computeSign("19195a71e76912f42b0f4f433562bea2", 1731491158L, String.valueOf(nonce));
        System.out.println("线上环境签名: " + signonline);

    }

    /**
     * 这个map需要对key已做好排序
     */
    public static String computeSign(String appKey, Long timeStamp, String nonce) {
        // 计算签名
        Map<String, String> paramMap = new TreeMap<>();
        paramMap.put(CommonConstants.APP_KEY_NAME, appKey);
        paramMap.put(CommonConstants.TIMESTAMP, String.valueOf(timeStamp));
        paramMap.put(CommonConstants.NONCE, nonce);

        if (CollectionUtils.isEmpty(paramMap)) {
            return "";
        }
        String originStr = MAP_JOINER.join(paramMap);
        return DigestUtils.md5DigestAsHex(originStr.getBytes(Charsets.UTF_8));
    }

    /**
     * join map 常用
     */
    public static final Joiner.MapJoiner MAP_JOINER = Joiner.on("&").withKeyValueSeparator("=");
}
