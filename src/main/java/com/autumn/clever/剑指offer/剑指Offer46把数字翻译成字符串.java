package com.autumn.clever.剑指offer;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/30 下午4:36
 */
public class 剑指Offer46把数字翻译成字符串 {
    public static void main(String[] args) {
        System.out.println(translateNum2(25));
//        System.out.println("506".compareTo("10"));
    }

    public static int translateNum(int num) {
        if (num < 10) {
            return 1;
        }

        String str = String.valueOf(num);
        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < str.length(); i++) {
            String sub = str.substring(i - 1, i + 1);
            int temp = Integer.valueOf(sub);
            if (temp > 25 || temp < 10) {
                dp[2] = dp[1];
            } else {
                dp[2] = dp[1] + dp[0];
            }
            dp[0] = dp[1];
            dp[1] = dp[2];
        }
        return dp[2];
    }

    public static int translateNum2(int num) {
        String str = String.valueOf(num);
        int a = 1;
        int b = 1;
        int c = 1;
        for (int i = 2; i <= str.length(); i++) {
            String sub = str.substring(i - 2, i);
            if (sub.compareTo("10") >= 0 && sub.compareTo("25") <= 0) {
                c = a + b;
            } else {
                c = b;
            }
            a = b;
            b = c;
        }
        return c;
    }
}
