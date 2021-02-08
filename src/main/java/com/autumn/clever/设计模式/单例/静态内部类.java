package com.autumn.clever.设计模式.单例;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/28 上午11:00
 */
public class 静态内部类 {
    // 定义一个静态内部类 然后把这个实例化注入到静态内部类里面
    private static class 静态内部类Holder {
        private static 静态内部类 INSTANCE = new 静态内部类();
    }

    public static 静态内部类 getINSTANCE() {
        return 静态内部类Holder.INSTANCE;
    }

    private static class 静态内部类单例 {
        private static 静态内部类 instance = new 静态内部类();
    }

}
