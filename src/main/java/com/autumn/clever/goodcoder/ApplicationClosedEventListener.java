package com.autumn.clever.goodcoder;

import org.apache.commons.collections4.MapUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-05-08 16:28
 **/
public class ApplicationClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    private LruCache lruCache;

    public ApplicationClosedEventListener() {
    }

    public ApplicationClosedEventListener(LruCache lruCache) {
        this.lruCache = lruCache;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        LruCacheUtils.saveLruCache(lruCache.getCacheMap(), lruCache.getKeys());
    }
}
