package com.autumn.clever.leetcode.array;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
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
 * @Date: 2021/1/24 下午2:58
 */
public class 三数之和 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(JSON.toJSONString(list));
        }
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 2; k++) {
            if (nums[k] > 0) {
                // 如果最小值都 > 0，不可能存在三个数字和为 0
                break;
            }
            if (k > 0 && nums[k - 1] == nums[k]) {
                // 如果[1, nums.length - 1]，k前后两个值相同，继续下一个循环
                continue;
            }
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];
                if (sum > 0) {
                    // 如果三个数的和 > 0 -> 应该获取一个更小的数字，注意要去重，遇到相同的值，继续往左边移
                    while (i < j && nums[j] == nums[--j]) {
                        continue;
                    }
                } else if (sum < 0) {
                    // 如果三个数的和 < 0 -> 应该获取一个更大的数字
                    while (i < j && nums[i] == nums[++i]) {
                        continue;
                    }
                } else {
                    list.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while (i < j && nums[j] == nums[--j]) {
                        continue;
                    }
                    while (i < j && nums[i] == nums[++i]) {
                        continue;
                    }
                }
            }
        }
        return list;
    }
}
