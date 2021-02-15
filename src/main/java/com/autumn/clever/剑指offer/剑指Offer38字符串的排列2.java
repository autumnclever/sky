package com.autumn.clever.剑指offer;

import java.util.ArrayList;

/**
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
 * 则按字典序打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 * https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/14 下午5:33
 */
public class 剑指Offer38字符串的排列2 {
    public static void main(String[] args) {
        ArrayList<String> array = Permutation("abc");
        for (String a : array) {
            System.out.println(a);
        }
    }

    public static ArrayList<String> Permutation(String str) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }
        boolean[] used = new boolean[str.length()];
        ArrayList<String> arrays = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Permutation(str, used, arrays, sb);
        return arrays;
    }

    public static void Permutation(String str, boolean[] used, ArrayList<String> arrays, StringBuilder sb) {
        if (sb.length() == str.length()) {
            arrays.add(sb.toString());
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            if (used[i] || (i > 0 && used[i - 1] && str.charAt(i) == str.charAt(i - 1))) {
                continue;
            }
            used[i] = true;
            sb.append(str.charAt(i));
            Permutation(str, used, arrays, sb);
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }
}
