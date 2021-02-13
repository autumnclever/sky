package com.autumn.clever.剑指offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * 输入: [1,2,3,4,5]
 * 输出: True
 * 输入: [0,0,1,2,5]
 * 输出: True
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/12 下午1:11
 */
public class 剑指Offer61扑克牌中的顺子 {
    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 2, 6};
        System.out.println(isStraight2(nums));
    }

    public static boolean isStraight2(int[] nums) {
        if (nums == null || nums.length != 5) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int min = 14;
        for (int num : nums) {
            if (num == 0) {
                continue;
            }
            if (map.containsKey(num)) {
                return false;
            }
            max = Math.max(max, num);
            min = Math.min(min, num);
            map.put(num, 1);
        }
        return max - min < 5;
    }

    public static boolean isStraight(int[] nums) {
        if (nums == null || nums.length != 5) {
            return false;
        }
        Arrays.sort(nums);
        int zeroCount = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else if (nums[i] == nums[i + 1]) {
                return false;
            }
        }
        return nums[nums.length - 1] - nums[zeroCount] < 5;
    }
}
