package com.autumn.clever.剑指offer;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * <p>
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/6 下午6:03
 */
public class 剑指Offer56II数组中数字出现的次数II {
    public static void main(String[] args) {
        int[] nums = {9, 1, 7, 9, 7, 9, 7};
        System.out.println(singleNumber(nums));
    }

    public static int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int xoy = 0;
        for (int num : nums) {
            xoy ^= num;
        }
        for (int num : nums) {
            xoy ^= num;
        }
        return xoy;
    }
}