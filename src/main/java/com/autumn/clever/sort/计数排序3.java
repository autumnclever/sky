package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/19 20:27
 */
public class 计数排序3 {
    public static void main(String[] args) {

    }

    public static void countSort(int[] array) {
        int len = array.length;
        int max = array[0];
        int min = array[0];
        for (int i = 1; i < len; i++) {
            max = max > array[i] ? max : array[i];
            min = min < array[i] ? min : array[i];
        }

        int[] temp = new int[max + 1];
        for (int i = 0; i < len; i++) {
            temp[array[i]]++;
        }
        int k = 0;
        for (int i = min; i < temp.length; i++) {
            while (temp[i] > 0) {
                array[k++] = i;
                temp[i]--;
            }
        }
    }
}
