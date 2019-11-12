package com.xiaozhou.dp;

class Solution122 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1)
            return 0;
        int min = prices[0];
        int profit = 0;
        for(int i = 0; i < prices.length;i++) {
            if(prices[i] <min) {
                min = prices[i];
            } else {
                profit += prices[i] - min;
                min = prices[i];
            }
        }
        return profit;
    }
}
