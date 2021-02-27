package com.autumn.clever.leetcode.字符串;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/16 上午8:28
 */
public class 最长公共子串2 {
    public static void main(String[] args) {
        System.out.println(LCS("1AB2345CD", "12345EF"));
    }

    public static String LCS(String str1, String str2) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return "-1";
        }
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int maxLen = 0;
        int end = 0;
        int start = 0;
//        for (int i = 0; i < str1.length(); i++) {
//            dp[i][0] = 0;
//        }
//        for (int j = 0; j < str2.length(); j++) {
//            dp[0][j] = 0;
//        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = 0;
                }
                if (dp[i][j] > maxLen) {
                    maxLen = dp[i][j];
                    start = j - maxLen;
                    end = j - 1;
                }
            }
        }
        return maxLen <= 0 ? "-1" : str2.substring(start, end + 1);
    }

//    String LCS2(String str1, String str2) {
//        // write code here
//        int m = str1.length();
//        int n = str2.length();
//        // dp[i][j] str1前i个字符和str2前j个字符（以其为尾字符）的最长公共子串长度
//        int[][] dp = new int[m + 1][n + 1];
//        int maxlen = 0, end = 0;
//        //base case
//        for (int i = 0; i <= m; ++i) dp[i][0] = 0;
//        for (int j = 0; j <= n; ++j) dp[0][j] = 0;
//        for (int i = 1; i <= m; ++i) {
//            for (int j = 1; j <= n; ++j) {
//                if (str1.charAt(i - 1) == str2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
//                else dp[i][j] = 0;
//                if (dp[i][j] > maxlen) {
//                    maxlen = dp[i][j];
//                    end = j - 1;
//                }
//            }
//        }
//        String r;
//        if (maxlen == 0) return "-1";
//        else r = str2.substring(end - maxlen + 1, maxlen);
//        return r;
//    }
}
