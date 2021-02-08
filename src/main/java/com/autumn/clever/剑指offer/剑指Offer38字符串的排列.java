package com.autumn.clever.剑指offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/30 上午11:10
 */
public class 剑指Offer38字符串的排列 {
    public static void main(String[] args) {
        String[] array = permutation3("aab");
        for (String a : array) {
            System.out.println(a);
        }
    }

    public static String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        int len = s.length();
        char[] chars = s.toCharArray();
        String[] array = new String[len * len];
        StringBuilder sb = new StringBuilder();
        help(chars, sb, 0, array);
        return array;
    }

    public static void help(char[] chars, StringBuilder sb, Integer index, String[] array) {
        if (sb.length() == chars.length) {
            array[index] = sb.toString();
            index += 1;
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            String s = String.valueOf(chars[i]);
            if (sb.toString().contains(s)) {
                continue;
            }
            sb.append(s);
            System.out.println(sb.toString());
            help(chars, sb, index, array);
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
        }
    }

    public static String[] permutation2(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        help2(s, sb, list);
        String[] array = new String[list.size()];
        int index = 0;
        for (String l : list) {
            array[index++] = l;
        }
        return array;
    }

    public static void help2(String s, StringBuilder sb, List<String> list) {
        if (sb.length() == s.length()) {
            list.add(sb.toString());
            return;
        }

        for (int i = 0; i < s.length(); i++) {
            String str = String.valueOf(s.charAt(i));
            if (sb.toString().contains(str)) {
                continue;
            }
            sb.append(str);
            help2(s, sb, list);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


    public static String[] permutation3(String s) {
        if (s == null || s.length() == 0) {
            return new String[0];
        }
        char[] chars = s.toCharArray();
        // 因为涉及到去重，先进行排序，这样重复的值都是挨在一起的，方便前后判断重复问题
        Arrays.sort(chars);
        StringBuilder sb = new StringBuilder();
        List<String> list = new ArrayList<>();
        // 1.原结构中存在重复元素；2.返回结果要求不能有重复；使用 used 数组判断是否有重复
        boolean[] used = new boolean[s.length()];

        help3(chars, used, sb, list);
        // list -> array
        return list.toArray(new String[list.size()]);
    }

    public static void help3(char[] chars, boolean[] used, StringBuilder sb, List<String> list) {
        if (sb.length() == chars.length) {
            list.add(sb.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (used[i] || i > 0 && chars[i] == chars[i - 1] && !used[i - 1]) {
                // 两种情况进行剪枝：
                // 1.如果 i 位置上的数字使用过了，直接跳过；
                // 2.如果 i 位置上的数字没有使用过 && 当前索引值 > 0
                // && 当前索引值上面的数字和前一位索引值上的数字相同 && 前一位索引上的数字已经使用过了，也同样直接跳过
                continue;
            }
            used[i] = true;
            sb.append(chars[i]);
            help3(chars, used, sb, list);
            // 回溯：
            // 1.删除 sb 最后一位的值
            // 2.将当前索引位置的值使用过 -> 未使用
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }
}
