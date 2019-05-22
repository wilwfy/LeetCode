class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if ((m == 0) || (n == 0)) return 0;
        // dp[i][j] denotes the number of possible path to reach grid[i][j]
        int[][] dp = new int[m][n];
        // Initialize the top row of grids
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1)
                break;
            else
                dp[0][j] = 1;
        }
        // Initialize the most left column of grids
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1)
                break;
            else
                dp[i][0] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) continue;
                if ((obstacleGrid[i][j-1] == 1) && (obstacleGrid[i-1][j] == 1)) {
                    continue;
                } else if (obstacleGrid[i][j-1] == 1) {
                    dp[i][j] = dp[i-1][j];
                } else if (obstacleGrid[i-1][j] == 1) {
                    dp[i][j] = dp[i][j-1];
                } else {
                    dp[i][j] = dp[i][j-1] + dp[i-1][j];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
