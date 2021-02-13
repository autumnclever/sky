package com.autumn.clever.剑指offer;

import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/12 下午2:56
 */
public class 剑指Offer60n个骰子的点数 {
    public static void main(String[] args) {
        double[] array = dicesProbability(2);
        System.out.println(Arrays.toString(array));
    }

    public static double[] dicesProbability(int n) {
        if (n <= 0) {
            return new double[0];
        }
        // 当掷骰子，掷到第 n 个的时候，出现的点数和
        int[][] dp = new int[n][6 * n];
        for (int i = 0; i < 6; i++) {
            // 初始化边界值，掷骰子，第一个，出现的点数从1-6，并且每个值最多出现 1 次
            dp[0][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = i; j < 6 * (i + 1); j++) {
                for (int m = 1; m <= 6; m++) {
                    if (j - m < 0) {
                        break;
                    }
                    dp[i][j] += dp[i - 1][j - m];
                }
            }
        }
        double count = Math.pow(6.0d, Double.valueOf(n));
        double[] array = new double[5 * n + 1];
        int index = 0;
        for (int i = n - 1; i < dp[n - 1].length; i++) {
            array[index++] = Math.round(dp[n - 1][i] / count * 100000) * 0.00001d;
        }
        return array;
    }
}
