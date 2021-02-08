package com.autumn.clever.剑指offer;

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * <p>
 * 输入: n = 10
 * 输出: 12
 * 解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/7 下午4:19
 */
public class 剑指Offer49丑数 {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }

    public static int nthUglyNumber(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n];
        dp[0] = 1;
        int a = 0;
        int b = 0;
        int c = 0;
        for (int i = 1; i < n; i++) {
            int numA = dp[a] * 2;
            int numB = dp[b] * 3;
            int numC = dp[c] * 5;
            dp[i] = Math.min(Math.min(numA, numB), numC);
            if (dp[i] == numA) {
                a++;
            }
            if (dp[i] == numB) {
                b++;
            }
            if (dp[i] == numC) {
                c++;
            }
        }
        return dp[n - 1];
    }
}
