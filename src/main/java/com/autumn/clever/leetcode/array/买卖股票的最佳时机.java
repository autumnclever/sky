package com.autumn.clever.leetcode.array;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/26 下午1:27
 */
public class 买卖股票的最佳时机 {
    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit2(prices));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            // 如果买卖股票的天数不足 2 天，不足够交易，肯定赚不到钱，返回 0
            return 0;
        }
        // 初始化二维数组，该数组的含义：第 i 天结束时，兜里剩余的钱
        // 一维：第 i 天
        // 二维：0：当天没有持有股票；1：当天持有股票
        int[][] dp = new int[prices.length][2];
        // 第 0 天结束时，没有持有股票，兜里剩余的钱没有任何变动
        dp[0][0] = 0;
        // 第 0 天结束时，持有股票，兜里的钱用来买股票了，花了 prices[0]
        dp[0][1] = -prices[0];

        // 第 0 天已经复制了，从第一天开始遍历
        for (int i = 1; i < prices.length; i++) {
            // 如果第 i 天结束，没有持有股票：
            // 第一种情况：昨天就没有持有股票；
            // 第二种情况：昨天持有股票，今天卖掉了，赚到今天股票的销售价格 prices[i]
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            // 如果第 i 天结束，持有股票：
            // 第一种情况：昨天本就持有股票，那今天结束时，今天没有进行交易，没花钱也没有收入，持有的钱和昨天结束时是一样的；
            // 第二种情况：昨天没有持有股票，今天买入，花了 prices[i]
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            // 如果买卖股票的天数不足 2 天，不足够交易，肯定赚不到钱，返回 0
            return 0;
        }

        int[] dp = new int[2];
        dp[0] = 0;
        dp[1] = -prices[0];

        for (int i = 0; i < prices.length; i++) {
            dp[0] = Math.max(dp[0], dp[1] + prices[i]);
            dp[1] = Math.max(dp[1], -prices[i]);
        }
        return dp[0];
    }
}
