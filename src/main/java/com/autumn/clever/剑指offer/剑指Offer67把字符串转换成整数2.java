package com.autumn.clever.剑指offer;

/**
 * 实现函数 atoi 。函数的功能为将字符串转化为整数
 * 提示：仔细思考所有可能的输入情况。这个问题没有给出输入的限制，你需要自己考虑所有可能的情况。
 * https://www.nowcoder.com/practice/44d8c152c38f43a1b10e168018dcc13f?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/15 下午7:35
 */
public class 剑指Offer67把字符串转换成整数2 {
    public static void main(String[] args) {
        System.out.println(atoi("words and 987"));
        System.out.println(atoi(" "));
        System.out.println(atoi("-91283472332"));
        System.out.println(atoi("words and 987"));
        System.out.println(atoi(" "));
        System.out.println(atoi("-91283472332"));
    }

    public static int atoi(String str) {
        if (str == null || str.length() == 0 || str == "0") {
            return 0;
        }
        int bit = 1;
        int i = 0;
        int max = Integer.MAX_VALUE / 10;
        if (str.charAt(0) == '-') {
            i++;
            bit = -1;
        } else if (str.charAt(0) == '+') {
            i++;
        }
        int num = 0;
        for (; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return 0;
            }
            int a = str.charAt(i) - '0';
            if (num > max || (num == max && a > 7)) {
                return bit > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            num = num * 10 + a;
        }
        return bit * num;
    }
}
