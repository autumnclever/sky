package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/17 下午3:31
 */
public class 二分查找 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 6};
        System.out.println("array");
        System.out.println(binarySearch(array, 4));
        System.out.println(binaryLeftSearch(array, 5));
        System.out.println(binaryRightSearch(array, 4));
        int[] array2 = {1, 1, 1, 1, 2, 2, 2, 3, 6, 7, 8};
        System.out.println("array2");
        System.out.println(binarySearch(array2, 7));
        System.out.println(binaryLeftSearch(array2, 7));
        System.out.println(binaryRightSearch(array2, 7));
        int[] array3 = {1, 1, 2, 2, 2, 3, 9, 10};
        System.out.println("array3");
        System.out.println(binarySearch(array3, 2));
        System.out.println(binaryLeftSearch(array3, 2));
        System.out.println(binaryRightSearch(array3, 3));
    }

    /**
     * 排序数组中查找到 target
     *
     * @param nums   有序数组，可能重复
     * @param target 目标数
     * @return
     */
    static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        // 查找的区间范围左边索引
        int left = 0;
        // 查找的区间范围右边索引
        int right = nums.length - 1;
        // 查找的区间范围是[left, right] -> 左彼右闭区间
        // 循环的终止条件是 left > right
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                // 当前值 > 目标值 -> 目标值在 [left, mid-1] 中
                // 此处因为查找的区间范围是[left, right]，所以 mid 上的值已经查看过了，所以 right = mid-1
                right = mid - 1;
            } else if (nums[mid] < target) {
                // 当前值 < 目标值 -> 目标值在 [mid+1, right] 中
                // 同理因为查找的区间范围是[left, right]，所以 mid 上的值已经查看过了，所以 left = mid+1
                left = mid + 1;
            }
        }
        // 当 left > right 时，还未找到即返回 -1
        return -1;
    }


    /**
     * 寻找左侧边界的二分搜索
     * 1.当 nums 中包含 target 时：
     * 左侧边界：在 nums 中第一个是 target 的索引值，即 nums 中小于等于 target 的数字个数
     * 2.当 nums 中不包含 target 时：
     * 返回的是 nums 第一个大于 target 的索引，即 nums 中小于 target 的数字个数，
     * 注意，这种情况下可能会出现数组越界的情况，即 target 可能大过于 nums 中所有的数，
     * 此时得到的 left = nums.length
     * 当 target < nums 中所有数字时，返回的是 left=0
     *
     * @param nums
     * @param target
     * @return
     */
    static int binaryLeftSearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] >= target) {
                // 当在 nums 中找到 target 时，不要停止，缩小查询范围，
                // 因为是要到找到左侧边界，将 right 往左边调整
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 寻找右侧边界的二分搜索：
     * 不管 nums 是否包含 target，返回的是：
     * 在 nums 中，第一个大于 target 的索引值，即 nums 中 <= target 的数字个数；
     * （当 nums 中不包含 target 时：此方法效果等同于 binaryLeftSearch）
     * 注意：当 target 小于 nums 中所有的数时，left = 0
     *
     * @param nums
     * @param target
     * @return
     */
    static int binaryRightSearch(int[] nums, int target) {
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
        return left;
    }
}
