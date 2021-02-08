package com.autumn.clever.设计模式.单例;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/28 上午10:50
 */
public class 双重校验锁 {
    private static 双重校验锁 INSTANCE = null;

    private 双重校验锁() {
    }

    /**
     * 错误的双重校验锁，这个地方可能会发生指令重排序，
     * 1.为对象分配内存；
     * 2.对象初始化；
     * 3.对象指向内存空间
     * <p>
     * 1和2如果步骤颠倒，会线程不安全 -> 正确的做法，将 INSTANCE 用 volatile 修饰
     *
     * @return
     */
    public static 双重校验锁 getINSTANCE() {
//        if (INSTANCE == null) {
//            sychronized(双重校验锁.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new 双重校验锁(); // error
//                }
//            }
//        }
        return INSTANCE;
    }
}
