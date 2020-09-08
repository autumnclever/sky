package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/6 15:15
 */
public class 把数组排成最小的数 {
    public static void main(String[] args) {
//        int[] nums = {824, 938, 1399, 5607, 6973, 5703, 9609, 4398, 8247};
        int[] num2 = {12, 121};
        String result = minNumber(num2);
        System.out.println(result);
    }


    public static String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (!isNeedChange(nums[i], nums[j])) {
                    // 如果后面的数字小，将两个数的位置进行调换
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            sb.append(nums[i]);
        }
        return sb.toString();
    }

    public static boolean isNeedChange(int a, int b) {
        long ab = Long.valueOf("" + a + b);
        long ba = Long.valueOf("" + b + a);
        return ba > ab;
    }

    public static boolean isAfterBigger(int before, int after) {
        // 获取第一个数的位数
        int beforeBit = String.valueOf(before).length();
        // 第二个数的位数
        int afterBit = String.valueOf(after).length();
        if (beforeBit == afterBit) {
            // 如果两个数位数相同，直接做比较
            return after >= before;
        } else if (afterBit > beforeBit) {
            // 如果后面数字位数大
            // 获取前面数字的个数位的数字
            int temp = fastPower(10, afterBit - 1);
            int afterMaxBit = after / temp;

            StringBuilder sb = new StringBuilder();
            sb.append(before);
            for (int i = 0; i < afterBit - beforeBit; i++) {
                // 用后面数字的最高位数值，在前面数字的末尾补齐相同位数的数字
                sb.append(afterMaxBit);
            }
            return after >= Integer.valueOf(sb.toString());
        } else {
            int temp = fastPower(10, beforeBit - 1);
            int beforeMaxBit = before / temp;
            StringBuilder sb = new StringBuilder();
            sb.append(after);
            for (int i = 0; i < beforeBit - afterBit; i++) {
                // 用前面数字的最高位数值，在后面数字的末尾补齐相同位数的数字
                sb.append(beforeMaxBit);
            }
            return Integer.valueOf(sb.toString()) >= before;
        }
    }

    public static int fastPower(int a, int b) {
        int ans = 1;
        int base = a;
        while (b != 0) {
            if ((b & 1) != 0) { //如果当前的次幂数最后一位(二进制数)不为0的话，那么我们将当前权值加入到最后答案里面去
                ans = ans * base;
            }
            //权值增加
            base = base * base;
            b >>= 1;
        }
        return ans;
    }
}
