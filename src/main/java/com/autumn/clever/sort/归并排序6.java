package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * 算法思想：
 * 从上到下依次拆开
 * 从小到上依次合并
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/10 下午12:03
 */
public class 归并排序6 {
    public static void main(String[] args) {
        int[] arr = {3, 9, 6, 2, 1, 8};
//        int[] arr = {3, 8};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 把数组从中间结点拆开
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void mergeSort(int[] arr, int left, int right) {
        // 找到数组中间的位置
        int mid = (left + right) / 2;
        if (left < right) {
            // 拆开左边
            mergeSort(arr, left, mid);
            // 拆开右边
            mergeSort(arr, mid + 1, right);
            // 拆好的数组，进排序
            sort(arr, left, mid, right);
        }
    }

    /**
     * 对 arr 合并
     *
     * @param arr   待合并数组
     * @param left  待合并数组最小下标
     * @param mid   待合并数组中间下标
     * @param right 待合并数组最大下标
     */
    public static void sort(int[] arr, int left, int mid, int right) {
        // 初始化临时数组，用于临时保存每次合并之后的数组
        int[] temp = new int[right - left + 1];
        // 左边的起始位置
        int i = left;
        // 右边的起始位置
        int j = mid + 1;
        // temp 数组的索引
        int k = 0;
        // 循环，看左边、右边谁比较小，就放到temp里面去
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 左边没有遍历完，就依次放到temp里面去
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 右边没有遍历完，就依次放到temp里面去
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 把temp排序好的，放到真实的arr对应的位置中
        for (int m = 0; m < temp.length; m++) {
            arr[left + m] = temp[m];
        }
    }
}
