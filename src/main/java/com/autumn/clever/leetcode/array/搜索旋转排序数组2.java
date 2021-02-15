package com.autumn.clever.leetcode.array;

/**
 * 给出一个转动过的有序数组，你事先不知道该数组转动了多少
 * (例如,0 1 2 4 5 6 7可能变为4 5 6 7 0 1 2).
 * 在数组中搜索给出的目标值，如果能在数组中找到，返回它的索引，否则返回-1。
 * 假设数组中不存在重复项。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/14 下午10:21
 */
public class 搜索旋转排序数组2 {
    public static void main(String[] args) {
//        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int[] nums2 = {3, 1};
        int[] nums3 = {258, 260, 265, 266, 268, 269, 271, 275, 276, 278, 280, 282, 287, 288, 289, 293, 2, 4, 5, 9, 16, 23, 24, 25, 26, 27, 28, 36, 37, 46, 47, 52, 55, 56, 60, 63, 67, 71, 74, 75, 76, 79, 80, 81, 82, 92, 97, 99, 103, 104, 106, 109, 111, 112, 117, 121, 125, 131, 133, 136, 141, 142, 143, 144, 154, 160, 161, 168, 169, 179, 187, 190, 201, 202, 204, 206, 208, 209, 211, 213, 218, 220, 222, 224, 225, 226, 229, 230, 231, 234, 240, 241, 242, 243, 244, 245, 247, 249, 252, 253, 254, 257};
        System.out.println(search3(nums2, 1));
    }

    public static int search3(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int left = 0;
        int right = A.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] >= A[left]) {
                if (target >= A[left] && target < A[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (target <= A[right] && target > A[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int search2(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int left = 0;
        int right = A.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target) {
                return mid;
            } else if (A[mid] > target) {
                if (A[mid] > A[left] && target >= A[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (A[mid] < A[right] && target <= A[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        int left = 0;
        int right = A.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (A[mid] == target) {
                return mid;
            } else if ((A[mid] > A[left] && A[mid] > target && target >= A[left])
                    || (A[mid] < A[right] && target > A[right])) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (left < A.length && A[left] == target) {
            return left;
        }
        return -1;
    }
}
