package com.autumn.clever.剑指offer;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/30 下午3:00
 */
public class 剑指Offer50第一个只出现一次的字符 {
    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(firstUniqChar2(s));
    }

    public static char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer count = map.get(s.charAt(i));
            if (count == null || count.equals(0)) {
                map.put(s.charAt(i), 1);
            } else {
                map.put(s.charAt(i), count + 1);
            }
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)).equals(1)) {
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public static char firstUniqChar2(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Integer count = map.get(s.charAt(i));
            if (count == null || count.equals(0)) {
                map.put(s.charAt(i), 1);
            } else {
                map.put(s.charAt(i), count + 1);
            }
        }

        for (Character c : map.keySet()) {
            if (map.get(c).equals(1)) {
                return c;
            }
        }
        return ' ';
    }
}
