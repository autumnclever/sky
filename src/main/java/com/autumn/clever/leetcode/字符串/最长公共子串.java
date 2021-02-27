package com.autumn.clever.leetcode.字符串;

/**
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串，如果最长公共子串为空，输出-1。
 * https://www.nowcoder.com/practice/f33f5adc55f444baa0e0ca87ad8a6aac?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/15 下午11:22
 */
public class 最长公共子串 {
    public static void main(String[] args) {
        System.out.println(LCS("1AB2345CD", "12345EF"));
    }

    public static String LCS(String str1, String str2) {
        if (str1 == null || str1.length() == 0 || str2 == null || str2.length() == 0) {
            return "-1";
        }
        String max = "";
        int i = 0;
        int j = 0;
        while (i < str1.length()) {
            int begin = i;
            while ((i < str1.length() && j < str2.length()) || (str1.charAt(i) == str2.charAt(j))) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    i++;
                    j++;
                } else if (i < str1.length() && j < str2.length()) {
                    j++;
                } else {
                    max = Math.max(i - begin, max.length()) == max.length() ? max : str1.substring(begin, i);
                    i = begin;
                    j++;
                }
            }
            i++;
        }
        return max;
    }
}
