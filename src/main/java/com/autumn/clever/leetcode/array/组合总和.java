package com.autumn.clever.leetcode.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * <p>
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：
 * <p>
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/25 上午10:15
 */
public class 组合总和 {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        List<List<Integer>> arrays = combinationSum2(candidates, 7);
        for (List<Integer> array : arrays) {
            System.out.println(JSON.toJSONString(array));
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> arrays = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return arrays;
        }

        LinkedList<Integer> list = new LinkedList<>();
        help(candidates, target, 0, list, arrays);
        return arrays;
    }

    /**
     * 回溯算法辅助函数
     *
     * @param candidates 原数组
     * @param target     目标值
     * @param begin      开始遍历的起始位置，这里为避免重复，每次只从当前结点开始遍历后续的结点，排在当前结点前面的结点就不管了
     * @param list       一次深度递归途径的路径元素记录，注意，因为 java 语言的值传递，再把当前遍历结果添加到 arrays 中时，需要深拷贝
     * @param arrays     所有满足条件的结果集合
     */
    public static void help(int[] candidates, int target, int begin, LinkedList<Integer> list, List<List<Integer>> arrays) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            // 到达叶子满足，并且满足加和为 target -> 深拷贝，将集合添加到返回的结果中
            arrays.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            // 这里遍历的起始位置，从当前的结点开始，往后遍历，避免重复
            list.add(candidates[i]);
            // 正式递归前 -> 这里可以通过打印查看递归前(深度遍历)的遍历情况
            help(candidates, target - candidates[i], i, list, arrays);
            // 状态重置
            list.removeLast();
            // 递归结束后 -> 这里可以通过打印查看递归后(返回上一层)的遍历情况
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> arrays = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return arrays;
        }

        LinkedList<Integer> list = new LinkedList<>();
        // 先进行排序，为剪枝做准备
        Arrays.sort(candidates);
        help2(candidates, target, 0, list, arrays);
        return arrays;
    }

    public static void help2(int[] candidates, int target, int begin, LinkedList<Integer> list, List<List<Integer>> arrays) {
        if (target == 0) {
            // 这里只处理等于 0 的情况即可，小于 0 的情况，在 for 循环当中处理
            arrays.add(new ArrayList<>(list));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (target - candidates[i] < 0) {
                // 剪枝工作：不符合条件的结点，直接舍弃以及后续对应的结点
                // 因为数组提前进行了排序，如果计算到当前 target 已经小于 0 了，
                // 那后面肯定不会再满足等于 0 的情况了，所以直接 break 即可
                break;
            }
            list.add(candidates[i]);
            help2(candidates, target - candidates[i], i, list, arrays);
            list.removeLast();
        }
    }
}
