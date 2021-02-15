package com.autumn.clever.剑指offer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/13 下午6:05
 */
public class 剑指Offer10II青蛙跳台阶问题2 {
    public static void main(String[] args) {
        System.out.println(JumpFloor(1));
        System.out.println(JumpFloor(2));
        System.out.println(JumpFloor(3));
        System.out.println(JumpFloor(44));
    }

    public static int JumpFloor(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target <= 2) {
            return target;
        }
        int a = 1;
        int b = 2;
        int c = 1;
        for (int i = 2; i < target; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
