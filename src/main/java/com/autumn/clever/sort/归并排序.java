package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/9 21:10
 */
public class 归并排序 {
    public static void main(String[] args) {
        int[] arr = {3, 9, 6, 2, 1, 8};
//        int[] arr = {3, 8};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 递归拆分
     *
     * @param arr   待拆分数组
     * @param left  待拆分数组最小下标
     * @param right 待拆分数组最大下标
     */
    public static void mergeSort(int[] arr, int left, int right) {
        // 中间下标
        int mid = (left + right) / 2;
        if (left < right) {
            // 递归拆分左边
            mergeSort(arr, left, mid);
            // 递归拆分右边
            mergeSort(arr, mid + 1, right);
            // 合并左右
            sort(arr, left, mid, right);
        }
    }

    /**
     * 合并两个有序子序列
     *
     * @param arr   待合并数组
     * @param left  待合并数组最小下标
     * @param mid   待合并数组中间下标
     * @param right 待合并数组最大下标
     */
    public static void sort(int[] arr, int left, int mid, int right) {
        // 临时数组，用来保存每次合并之后的结果
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        // 临时数组的初始下标
        int k = 0;
        //这个 while 循环能够初步筛选出待合并的两个子序列中的较小数
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        // 将左边序列中剩余的数放入临时数组
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        // 将右边序列中剩余的数放入临时数组
        while (j <= right) {
            temp[k++] = arr[j++];
        }
        // 将临时数组中的元素位置对应到真真实的数组中
        for (int m = 0; m < temp.length; m++) {
            arr[m + left] = temp[m];
        }
    }
}
