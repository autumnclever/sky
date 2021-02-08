package com.autumn.clever.leetcode.字符串;

/**
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/16 下午11:25
 */
public class 字符串相加 {
    public static void main(String[] args) {
        String result = addStrings2("1234", "45678");
        System.out.println(result);
    }

    public static String addStrings(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            return num2;
        }

        if (num2 == null || num2.length() == 0) {
            return num1;
        }

        int bit = 0;
        StringBuilder sb = new StringBuilder();
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;

        while (index1 >= 0 || index2 >= 0) {
            int sum = Integer.valueOf(num1.charAt(index1)) + Integer.valueOf(num2.charAt(index2)) + bit;
            if (sum >= 10) {
                sb.append(sum % 10);
                bit = sum / 10;
            } else {
                sb.append(sum);
                bit = 0;
            }
            index1--;
            index2--;
        }

        StringBuilder result = null;
        if (index1 > 0) {
            result = new StringBuilder(num1.substring(0, index1));
        } else if (index2 > 0) {
            result = new StringBuilder(num2.substring(0, index2));
        } else {
            result = new StringBuilder("0");
        }

        if (bit > 0) {
            int sum = Integer.valueOf(result.charAt(result.length() - 1)) + bit;
            result.deleteCharAt(result.length() - 1);
            result.append(sum);
        }
        return result.append(sb).toString();
    }

    public static String addStrings2(String num1, String num2) {
        if (num1 == null || num1.length() == 0) {
            return num2;
        }

        if (num2 == null || num2.length() == 0) {
            return num1;
        }

        int bit = 0;
        StringBuilder sb = new StringBuilder();
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;

        while (index1 >= 0 || index2 >= 0) {
            int a = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int b = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            int sum = a + b + bit;
            sb.append(sum % 10);
            bit = sum / 10;
            index1--;
            index2--;
        }

        if (bit > 0) {
            sb.append(bit);
        }
        return sb.reverse().toString();
    }
}
