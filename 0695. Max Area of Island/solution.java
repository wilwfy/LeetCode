/**
 * My solution of DFS
 *
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n)
 */
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] visited = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j, 0, grid, visited));
            }
        }
        return max;
    }
    
    private int dfs(int row, int col, int area, int[][] grid, int[][]visited) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col] == 1)
            return 0;
        
        visited[row][col] = 1;
        if (grid[row][col] == 1) {
            area += 1;
            area += dfs(row-1, col, 0, grid, visited);
            area += dfs(row+1, col, 0, grid, visited);
            area += dfs(row, col-1, 0, grid, visited);
            area += dfs(row, col+1, 0, grid, visited);
        }
        return area;
    }
}
