/**
 * Other's solution of brute force Backstracking
 *
 * Explanation
 * First find out where the start and the end is.
 * Also We need to know the number of empty cells.
 * 
 * We we try to explore a cell,
 * it will change 0 to -2 and do a dfs in 4 direction.
 * 
 * If we hit the target and pass all empty cells, increment the result.
 * 
 * 
 * Complexity
 * Time complexity is as good as dp,
 * but it take less space and easier to implement.
 *
 * Time Complexity: O(4^(R∗C)), where R,C are the number of rows and columns in the grid. (We can find tighter bounds, but such a bound is beyond the scope of this article.)
 * Space Complexity: O(R∗C).
 */
class Solution {
    int res = 0, sx, sy; // source x, y
    int empty = 1; // the start position is counted as empty as well
    public int uniquePathsIII(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) empty++;
                else if (grid[i][j] == 1) {
                    sx = i;
                    sy = j;
                }
            }
        }
        
        dfs(grid, sx, sy);
        return res;
    }
    
    public void dfs(int[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] < 0)
            return;
        
        if (grid[x][y] == 2) {
            if (empty == 0) res++;
            return;
        }
        
        grid[x][y] = -2; // mark grid[x][y] as being visited
        empty--;
        dfs(grid, x + 1, y);
        dfs(grid, x - 1, y);
        dfs(grid, x, y + 1);
        dfs(grid, x, y - 1);
        grid[x][y] = 0; // resume the original value of grid[x][y]
        empty++;
    }
}
