class Solution {
    public int uniquePaths(int m, int n) {
        if ((m == 0) || (n == 0)) return 0;
        // dp[i][j] denotes the number of possible path to reach grid[i][j]
        int[][] dp = new int[m][n+1];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n+1; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[m-1][n];
    }
}
