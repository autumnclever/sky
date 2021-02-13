package com.autumn.clever.剑指offer;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/30 下午1:50
 */
public class 剑指Offer48最长不含重复字符的子字符串 {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
        System.out.println(lengthOfLongestSubstring2("bbbbb"));
        System.out.println(lengthOfLongestSubstring2("pwwkew"));
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

    public static int lengthOfLongestSubstring2(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int left = 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = Math.max(map.get(s.charAt(i)) + 1, left);
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }
        return max;
    }
}
