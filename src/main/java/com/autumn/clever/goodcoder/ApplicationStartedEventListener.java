package com.autumn.clever.goodcoder;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-05-08 16:26
 **/
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    private LruCache lruCache;

    public ApplicationStartedEventListener() {
    }

    public ApplicationStartedEventListener(LruCache lruCache) {
        this.lruCache = lruCache;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        ConcurrentHashMap cacheMap = LruCacheUtils.getCacheMap();
        lruCache.setCacheMap(cacheMap);
        ConcurrentLinkedQueue keys = LruCacheUtils.getKeys();
        lruCache.setKeys(keys);
    }
}
