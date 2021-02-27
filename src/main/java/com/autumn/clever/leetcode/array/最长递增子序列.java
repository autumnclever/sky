package com.autumn.clever.leetcode.array;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/17 下午6:08
 */
public class 最长递增子序列 {
    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 3, 6, 4, 8, 9, 7};
//        System.out.println(lengthOfLIS4(nums));
        System.out.println(Arrays.toString(lengthOfLIS4(nums)));
        System.out.println(Arrays.toString(lengthOfLIS5(nums)));
    }

    public static int[] lengthOfLIS5(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        // 假设索引为 index，tails[index] 表示，长度为 index+1 的子序列，且其以 tails[index] 结尾
        int[] tails = new int[nums.length];
        int index = 0;
        for (int num : nums) {
            int left = 0;
            int right = index;
            while (left < right) {
                // 这里用二分查找左侧边界，即
                // 在 tails 找到：第一个大于等于自己的那个数字
                // 如果[left, right)中存在 num -> left 刚好是 num 在 tail 中的索引值
                // 如果[left, right)中不存在 num -> left 刚好是第一个大于 num 的值在 tail 中的索引值
                int mid = (left + right) / 2;
                if (tails[mid] >= num) {
                    // 当 num 和 tails[mid] 的值相等时，
                    // 与 tails 中，小过自己的那些数一样对待，不用去理会
                    right = mid;
                } else {
                    // 当 num > tails[mid] 上的数字时，
                    // 将 left 往左收缩
                    left = mid + 1;
                }
            }
            // 保证 tails 数组是递增的，并且当在 tails 找到刚好比自己大一丢丢的值时候，把它换掉。
            // 或者 tails 中所有的数字都小于 num，此时 left 刚好等于 index+1，即在 tails 中新添加一个元素。
            // 原因：3 和 5，3 找到一个大过它的数字比 5 找到的概率要大。
            // 所以让 tails 数组里面的数字尽可能的小。
            tails[left] = num;
            if (index == right) {
                // 如果 right 的值没有改变，说明 tails 中的所有数字都小过自己 -> 扩容 tails
                index++;
            }
        }
        return tails;
    }

    public static int[] lengthOfLIS4(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
        int[] tails = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            int i = 0;
            int j = res;
            while (i < j) {
                int mid = (i + j) / 2;
                if (num > tails[mid]) {
                    i = mid + 1;
                } else {
                    j = mid;
                }
            }
            tails[i] = num;
            if (res == j) {
                res++;
            }
        }
//        return res;
        return tails;
    }

    public static int lengthOfLIS3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    public static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int j = i - 1;
            for (; j >= 0; j--) {
                if (nums[i] >= nums[j]) {
                    break;
                }
            }
            if (j < 0) {
                dp[i] = 1;
            } else {
                dp[i] = nums[i] == nums[j] ? dp[j] : dp[j] + 1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            int index = getIndex(nums, 0, i, nums[i]);
            if (nums[index] == nums[i]) {
                dp[i] = dp[index];
            } else {
                dp[i] = dp[index] + 1;
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public static int getIndex(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
