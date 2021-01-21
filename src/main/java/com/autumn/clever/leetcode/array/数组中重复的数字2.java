package com.autumn.clever.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 找出数组中重复的数字。
 * <p>
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/17 上午9:56
 */
public class 数组中重复的数字2 {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber2(array));
    }

    public static int findRepeatNumber(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                return nums[i];
            }
            list.add(nums[i]);
        }
        return 0;
    }

    /**
     * 一个萝卜一个坑，将值放到对应的 index 上，让索引和索引位置上的值相等
     *
     * @param nums
     * @return
     */
    public static int findRepeatNumber2(int[] nums) {
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                // 索引上的值与索引本身不同时
                if (nums[i] == nums[nums[i]]) {
                    // 在索引 num[i] 位置上已经放了一个 num[i] 了，此时重复，直接返回
                    return nums[i];
                }
                // 调整索引和索引位置上的值，把该值放在对应的索引位置上
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}
