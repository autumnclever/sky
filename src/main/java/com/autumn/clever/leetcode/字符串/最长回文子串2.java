package com.autumn.clever.leetcode.字符串;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/25 下午9:53
 */
public class 最长回文子串2 {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babadb"));
        System.out.println(longestPalindrome("babadba"));
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = true;
        }

        int maxLen = 1;
        int begin = 0;
        char[] chars = s.toCharArray();
        for (int j = 1; j < chars.length; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
