package com.autumn.clever.剑指offer;

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/30 下午4:28
 */
public class 剑指Offer05替换空格 {
    public static void main(String[] args) {
        System.out.println(replaceSpace("We are happy."));
    }

    public static String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (String.valueOf(s.charAt(i)).equals(" ")) {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
