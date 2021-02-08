package com.autumn.clever.leetcode.array;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * <p>
 * <p>
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/30 下午7:30
 */
public class 买卖股票的最佳时机III {
    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(maxProfit3(prices));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[][][] dp = new int[prices.length][3][2];
        dp[0][0][0] = 0;
        dp[0][1][1] = -prices[0];
        // 在这之前交易过一次，可能赔了很多
        dp[0][2][1] = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {
            dp[i][1][0] = Math.max(dp[i - 1][1][0], dp[i - 1][1][1] + prices[i]);
            dp[i][1][1] = Math.max(dp[i - 1][1][1], -prices[i]);
            dp[i][2][0] = Math.max(dp[i - 1][2][0], dp[i - 1][2][1] + prices[i]);
            dp[i][2][1] = Math.max(dp[i - 1][2][1], dp[i - 1][1][0] - prices[i]);
        }

        return Math.max(dp[prices.length - 1][1][0], dp[prices.length - 1][2][0]);
    }

    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[][][] dp = new int[2][3][2];
        dp[0][0][0] = 0;
        dp[0][1][1] = -prices[0];
        dp[0][2][1] = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {
            // 滚动数组，此处的逻辑只依赖前一天，一个用于存储前一天的情况，一个用于存储今天的情况，所以对 2 取余
            dp[i % 2][1][0] = Math.max(dp[(i - 1) % 2][1][0], dp[(i - 1) % 2][1][1] + prices[i]);
            dp[i % 2][1][1] = Math.max(dp[(i - 1) % 2][1][1], -prices[i]);
            dp[i % 2][2][0] = Math.max(dp[(i - 1) % 2][2][0], dp[(i - 1) % 2][2][1] + prices[i]);
            dp[i % 2][2][1] = Math.max(dp[(i - 1) % 2][2][1], dp[(i - 1) % 2][1][0] - prices[i]);
        }

        return Math.max(dp[(prices.length - 1) % 2][1][0], dp[(prices.length - 1) % 2][2][0]);
    }

    public static int maxProfit3(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int[][] dp = new int[3][2];
        dp[0][0] = 0;
        dp[1][1] = -prices[0];
        dp[2][1] = Integer.MIN_VALUE;

        for (int i = 1; i < prices.length; i++) {
            // 状态方程的变更和天数无关，可以直接去掉一维数组，天数仅仅用于控制循环的次数
            dp[1][0] = Math.max(dp[1][0], dp[1][1] + prices[i]);
            dp[1][1] = Math.max(dp[1][1], -prices[i]);
            dp[2][0] = Math.max(dp[2][0], dp[2][1] + prices[i]);
            dp[2][1] = Math.max(dp[2][1], dp[1][0] - prices[i]);
        }

        return Math.max(dp[1][0], dp[2][0]);
    }
}
