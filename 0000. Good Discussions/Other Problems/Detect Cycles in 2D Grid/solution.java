/**
 * My solution of DFS
 *
 * Time: O(m * n)
 * Space: O(m * n)
 */
class Solution {
    public boolean containsCycle(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;
                if (dfs(grid, i, j, visited, i, j))
                    return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] grid, int row, int col, boolean[][] visited, int preRow, int preCol) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != grid[preRow][preCol])
            return false;
        
        if (visited[row][col]) {
            //System.out.println("Ok =========== ");
            //System.out.println("preRow = " + preRow + ", preCol = " + preCol);
            //System.out.println("row = " + row + ", col = " + col);
            return true;
        }
        else
            visited[row][col] = true;
        
        boolean res = false;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir: dirs) {
            int r = row + dir[0];
            int c = col + dir[1];
            
            if (r != preRow || c != preCol) {
                //System.out.println("r = " + r + ", c = " + c + ", rpreRow = " + preRow + ", preCol = " + preCol);
                res = res || dfs(grid, r, c, visited, row, col);
            }
        }
        return res;
    }
}
