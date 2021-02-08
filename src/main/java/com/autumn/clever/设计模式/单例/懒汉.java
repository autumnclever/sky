package com.autumn.clever.设计模式.单例;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/28 上午10:37
 */
public class 懒汉 {
    private static 懒汉 INSTANCE = null;

    private 懒汉() {

    }

    public static 懒汉 getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new 懒汉();
        }
        return INSTANCE;
    }
}
