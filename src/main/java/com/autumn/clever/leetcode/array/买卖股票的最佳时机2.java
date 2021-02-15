package com.autumn.clever.leetcode.array;

/**
 * 假设你有一个数组，其中第 i\ i i 个元素是股票在第 i\ i i 天的价格。
 * 你有一次买入和卖出的机会。（只有买入了股票以后才能卖出）。请你设计一个算法来计算可以获得的最大收益。
 * https://www.nowcoder.com/practice/64b4262d4e6d4f6181cd45446a5821ec?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/14 下午9:59
 */
public class 买卖股票的最佳时机2 {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {1, 4, 2};
        System.out.println(maxProfit(prices2));
    }

    public static int maxProfit(int[] prices) {
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
}
