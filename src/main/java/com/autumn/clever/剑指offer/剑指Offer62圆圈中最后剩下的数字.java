package com.autumn.clever.剑指offer;

/**
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/8 下午2:39
 */
public class 剑指Offer62圆圈中最后剩下的数字 {
    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3));
    }

    public static int lastRemaining(int n, int m) {
        int a = 0;
        for (int i = 1; i < n; i++) {
            a = (a + m) % (i + 1);
        }
        return a;
    }
}
