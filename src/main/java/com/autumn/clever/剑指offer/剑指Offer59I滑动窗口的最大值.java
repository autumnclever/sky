package com.autumn.clever.剑指offer;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/8 下午10:33
 */
public class 剑指Offer59I滑动窗口的最大值 {
    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] array = maxSlidingWindow3(nums, 3);
        System.out.println(Arrays.toString(array));
    }

    public static int[] maxSlidingWindow3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int[] array = new int[nums.length - k + 1];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!list.isEmpty() && list.peekLast() < nums[i]) {
                list.removeLast();
            }
            list.add(nums[i]);
        }
        array[0] = list.peek();
        for (int i = k; i < nums.length; i++) {
            if (nums[i - k] == list.peek()) {
                list.remove();
            }
            while (!list.isEmpty() && list.peekLast() < nums[i]) {
                list.removeLast();
            }
            list.add(nums[i]);
            array[i - k + 1] = list.peek();
        }
        return array;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        // 返回的数组长度应该是 nums.length-k+1
        int[] array = new int[nums.length - k + 1];
        LinkedList<Integer> list = new LinkedList<>();
        // 构建滑动窗口
        for (int i = 0; i < k; i++) {
            while (!list.isEmpty() && list.peekLast() < nums[i]) {
                // 队列的尾部 < 数组的值 -> 删除队尾数字
                list.removeLast();
            }
            list.add(nums[i]);
        }
        // 滑动窗口构建成功
        array[0] = list.peek();
        for (int i = k; i < nums.length; i++) {
            if (nums[i - k] == list.peek()) {
                list.remove();
            }
            while (!list.isEmpty() && list.peekLast() < nums[i]) {
                list.removeLast();
            }
            list.add(nums[i]);
            array[i - k] = list.peek();
        }
        return array;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        int[] arr = new int[nums.length - k + 1];
        LinkedList<Integer> list = new LinkedList<>();
        for (int j = 0; j < k; j++) {
            while (!list.isEmpty() && list.peekLast() < nums[j]) {
                list.removeLast();
            }
            list.add(nums[j]);
        }
        arr[0] = list.peek();
        for (int j = k; j < nums.length; j++) {
            if (list.peek() == nums[j - k]) {
                list.remove();
            }
            while (!list.isEmpty() && list.peekLast() < nums[j]) {
                list.removeLast();
            }
            list.addLast(nums[j]);
            arr[j - k + 1] = list.peek();
        }
        return arr;
    }
}
