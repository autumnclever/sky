package com.autumn.clever;

import com.autumn.clever.goodcoder.ApplicationClosedEventListener;
import com.autumn.clever.goodcoder.ApplicationStartedEventListener;
import com.autumn.clever.goodcoder.LruCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CleverApplication {

    private static volatile LruCache lruCache = null;

    static {
        if (lruCache == null) {
            synchronized (LruCache.class) {
                if (lruCache == null) {
                    lruCache = new LruCache(3);
                }
            }
        }
    }

    public static void main(String[] args) {
//        SpringApplication.run(CleverApplication.class, args);

        SpringApplication application = new SpringApplication(CleverApplication.class);
        application.addListeners(new ApplicationStartedEventListener(lruCache));
        application.addListeners(new ApplicationClosedEventListener(lruCache));
        application.run(args);
    }

    public static LruCache getLruCache() {
        return lruCache;
    }
}
