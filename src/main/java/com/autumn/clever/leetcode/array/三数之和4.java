package com.autumn.clever.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/15 下午6:37
 */
public class 三数之和4 {
    public static void main(String[] args) {
        int[] num = {-10, 0, 10, 20, -10, -40};
        ArrayList<ArrayList<Integer>> lists = threeSum(num);
        for (ArrayList<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }

    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        if (num == null || num.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < num.length - 2; i++) {
            if (num[i] > 0) {
                break;
            }
            if (i > 0 && num[i - 1] == num[i]) {
                continue;
            }
            int left = i + 1;
            int right = num.length - 1;
            while (left < right) {
                int sum = num[i] + num[left] + num[right];
                if (sum == 0) {
                    lists.add(new ArrayList<>(Arrays.asList(num[i], num[left], num[right])));
                    while (left < right && num[right] == num[--right]) {
                        continue;
                    }
                    while (left < right && num[left] == num[++left]) {
                        continue;
                    }
                } else if (sum > 0) {
                    while (left < right && num[left] == num[++left]) {
                        continue;
                    }
                } else {
                    while (left < right && num[right] == num[--right]) {
                        continue;
                    }
                }
            }
        }
        return lists;
    }
}
