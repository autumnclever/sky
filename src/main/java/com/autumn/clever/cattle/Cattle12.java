package com.autumn.clever.cattle;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 * <p>
 * 保证base和exponent不同时为0
 * https://www.nowcoder.com/questionTerminal/1a834e5e3e1a4b7ba251417554e07c00
 * 完整快速幂：https://baike.baidu.com/item/%E5%BF%AB%E9%80%9F%E5%B9%82/5500243?fr=aladdin
 *
 * @Author: zhangqiuying
 * @Date: 2020/5/31 17:38
 */
public class Cattle12 {
    public static void main(String[] args) {
        double result = Power(10, 13);
        System.out.println(result);
    }

    public static double Power(double base, int exponent) {
        if (base == 0d) {
            return 0d;
        }

        if (exponent == 0) {
            return 1;
        }

        int n = exponent;
        if (exponent < 0) {
            n = -exponent;
        }

        double result = 1;
        while (n != 0) {
            if ((n & 1) == 1) {
                result *= base;
            }
            base *= base;
            n >>= 1;
        }
        return exponent > 0 ? result : (1 / result);
    }
}
