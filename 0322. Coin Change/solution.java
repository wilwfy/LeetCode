/**
 * Official solution of DP - Top down
 *
 * Time complexity : O(S*n). where S is the amount, n is denomination count. In the worst case the recursive tree of the algorithm has
 *                   height of S and the algorithm solves only S subproblems because it caches precalculated solutions in a table. Each
 *                   subproblem is computed with n iterations, one by coin denomination. Therefore there is O(S*n) time complexity.
 * Space complexity : O(S), where S is the amount to change We use extra space for the memoization table.
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) return 0;
        return coinChange(coins, amount, new int[amount]);
    }
    
    public int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return 0;
        if (count[rem - 1] != 0) return count[rem - 1];
        
        int min = Integer.MAX_VALUE;
        for (int coin: coins) {
            int res = coinChange(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = res + 1;
        }
        
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
}


/**
 * Official solution of DP - Bottom up
 *
 * Time complexity : O(S*n). On each step the algorithm finds the next F(i) in n iterations, where S1 ≤ i ≤ S. Therefore in total the
 *                   iterations are S*n.
 * Space complexity : O(S). We use extra space for the memoization table.
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) return 0;
        
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]]+1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
