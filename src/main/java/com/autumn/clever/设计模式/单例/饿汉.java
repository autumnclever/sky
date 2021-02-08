package com.autumn.clever.设计模式.单例;

/**
 * 单例模式的四个必要条件：
 * 1. 构造私有；
 * 2. 以静态方法或者枚举，将实例返回；
 * 3. 确保实例只有一个，尤其是多线程环境下；
 * 4. 确保反序列化变换时，不会产生多个实例化对象
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/28 上午10:41
 */
public class 饿汉 {
    private static 饿汉 INSTANCE = new 饿汉();

    private 饿汉() {
    }

    public 饿汉 getINSTANCE() {
        return INSTANCE;
    }
}
