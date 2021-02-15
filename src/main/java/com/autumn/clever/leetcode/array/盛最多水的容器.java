package com.autumn.clever.leetcode.array;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * <p>
 * 说明：你不能倾斜容器。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/26 上午11:29
 */
public class 盛最多水的容器 {
    public static void main(String[] args) {
//        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height = {3, 1, 2, 5, 2, 4};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        int max = 0;
        if (height == null || height.length == 0) {
            return max;
        }

        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            // 水槽的高度以矮的为准，如果遇到矮的那一个，缩小它所在的索引范围，力求找到一个比刚才大的
            max = height[left] < height[right] ? Math.max(max, (right - left) * height[left++]) :
                    Math.max(max, (right - left) * height[right--]);

        }
        return max;
    }
}
