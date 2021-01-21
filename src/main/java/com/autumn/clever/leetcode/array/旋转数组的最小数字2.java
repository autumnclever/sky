package com.autumn.clever.leetcode.array;

/**
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/17 上午8:52
 */
public class 旋转数组的最小数字2 {
    public static void main(String[] args) {
        int[] array = {3, 4, 5, 1, 2};
        System.out.println(minArray(array));
    }

    public static int minArray(int[] numbers) {
        int begin = 0;
        int end = numbers.length - 1;
        while (begin < end) {
            int mid = (begin + end) / 2;
            if (numbers[mid] < numbers[end]) {
                // 如果 mid 索引上的值比 end 小，最小值在 [begin, mid] 范围内
                end = mid;
            } else if (numbers[mid] > numbers[end]) {
                // 如果 mid 索引上的值比 end 上的值大，mid 肯定不是最小的，最小值在 [mid + 1, end] 范围内
                begin = mid + 1;
            } else {
                // 如果两个值相等，直接把 end 缩小一下
                end--;
            }
        }
        return numbers[end];
    }
}
