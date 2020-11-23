package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/19 19:04
 */
public class 插入排序3 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j = i;
            int target = array[j];
            while (j > 0 && target < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = target;
        }
    }
}
