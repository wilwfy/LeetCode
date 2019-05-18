class Solution {
    public int minCostClimbingStairs(int[] cost) {
        if ((cost == null) || (cost.length == 0)) return 0;
        if (cost.length == 1) return cost[0];
        if (cost.length == 2) return Math.min(cost[0], cost[1]);
        int[] dp = new int[cost.length];
        // we could start from 0-th step or 1st step respectively
        dp[0] = cost[0];
        dp[1] = cost[1];
        // dp[i] denotes the minimum cost at the i-th step so far,
        // it could come from the i-1 th step or i-2 th step
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        return Math.min(dp[cost.length-1], dp[cost.length-2]);
    }
}


/*
 * Official Solution: Dynamic Programming
 *
 * Intuition: There is a clear recursion available: the final cost f[i] to climb the staircase from some
 * step i is f[i] = cost[i] + min(f[i+1], f[i+2]). This motivates dynamic programming.
 * Algorithm: Let's evaluate f backwards in order. That way, when we are deciding what f[i] will be, we've
 * already figured out f[i+1] and f[i+2].
 * We can do even better than that. At the i-th step, let f1, f2 be the old value of f[i+1], f[i+2], and
 * update them to be the new values f[i], f[i+1]. We keep these updated as we iterate through i backwards.
 * At the end, we want min(f1, f2).
 *
 * Time Complexity: O(N) where N is the length of cost.
 * Space Complexity: O(1), the space used by f1, f2.
 */
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int f1 = 0, f2 = 0;
        for (int i = cost.length - 1; i >= 0; --i) {
            int f0 = cost[i] + Math.min(f1, f2);
            f2 = f1;
            f1 = f0;
        }
        return Math.min(f1, f2);
    }
}
