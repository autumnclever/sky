package com.autumn.clever.leetcode.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 回溯算法，会超时
 * <p>
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/26 上午10:36
 */
public class 三数之和2 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(JSON.toJSONString(list));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> arrays = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return arrays;
        }
        Arrays.sort(nums);
        LinkedList<Integer> list = new LinkedList<>();
        help(nums, 0, 0, list, arrays);
        return arrays;
    }

    public static void help(int[] nums, int begin, int target, LinkedList<Integer> list, List<List<Integer>> arrays) {
        if (target == 0 && list.size() == 3) {
            arrays.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            if (target + nums[i] > 0) {
                break;
            }
            if (i > begin && nums[i - 1] == nums[i]) {
                continue;
            }
            list.add(nums[i]);
            help(nums, i + 1, target + nums[i], list, arrays);
            list.removeLast();
        }
    }
}
