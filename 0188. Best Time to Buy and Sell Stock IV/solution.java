/*
 * Referring to other's Dynamic Programming solution
 *
 * dp[i, j] represents the max profit up until prices[j] using at most i transactions. 
 * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
 *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
 * dp[0, j] = 0; 0 transactions makes 0 profit
 * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
 */
class Solution {
    public int maxProfit(int k, int[] prices) {
        // https://www.youtube.com/watch?v=oDhu5uGq_ic
        if ((k == 0) || (prices == null) || (prices.length <= 0)) return 0;
        
        int n = prices.length;
        int profit = 0;
        // The transaction number is enough to cover every price descending
        if (k >= n/2) {
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i-1])
                    profit += prices[i] - prices[i-1];
            }
            return profit;
        }
        
        int[][] dp = new int[k+1][n];
        for (int i = 1; i < k+1; i++) {
            int localMax = dp[i-1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j-1], localMax + prices[j]);
                //System.out.println(dp[i][j]);
                localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
                //System.out.println("localMax = " + localMax);
            }
        }
        return dp[k][n-1];
    }
}
