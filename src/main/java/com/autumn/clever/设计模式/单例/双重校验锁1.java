package com.autumn.clever.设计模式.单例;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/25 上午9:47
 */
public class 双重校验锁1 {
    private static volatile 双重校验锁1 stance;

    private 双重校验锁1() {

    }

    public static 双重校验锁1 getSingle() {
        if (stance == null) {
            synchronized (双重校验锁1.class) {
                if (stance == null) {
                    stance = new 双重校验锁1();
                }
            }
        }
        return stance;
    }
}
