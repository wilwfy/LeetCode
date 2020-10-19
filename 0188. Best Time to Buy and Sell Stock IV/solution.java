/*
 * Referring to other's Dynamic Programming solution
 *
 * dp[i, j] represents the max profit up until prices[j] using at most i transactions. 
 * dp[i, j] = max(dp[i, j-1], prices[j] - prices[jj] + dp[i-1, jj]) { jj in range of [0, j-1] }
 *          = max(dp[i, j-1], prices[j] + max(dp[i-1, jj] - prices[jj]))
 * dp[0, j] = 0; 0 transactions makes 0 profit
 * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
 *
 * Explanation:
 * For example, if j == 8, then amongst all jj == 1,2,...,7
 * The max profit could be one of the following:
 * dp[i-1][0] + prices[8] - prices[0]
 * dp[i-1][1] + prices[8] - prices[1]
 * dp[i-1][2] + prices[8] - prices[2]
 * ...
 * dp[i-1][6] + prices[8] - prices[6]
 * dp[i-1][7] + prices[8] - prices[7]
 * 
 * localMax is the max value amongst all:
 * dp[i-1][0] - prices[0]
 * dp[i-1][1] - prices[1]
 * dp[i-1][2] - prices[2]
 * ...
 * dp[i-1][6] - prices[6]
 * dp[i-1][7] - prices[7]
 * 
 * Then localMax + prices[8] is the max profit if we sell the stock at day 8.
 * Then we compare this result with the max profit of not selling the stock 
 * at day 8, take the max of the two.
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
            // To find the max profit for i th transaction on day j, we
            // need use the total profit already obtained in i-1 th transaction
            // on day j-1 to minus the cost of price on day j-1 as the 
            // start point. The higher start point value, the higher profit.
            // Here, we start from i-1 transaction on day j=0 for i transaction on day j=1.
            int localMax = dp[i-1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                // localMax always present the total profit already obtained
                // in prevous i-1 transaction on previous j-1 day with a cost
                // of price on previous j-1 day
                dp[i][j] = Math.max(dp[i][j-1], localMax + prices[j]);
                
                // The start point is higher, the profit to be found is higher
                localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
            }
        }
        return dp[k][n-1];
    }
}


/**
 * Other's solution of DP
 *
 * Explanation:
 * The general idea is DP, while having to add a "quickSolve" function to tackle some corner cases to avoid TLE.
 * DP: t(i,j) is the max profit for up to i transactions by time j (0<=i<=K, 0<=j<=T).
 */
class Solution {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);
        
        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax = -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }

    private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
}
