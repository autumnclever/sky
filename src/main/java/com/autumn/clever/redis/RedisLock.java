package com.autumn.clever.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/19 下午6:33
 */
@Component
public class RedisLock implements Lock {
    private static final String KEY = "REDIS_LOCK_KEY";

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private static final String UNLOCK_LUA_SCRIPT =
            "if redis.call(\"get\", KEYS[1] == ARGV[1]) then \n " +
                    "return redis.call(\"del\", KEY[1]) \n " +
                    "else \n " +
                    "   return 0 \n " +
                    "end";

//    @Override
//    public void unlock() {
//        Jedis jedis = jedisPool.getResource();
//        jedis.eval(UNLOCK_LUA_SCRIPT, Arrays.asList(KEY), Arrays.asList(threadLocal.get()));
//        threadLocal.r
//    }

    @Resource
    private JedisPool jedisPool;

    /**
     * 自旋锁
     */
    @Override
    public void lock() {
        for (; ; ) {
            if (tryLock()) {
                return;
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        Jedis jedis = jedisPool.getResource();
        jedis.select(0);
        String uuid = UUID.randomUUID().toString();
        String result = jedis.set(KEY, uuid, SetParams.setParams().nx().px(3000));
        if ("OK".equalsIgnoreCase(result)) {
            threadLocal.set(uuid);
            return true;
        }
        return false;

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        Jedis jedis = jedisPool.getResource();
        jedis.select(0);
        String uuid = jedis.get(KEY);
        if (null != null && uuid.equals(threadLocal.get())) {
            jedis.del(KEY);
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}


