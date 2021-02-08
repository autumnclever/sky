package com.autumn.clever.leetcode.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/26 上午9:50
 */
public class 全排列 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> arrays = permute(nums);
        for (List<Integer> array : arrays) {
            System.out.println(JSON.toJSONString(array));
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> arrays = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return arrays;
        }

        LinkedList<Integer> list = new LinkedList<>();
        help(nums, list, arrays);
        return arrays;
    }

    public static void help(int[] nums, LinkedList<Integer> list, List<List<Integer>> arrays) {
        if (list.size() == nums.length) {
            arrays.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                // 在这里进行剪枝操作
                continue;
            }
            list.add(nums[i]);
            help(nums, list, arrays);
            list.removeLast();
        }
    }
}
