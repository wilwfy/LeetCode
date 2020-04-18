/**
 * My solution of recursion (TLE - Time Limit Exceeded)
 *
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
