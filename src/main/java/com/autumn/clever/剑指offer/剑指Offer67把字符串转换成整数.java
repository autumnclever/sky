package com.autumn.clever.剑指offer;

/**
 * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。
 * 如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/11 下午2:04
 */
public class 剑指Offer67把字符串转换成整数 {
    public static void main(String[] args) {
//        System.out.println(strToInt3("words and 987"));
//        System.out.println(strToInt3(" "));
//        System.out.println(strToInt3("-91283472332"));
//        System.out.println(strToInt2("words and 987"));
//        System.out.println(strToInt2(" "));
//        System.out.println(strToInt2("-91283472332"));
        System.out.println(strToInt2("     -117e40091539"));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
    }

    public static int strToInt3(String str) {
        char[] chars = str.trim().toCharArray();
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int sign = 1;
        int max = Integer.MAX_VALUE / 10;
        int i = 0;
        if (chars[i] == '-') {
            sign = -1;
            i = 1;
        } else if (chars[i] == '+') {
            i = 1;
        }
        int result = 0;
        for (; i < chars.length; i++) {
            if (chars[i] > '9' || chars[i] < '0') {
                break;
            }
            if (result > max || (result == max && chars[i] > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + (chars[i] - '0');
        }
        return sign * result;
    }

    public static int strToInt(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.trim().toCharArray();
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int sign = 1;
        int result = 0;
        int i = 0;
        if (chars[i] == '-') {
            sign = -1;
            i = 1;
        } else if (chars[i] == '+') {
            i = 1;
        }
        int max = Integer.MAX_VALUE / 10;

        for (; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                break;
            }
            if (result > max || (result == max && chars[i] > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            result = result * 10 + Integer.valueOf(chars[i] - '0');
        }
        return sign * result;
    }

    public static int strToInt2(String str) {
        char[] c = str.trim().toCharArray();
        if (c.length == 0) {
            return 0;
        }
        int res = 0;
        int bndry = Integer.MAX_VALUE / 10;
//        System.out.println("bndry = " + bndry);
        int i = 1;
        int sign = 1;
        if (c[0] == '-') {
            sign = -1;
        } else if (c[0] != '+') {
            i = 0;
        }
        for (int j = i; j < c.length; j++) {
            if (c[j] < '0' || c[j] > '9') {
                break;
            }
            if (res > bndry || res == bndry && c[j] > '7') {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }
}
