package com.autumn.clever.leetcode.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/25 下午11:08
 */
public class 全排列2 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> arrays = permute(nums);
        for (List<Integer> array : arrays) {
            System.out.println(JSON.toJSONString(array));
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> array = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        help(nums, used, array, list);
        return array;
    }

    public static void help(int[] nums, boolean[] used, List<List<Integer>> array, LinkedList<Integer> list) {
        if (list.size() == nums.length) {
            array.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            list.addLast(nums[i]);
            help(nums, used, array, list);
            list.removeLast();
            used[i] = false;
        }
    }
}
