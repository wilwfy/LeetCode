/**
 * My solution of recursion (TLE - Time Limit Exceeded)
 *
 * Space: O(n * m)
 */
class Solution {
    int n;
    int m;
    int minSum = Integer.MAX_VALUE;
    int[][] g;
    public int minPathSum(int[][] grid) {
        g = grid;
        n = grid.length;
        if (n == 0) return 0;
        m = grid[0].length;
        helper(0, 0, 0); // i = 0, j = 0, curSum = 0
        return minSum;
    }
    
    public void helper(int i, int j, int curSum) {
        if (i >= n || j >= m) return; // out of bound of grid
        curSum += g[i][j];
        // Update the min path sum when the destination is reached
        if (i == n-1 && j == m-1) minSum = Math.min(curSum, minSum);
        // Otherwise, move rightwards or move downwards
        helper(i, j+1, curSum);
        helper(i+1, j, curSum);
    }
}


/**
 * My solution of Dynamic Programming
 *
 * Space: O(n * m)
 */
class Solution {
    public int minPathSum(int[][] grid) {
    	int n = grid.length; // row
        if (n == 0) return 0;
    	int m = grid[0].length; // column
        int[][] minSum = new int[n][m];
        minSum[0][0] = grid[0][0];
        for (int i = 1; i < n; i++)
            minSum[i][0] = minSum[i-1][0] + grid[i][0];
        for (int j = 1; j < m; j++)
            minSum[0][j] = minSum[0][j-1] + grid[0][j];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                minSum[i][j] = Math.min(minSum[i-1][j], minSum[i][j-1]) + grid[i][j];
            }
        }
        return minSum[n-1][m-1];
    }
}


/**
 * Other's solution of in-place Dynamic Programming
 *
 * Space: O(1). But the original data are modified
 */
class Solution {
    public int minPathSum(int[][] grid) {
    	int m = grid.length; // row
    	int n = grid[0].length; // column
    	for(int j = 1; j < n; j++) grid[0][j] = grid[0][j] + grid[0][j-1];
    	for(int i = 1; i < m; i++) grid[i][0] = grid[i][0] + grid[i-1][0];
    	for (int i = 1; i < m; i++) {
    		for (int j = 1; j < n; j++) {
    				grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
    		}
    	}
    	return grid[m - 1][n - 1];
    }
}


/**
 * Other's solution of Dynamic Programming with O(n) space
 *
 * Space: O(n)
 */
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];
        for (int j = 1; j < n; j++) {
            dp[j] = dp[j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[j] += grid[i][j];
                } else {
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j - 1]);
                }
            }
        }
        return dp[n - 1];
    }
}
