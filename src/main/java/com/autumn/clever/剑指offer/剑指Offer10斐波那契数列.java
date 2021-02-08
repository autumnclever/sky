package com.autumn.clever.剑指offer;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/30 下午11:27
 */
public class 剑指Offer10斐波那契数列 {
    public static void main(String[] args) {
        System.out.println(fib2(1));
        System.out.println(fib2(5));
    }

    public static int fib2(int n) {
        if (n == 0) {
            return 0;
        }
        int a = 0;
        int b = 1;
        int c = 1;
        for (int i = 2; i <= n; i++) {
            c = (a + b) % 1000000007;
            a = b;
            b = c;
        }
        return c;
    }

    public static int fib(int n) {
        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 0; i < n; i++) {
            System.out.println("begin --- i= " + i + ";a=" + a + ";b=" + b + ";c=" + c);
            c = (a + b) % 1000000007;
            a = b;
            b = c;
            System.out.println("end --- i= " + i + ";a=" + a + ";b=" + b + ";c=" + c);
        }
        return a;
    }
}
