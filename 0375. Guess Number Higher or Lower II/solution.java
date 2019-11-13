/**
 * Other's solution based on Dynamic Programming and Minimax rule
 *
 */
class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n+1][n+1];
        return getMinMaxCost(dp, 1, n);
    }
    
    private int getMinMaxCost(int[][] dp, int lower, int higher) {
        if (higher <= lower)
            return 0; // No cost
        
        // Return the cost if it has already been calculated
        if (dp[lower][higher] != 0)
            return dp[lower][higher];
        
        // Initialize the cost
        dp[lower][higher] = Integer.MAX_VALUE;
        
        // Calculate the possible cost when guessing between lower ~ higher
        for (int i = lower; i <= higher; i++) {
            // The possible maximum cost when answer is not i
            int tmpMaxCost = i + Math.max(getMinMaxCost(dp, lower, i-1), getMinMaxCost(dp, i+1, higher));
            // Minimize the maximum cost
            dp[lower][higher] = Math.min(dp[lower][higher], tmpMaxCost);
        }

        return dp[lower][higher];
    }
}
