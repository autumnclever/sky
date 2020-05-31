package com.autumn.clever.cattle;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 * https://www.nowcoder.com/questionTerminal/beb5aa231adc45b2a5dcc5b62c93f593
 *
 * @Author: zhangqiuying
 * @Date: 2020/5/31 21:16
 */
public class Cattle13 {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        reOrderArray(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void reOrderArray(int[] array) {
        if (array == null || array.length == 0 || array.length == 1) {
            return;
        }

        int oddCount = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 1) {
                oddCount++;
            }
        }

        int[] newArray = new int[array.length];
        int oddIndex = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & 1) == 1) {
                newArray[oddIndex++] = array[i];
            } else {
                newArray[oddCount++] = array[i];
            }
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = newArray[i];
        }
    }
}
