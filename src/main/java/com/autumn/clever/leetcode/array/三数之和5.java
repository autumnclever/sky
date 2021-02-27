package com.autumn.clever.leetcode.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/25 下午11:20
 */
public class 三数之和5 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(JSON.toJSONString(list));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        List<List<Integer>> array = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        help(nums, 0, 0, array, list);
        return array;
    }

    public static void help(int[] nums, int sum, int begin, List<List<Integer>> array, LinkedList<Integer> list) {
        if (list.size() == 3 && sum == 0) {
            array.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            if (sum + nums[i] > 0) {
                break;
            }
            if (i > begin && nums[i] == nums[i - 1]) {
                continue;
            }
            sum += nums[i];
            list.add(nums[i]);
            help(nums, sum, i + 1, array, list);
            list.removeLast();
            sum -= nums[i];
        }
    }
}
