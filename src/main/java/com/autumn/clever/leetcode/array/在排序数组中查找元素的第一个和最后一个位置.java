package com.autumn.clever.leetcode.array;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/17 下午5:07
 */
public class 在排序数组中查找元素的第一个和最后一个位置 {
    public static void main(String[] args) {
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int[] nums3 = {2, 2};
//        System.out.println(JSON.toJSONString(searchRange(nums1, 8)));
//        System.out.println(JSON.toJSONString(searchRange(nums2, 6)));
        System.out.println(JSON.toJSONString(searchRange(nums3, 3)));
    }

    public static int[] searchRange(int[] nums, int target) {
        int begin = binaryLeftSearch(nums, target);
        int end = binaryRightSearch(nums, target);
        return new int[]{begin, end};
    }

    /**
     * 有序数组 nums 中找到第一个 target 的索引位置，不存在返回 -1
     */
    public static int binaryLeftSearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        // 如果 left == nums.length -> target > nums 中的所有值，即不存在
        // 如果 nums[left] != target -> 找的 left 上的值不是 target，即不存在
        if (left == nums.length || nums[left] != target) {
            return -1;
        }
        return left;
    }

    /**
     * 有序数组 nums 中找到第最后一个 target 的索引位置，不存在返回 -1
     */
    public static int binaryRightSearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        // 如果 left = 0 -> nums 中没有大于 target 的数字
        // 如果 left 前面那个数字不是 target，说明 nums 中不存在 target
        if (left == 0 || nums[left - 1] != target) {
            return -1;
        }
        return left - 1;
    }
}
