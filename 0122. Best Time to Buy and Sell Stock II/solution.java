class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int profit = 0;
        int low = prices[0], high = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] >= prices[i-1]) {
                high = prices[i];
            } else if (prices[i] < prices[i-1]) {
                profit += high - low;
                low = high = prices[i];
            }
        }
        if (high > low)
            profit += high - low;
        return profit;
    }
}


/*
 * Official solution: Simple One Pass
 *
 * we can observe that the total profit is equal to the difference 
 * between the heights of the consecutive peak and valley.
 *
 * Time complexity : O(n). Single pass.
 * Space complexity : O(1). Constant space required.
 */
class Solution {
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxprofit += prices[i] - prices[i - 1];
        }
        return maxprofit;
    }
}
