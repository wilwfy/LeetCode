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


