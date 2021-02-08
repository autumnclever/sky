package com.autumn.clever.剑指offer;

/**
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/6 下午2:56
 */
public class 剑指Offer63股票的最大利润 {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit2(prices));
    }

    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int a = 0;
        int b = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            a = Math.max(a, b + prices[i]);
            b = Math.max(b, -prices[i]);
        }
        return a;
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], -prices[i]);
        }
        return dp[0];
    }
}
