package com.autumn.clever.leetcode.array;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回 0。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/24 下午6:52
 */
public class 长度最小的子数组 {
    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLens2(7, nums));
    }

    public static int minSubArrayLens2(int s, int[] nums) {
        if (nums == null || nums.length == 0 || s == 0) {
            return 0;
        }
        int left = 0;
        int right = -1;
        // 初始化结果值，因为迭代中，不断要的是小的 length 值，所以这里
        int length = nums.length + 1;
        int sum = 0;

        // 循环的条件，用左边界来界定
        while (left < nums.length) {
            if (right < nums.length - 1 && sum < s) {
                // 注意：这里是累加的 right 的下一个索引位置上的值，所以要判断是否 < 数组-1
                // 如果滑动窗口内的数组和 < 目标值 -> 扩大滑动窗口范围
                sum += nums[++right];
            } else {
                // 如果滑动窗口内的数组和 >= 目标值 -> 缩小滑动窗口范围
                sum -= nums[left++];
            }
            if (sum >= s) {
                // 满足目标值要求，更新结果值，要小的
                length = (length < right - left + 1) ? length : right - left + 1;
            }
        }
        // 如果 length 还是初始值，说明不存在满足条件，返回 0
        return length == nums.length + 1 ? 0 : length;
    }

    public static int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0 || s == 0) {
            return 0;
        }
        int left = 0;
        int right = -1;
        int length = nums.length;
        int sum = 0;

        while (right < nums.length) {
            while (right < nums.length - 1 && sum < s) {
                sum += nums[++right];
            }
            while (left < right && sum >= s) {
                length = (length < right - left + 1) ? length : right - left + 1;
                sum -= nums[left++];
            }
        }
        return length == nums.length ? 0 : length;
    }
}
