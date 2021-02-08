package com.autumn.clever.剑指offer;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。
 * 为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/30 下午3:32
 */
public interface 剑指Offer58I翻转单词顺序 {
    public static void main(String[] args) {
        System.out.println(reverseWords("  hello world!  "));
    }

    public static String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        s.trim();
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i].equals("")) {
                continue;
            }
            sb.append(strs[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}