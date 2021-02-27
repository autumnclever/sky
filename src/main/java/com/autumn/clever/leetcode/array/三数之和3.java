package com.autumn.clever.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
 * 注意：
 * <p>
 * 三元组（a、b、c）中的元素必须按非降序排列。（即a≤b≤c）
 * 解集中不能包含重复的三元组。
 * 例如，给定的数组 S = {-10 0 10 20 -10 -40},解集为(-10, 0, 10) (-10, -10, 20)
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/15 下午5:58
 */
public class 三数之和3 {
    public static void main(String[] args) {
        int[] num = {-10, 0, 10, 20, -10, -40};
        ArrayList<ArrayList<Integer>> lists = threeSum(num);
        for (ArrayList<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }

    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        if (num == null || num.length < 3) {
            return new ArrayList<>();
        }
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Arrays.sort(num);
        boolean[] used = new boolean[num.length];
        help(num, 0, 0, lists, list);
        return lists;
    }

    public static void help(int[] num, int sum, int begin, ArrayList<ArrayList<Integer>> lists, ArrayList<Integer> list) {
        if ((sum == 0 && list.size() == 3)) {
            lists.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i < num.length; i++) {
//            if (list.size() > 3) {
//                break;
//            }
            sum += num[i];
            list.add(num[i]);
            help(num, sum, i, lists, list);
            sum -= num[i];
            list.remove(list.size() - 1);
        }
    }
}
