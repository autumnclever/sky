package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/7 22:34
 */
public class 堆排序2 {
    public static void main(String[] args) {
        int[] arr = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] array) {
        if (array == null) {
            return;
        }
        // 获取数组的长度
        int len = array.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            // 从第一个非叶子节点开始
            adjustHeap(array, i, len);
        }
        for (int i = len - 1; i > 0; i--) {
            // 0位置永远都是最大的值，把它放在最后
            exchange(array, 0, i);
            // 调整剩下的位置
            adjustHeap(array, 0, i);
        }
    }

    public static void adjustHeap(int[] array, int i, int len) {
        // 取非叶子节点的值
        int temp = array[i];
        // 2*i+1是非叶子节点的左孩子节点，2*i+1+1是非叶子节点的右孩子
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
            if (k + 1 < len && array[k] < array[k + 1]) {
                // 时刻判断节点是否超出了数组的总长度 && 左孩子的值 < 右孩子的值
                k++;
            }
            if (temp < array[k]) {
                // 如果根节点的值比左右孩子中最大值要小 -> 把最大值赋值给根节点
                array[i] = array[k];
                // 再去判断最大值，也就是k所在的那个节点的左右子孩子是否有大过自己的
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    /**
     * 交换i和j位置的数组的值
     *
     * @param array
     * @param i
     * @param j
     */
    private static void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
