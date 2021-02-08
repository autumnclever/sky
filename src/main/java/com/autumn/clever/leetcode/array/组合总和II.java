package com.autumn.clever.leetcode.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的每个数字在每个组合中只能使用一次。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/25 下午5:30
 */
public class 组合总和II {
    public static void main(String[] args) {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> arrays = combinationSum2(candidates, 8);
        for (List<Integer> array : arrays) {
            System.out.println(JSON.toJSONString(array));
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> arrays = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return arrays;
        }
        Arrays.sort(candidates);
        LinkedList<Integer> list = new LinkedList<>();
        help(candidates, target, 0, list, arrays);
        return arrays;
    }

    public static void help(int[] candidates, int target, int begin, LinkedList<Integer> list, List<List<Integer>> arrays) {
        if (target == 0) {
            arrays.add(new ArrayList<>(list));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > begin && candidates[i - 1] == candidates[i]) {
                continue;
            }
            list.add(candidates[i]);
            help(candidates, target - candidates[i], i + 1, list, arrays);
            list.removeLast();
        }
    }
}
