package com.autumn.clever.leetcode.array;

/**
 * 题目描述
 * 给出两个有序的整数数组 A 和 B，请将数组 A 合并到数组 B 中，变成一个有序的数组
 * 注意：
 * 可以假设 A 数组有足够的空间存放 B 数组的元素，A 和 B 中初始的元素数目分别为 m 和 n
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/17 下午2:25
 */
public class 合并两个有序数组 {
    public static void main(String[] args) {
//        int[] A = {};
//        int[] B = {1};
//        merge(A, 0, B, 1);
//        System.out.println(Arrays.toString(A));
        int[] nums = new int[2];
        test(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    public static void test(int[] nums) {
        nums[0] = 1;
        nums[1] = 2;
    }


    public static void merge(int[] A, int m, int[] B, int n) {
        if (A == null || A.length == 0) {
            A = new int[B.length];
            for (int i = 0; i < B.length; i++) {
                A[i] = 1;
            }
            return;
        }
        if (B == null || B.length == 0) {
            return;
        }
        int[] arr = new int[A.length + B.length];
        int i = 0;
        int j = 0;
        int index = 0;
        while (i < A.length && j < B.length) {
            if (A[i] <= B[j]) {
                arr[index++] = A[i++];
            } else {
                arr[index++] = B[j++];
            }
        }
        while (i < A.length) {
            arr[index++] = A[i++];
        }
        while (j < B.length) {
            arr[index++] = B[j++];
        }
//        A = new int[A.length + B.length];
        for (int k = 0; k < arr.length; k++) {
            A[k] = arr[k];
        }
    }
}
