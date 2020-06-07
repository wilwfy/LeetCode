/**
 * Other's solution of DP with a matrix space
 *
 * Time & Space: O(n*m). n is the length of coins, m is the value of amount.
 */
class Solution {
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1; // use for case of amount = 0 and coins = []
        
        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1; // for amount = 0, there is only 1 way that we use none of the coins
            for (int j = 1; j <= amount; j++) {
                // for the iteration i, we are considering the i-1 th coin which is coins[i-1]
                if (j >= coins[i-1])
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i-1]];
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        return dp[coins.length][amount];
    }
}


/**
 * Other's solution of DP with a vector space
 *
 * Explanation:  https://youtu.be/jaNZ83Q3QGc
 *
 * Time: O(n*m). n is the length of coins, m is the value of amount.
 * Space: O(m). m is the value of amount.
 */
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1; // use for case of amount = 0
        
        for (int i = 0; i < coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                // for the iteration i, we are considering the i th coin which is coins[i]
                if (j >= coins[i])
                    dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
}
