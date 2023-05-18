package com.autumn.clever.goodcoder;

import com.autumn.clever.CleverApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-05-08 16:37
 **/
@RestController
@RequestMapping("/lruCache")
public class CacheController {

    private LruCache lruCache = CleverApplication.getLruCache();

    @RequestMapping("/get")
    public Object get(@RequestParam() String key) {
        return lruCache.get(key);
    }

    @RequestMapping("/put")
    public Object put(@RequestParam() String key,
                      @RequestParam() String value,
                      @RequestParam()long expireTime) {
        return lruCache.put(key, value, expireTime);
    }

    @RequestMapping("/remove")
    public Object remove(@RequestParam() String key) {
        return lruCache.remove(key);
    }
}
