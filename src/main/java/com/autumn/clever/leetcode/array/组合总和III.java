package com.autumn.clever.leetcode.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * <p>
 * 说明：
 * <p>
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combination-sum-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/25 下午10:01
 */
public class 组合总和III {
    public static void main(String[] args) {
        List<List<Integer>> arrays = combinationSum3(3, 7);
        for (List<Integer> array : arrays) {
            System.out.println(JSON.toJSONString(array));
        }
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> arrays = new ArrayList<>();
        if (n <= 0) {
            return arrays;
        }
        LinkedList<Integer> list = new LinkedList<>();
        help(k, n, 0, list, arrays);
        return arrays;
    }

    public static void help(int k, int n, int begin, LinkedList<Integer> list, List<List<Integer>> arrays) {
        if (k == 0 && n == 0) {
            arrays.add(new ArrayList<>(list));
            return;
        }

        for (int i = begin; i < 9; i++) {
            if ((k == 1 && !list.isEmpty() && list.getLast() >= (i + 1)) || list.contains(i + 1)) {
                break;
            }
            list.add(i + 1);
            help(k - 1, n - (i + 1), i + 1, list, arrays);
            list.removeLast();
        }
    }
}
