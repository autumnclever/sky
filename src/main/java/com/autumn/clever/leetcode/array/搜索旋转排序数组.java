package com.autumn.clever.leetcode.array;

/**
 * 升序排列的整数数组 nums 在预先未知的某个点上进行了旋转（例如， [0,1,2,4,5,6,7] 经旋转后可能变为 [4,5,6,7,0,1,2] ）。
 * <p>
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/24 下午4:36
 */
public class 搜索旋转排序数组 {
    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums = {1};
        System.out.println(search(nums, 1));
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[left]) {
                // 如果中间的数比 left 上的数字大，说明 left~mid 的数字是排序好的
                if (target >= nums[left] && target < nums[mid]) {
                    // 如果 target 在 [left, mid) 内，把查找范围缩减到 [left, mid-1]，
                    right = mid - 1;
                } else {
                    // 不在，把查找范围缩减到 [mid+1, right]
                    left = mid + 1;
                }
            } else {
                // 如果不满足上两个 if 条件，说明 mid~right 的数字是排序好的
                if (target > nums[mid] && target <= nums[right]) {
                    // 如果 target 在 (mid, right] 内，把查找范围缩减到 [mid+1, right]
                    left = mid + 1;
                } else {
                    // 不在，把查找范围缩减到 [left, mid-1]
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
