package com.autumn.clever.leetcode.array;

import java.util.Arrays;

/**
 * 给定一个无序数组arr，找到数组中未出现的最小正整数
 * 例如arr = [-1, 2, 3, 4]。返回1
 * arr = [1, 2, 3, 4]。返回5
 * [要求]
 * 时间复杂度为O(n)O(n)O(n)，空间复杂度为O(1)O(1)O(1)
 * https://www.nowcoder.com/practice/8cc4f31432724b1f88201f7b721aa391?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/15 下午11:09
 */
public class 数组中出现的最小正数 {
    public static void main(String[] args) {
        int[] arr = {-1, 2, 3, 4, 9};
        System.out.println(minNumberdisappered(arr));
    }

    public static int minNumberdisappered(int[] arr) {
        Arrays.sort(arr);
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                continue;
            }
            if (arr[i] != count) {
                break;
            }
            count++;
        }
        return count;
    }
}
