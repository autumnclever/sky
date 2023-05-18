package com.autumn.clever.goodcoder;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-05-08 16:38
 **/
@Slf4j
public class LruCacheUtils {

    private static ConcurrentHashMap cacheMap = new ConcurrentHashMap<>();
    private static ConcurrentLinkedQueue keys = new ConcurrentLinkedQueue<>();

    private static String LRU_CACHE_FILE_PATH1 = "file/lruCache.txt";
    private static String LRU_CACHE_WRITE_FILE_PATH = "src/main/resources/file/lruCache.txt";

    static {
        try {
            loadLruCache(LRU_CACHE_FILE_PATH1);
        } catch (Exception e) {
            log.error("load store basic and main biz error", e);
        }
    }

    private static void loadLruCache(String file) {
        List<String> lines = FileUtils.readLinesFromClasspath(file);
        if (CollectionUtils.isEmpty(lines)) {
            return;
        }
        String firstLine = lines.get(0);
        cacheMap = JacksonUtil.parseObject(firstLine, ConcurrentHashMap.class);
        String secondLine = lines.get(1);
        keys = JacksonUtil.parseObject(secondLine, ConcurrentLinkedQueue.class);
    }


    public static void saveLruCache(ConcurrentHashMap cacheMap, ConcurrentLinkedQueue keys) {
        String firstLine = JacksonUtil.toJSONString(cacheMap);
        String secondLine = JacksonUtil.toJSONString(keys);
        List<String> lines = Arrays.asList(firstLine, secondLine);
        FileUtils.writeLines(lines, LRU_CACHE_WRITE_FILE_PATH, false);
    }

    public static ConcurrentHashMap getCacheMap() {
        return cacheMap;
    }

    public static ConcurrentLinkedQueue getKeys() {
        return keys;
    }
}
