package com.autumn.clever.leetcode.others;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/26 下午4:59
 */
public class x的n次方 {
    public static void main(String[] args) {
        System.out.println(myPow(2, -4));
    }

    public static double myPow(double x, int n) {
        if (x == 0.0d) {
            return x;
        }
        long b = n;
        double res = 1.0d;
        if (b < 0) {
            x = 1 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }
}
