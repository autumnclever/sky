package com.autumn.clever.剑指offer;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/30 下午1:50
 */
public class 剑指Offer48最长不含重复字符的子字符串 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }

    public static int lengthOfLongestSubstring(String s) {
        int index1 = 0;
        int index2 = 0;

        int max = 0;

        while (index2 < s.length()) {
            boolean isContain = s.substring(index1, index2).contains(String.valueOf(s.charAt(index2)));
            if (!isContain) {
                index2++;
            } else {
                index1++;
            }
            max = Math.max(index2 - index1, max);
        }
        return max;
    }
}
