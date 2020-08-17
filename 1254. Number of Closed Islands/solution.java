/**
 * Other's solution of DFS with Flood fill
 *
 * Intuition
 * similar to 1020. Number of Enclaves.
 *
 * Algorithm
 * First, we need to remove all land connected to the edges using flood fill.
 * Then, we can count and flood-fill the remaining islands.
 *
 * Time: O(n), where n is the total number of cells. We flood-fill all land cells once.
 * Memory: O(n) for the stack. Flood fill is DFS, and the maximum depth is n.
 */
class Solution {
    public int closedIsland(int[][] grid) {
        // Remove all land connected to the edges using flood fill.
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1)
                    fill(grid, i, j);
            }
        }
        // Count and flood-fill the remaining islands.
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    fill(grid, i, j);
                }
            }
        }
        return res;
    }
    
    private void fill(int[][] grid, int x, int y) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] == 1)
            return;
        grid[x][y] = 1;
        int[] dirs = new int[]{0, 1, 0, -1, 0};
        for (int i = 0; i < dirs.length - 1; i++) {
            fill(grid, x + dirs[i], y + dirs[i + 1]);
        }
    }
}
