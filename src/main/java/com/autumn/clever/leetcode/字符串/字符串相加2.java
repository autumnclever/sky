package com.autumn.clever.leetcode.字符串;

import org.apache.logging.log4j.util.Strings;

/**
 * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 * （字符串长度不大于100000，保证字符串仅由'0'~'9'这10种字符组成）
 * https://www.nowcoder.com/practice/11ae12e8c6fe48f883cad618c2e81475?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/14 上午11:04
 */
public class 字符串相加2 {
    public static void main(String[] args) {
        String result = solve("1", "99");
        System.out.println(result);
    }

    public static String solve(String s, String t) {
        if (Strings.isEmpty(s)) {
            return t;
        }
        if (Strings.isEmpty(t)) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int i = s.length() - 1;
        int j = t.length() - 1;
        int bit = 0;
        int sum = 0;
        while ((i >= 0 || j >= 0) || bit != 0) {
            sum = bit;
            if (i >= 0) {
                sum += s.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += t.charAt(j) - '0';
                j--;
            }
            if (sum >= 10) {
                bit = 1;
                sum -= 10;
            } else {
                bit = 0;
            }
            sb.append(sum);
        }
        return sb.reverse().toString();
    }
}
