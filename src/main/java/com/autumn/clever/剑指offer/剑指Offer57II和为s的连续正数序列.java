package com.autumn.clever.剑指offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/6 上午11:12
 */
public class 剑指Offer57II和为s的连续正数序列 {
    public static void main(String[] args) {
        int[][] arrays = findContinuousSequence2(15);
        for (int i = 0; i < arrays.length; i++) {
            System.out.println(Arrays.toString(arrays[i]));
        }
    }

    public static int[][] findContinuousSequence2(int target) {
        if (target <= 2) {
            return null;
        }
        int mid = target / 2 + 1;
        int left = 1;
        int right = 2;
        int sum = 3;
        List<int[]> lists = new ArrayList<>();
        while (left < right && right <= mid) {
            if (sum == target) {
                int[] arr = new int[right - left + 1];
                int index = 0;
                for (int i = left; i <= right; i++) {
                    arr[index++] = i;
                }
                lists.add(arr);
            }
            if (sum < target) {
                right++;
                sum += right;
            } else if (sum >= target) {
                sum -= left;
                left++;
            }
        }
        return lists.toArray(new int[0][]);
    }

    public static int[][] findContinuousSequence(int target) {
        if (target <= 2) {
            return null;
        }
        int mid = target / 2 + 1;
        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 1; i < mid; i++) {
            int temp = 0;
            List<Integer> list = new ArrayList<>();
            for (int j = i; j <= mid; j++) {
                temp += j;
                list.add(j);
                if (temp == target) {
                    lists.add(list);
                    break;
                } else if (temp > target) {
                    break;
                }
            }
        }
        int[][] arrs = new int[lists.size()][];
        if (lists != null && lists.size() != 0) {
            for (int i = 0; i < lists.size(); i++) {
                List<Integer> list = lists.get(i);
                int[] arr = new int[list.size()];
                for (int j = 0; j < list.size(); j++) {
                    arr[j] = list.get(j);
                }
                arrs[i] = arr;
            }
        }
        return arrs;
    }
}
