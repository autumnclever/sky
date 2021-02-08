package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/28 下午4:53
 */
public class 快速排序10 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int index = getIndex(array, left, right);
            quickSort(array, left, index - 1);
            quickSort(array, index + 1, right);
        }
    }

    public static int getIndex(int[] array, int left, int right) {
        int temp = array[left];
        while (left < right) {
            while (left < right && array[right] > temp) {
                right--;
            }
            if (left < right) {
                array[left++] = array[right];
            }
            while (left < right && array[left] < temp) {
                left++;
            }
            if (left < right) {
                array[right--] = array[left];
            }
        }
        array[left] = temp;
        return left;
    }
}
