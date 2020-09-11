package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * 插入排序：
 * 插入排序的思想打牌的人肯定很容易理解，就是见缝插针。
 * 首先就默认数组中的第一个数的位置是正确的，即已经排序。
 * 然后取下一个数，与已经排序的数按从后向前的顺序依次比较， 如果该数比当前位置排好序的数小，则将排好序的数的位置向后移一位。
 * 重复上一步骤，直到找到合适的位置。 找到位置后就结束比较的循环，将该数放到相应的位置。
 *
 * @Author: zhangqiuying
 * @Date: 2020/9/8 21:39
 */
public class 插入排序 {
    public static void main(String[] args) {
        int[] arr = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    exchange(array, j, j - 1);
                }
            }
        }
    }

    public static void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
