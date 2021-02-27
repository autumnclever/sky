package com.autumn.clever.leetcode.others;

/**
 * 给你一个 32 位的有符号整数 x ，返回 x 中每位上的数字反转后的结果。
 * <p>
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/25 下午10:27
 */
public class 整数反转 {
    public static void main(String[] args) {
        System.out.println(reverse(-123));
        System.out.println(reverse(123));
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Integer.MIN_VALUE);
        System.out.println(reverse(2147483648L));
        System.out.println(reverse(-2147483412));
    }

    public static int reverse(long x) {
        int temp = 0;
        int max = Integer.MAX_VALUE / 10;
        int min = Integer.MIN_VALUE / 10;
        while (x != 0) {
            int remainder = (int) (x % 10);
            if ((temp > max) || (temp == max && remainder > 7)
                    || temp < min || (temp == min && remainder > 8)) {
                return 0;
            }
            x /= 10;
            temp = temp * 10 + remainder;
        }
        return temp;
    }
}
