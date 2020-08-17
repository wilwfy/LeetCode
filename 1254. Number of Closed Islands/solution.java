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


/**
 * Another solution of DFS
 *
 * Algorithm
 * Traverse grid, for each 0, do DFS to check if it is a closed island;
 * Within each DFS, if the current cell is out of the boundary of grid, return 0;
 * if the current cell value is positive, return 1;
 * otherwise, it is 0, change it to 2 then recurse to its 4 neighors and return the multification of them.
 *
 * Time & space: O(m * n), where m = grid.length, n = grid[0].length.
 */
class Solution {
    public int closedIsland(int[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; ++i)
            for (int j = 0; j < grid[0].length; ++j)
                if (grid[i][j] == 0)
                    cnt += dfs(i, j, grid);
        return cnt;
    }
    
    private int dfs(int i, int j, int[][] g) {
        if (i < 0 || i >= g.length || j < 0 || j >= g[0].length)
            return 0;
        if (g[i][j] > 0)
            return 1;
        g[i][j] = 2;
        return dfs(i + 1, j, g) * dfs(i - 1, j, g) * dfs(i, j + 1, g) * dfs(i, j - 1, g);
    }
}


/**
 * Other's solution of BFS
 *
 * Algorithm
 * For each land never seen before, BFS to check if the land extends to boundary. If yes, return 0, if not, return 1.
 *
 * Time & space: O(m * n), where m = grid.length, n = grid[0].length.
 */
class Solution {
    private static final int[] d = {0, 1, 0, -1, 0};
    private int m, n;
    
    public int closedIsland(int[][] grid) {
        int cnt = 0; 
        m = grid.length; n = m == 0 ? 0 : grid[0].length;
        Set<Integer> seenLand = new HashSet<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 0 && seenLand.add(i * n + j)) { // (i, j) is land never seen before.
                    cnt += bfs(i, j, grid, seenLand);
                }
            }
        }    
        return cnt;
    }
    
    private int bfs(int i, int j, int[][] g, Set<Integer> seenLand) {
        int ans = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(i * n + j);
        while (!q.isEmpty()) {
            i = q.peek() / n; j = q.poll() % n;
            for (int k = 0; k < 4; ++k) { // traverse 4 neighbors of (i, j)
                int r = i + d[k], c = j + d[k + 1];
                if (r < 0 || r >= m || c < 0 || c >= n) { // out of boundary.
                    ans = 0; // set 0;
                }else if (g[r][c] == 0 && seenLand.add(r * n + c)) { // (r, c) is land never seen before.
                    q.offer(r * n + c);
                }
            }
        }
        return ans;
    }
}


/**
 * Other's solution of Union-Find
 *
 * Algorithm
 * 1. Traverse all cells not on boundary of the grid; For each land with a land neighbor, if the neighbor belongs to
 *    open island, merge it into the open island; otherwise, merge the neighbor into the island including current cell;
 * 2. Traverse all cells not on boundary of the grid again; Whenever encountering a land that its parent (id) is
 *    itself, then it is the root of the component (island), increase count by 1; The final value of the count is
 *    the number of closed island.
 */
class Solution {
    private static final int[] d = {0, 1, 0, -1, 0};
    private int m, n; // numbers of rows and comlumns of grid.
    private int[] id; // parent ids. 
    
    public int closedIsland(int[][] grid) {
        m = grid.length; n = m == 0 ? 0 : grid[0].length;
        id = IntStream.range(0, m * n).toArray(); // Initialized as i * n + j the parent id of cell (i, j).
        for (int i = 1; i < m - 1; ++i) // traverse non-boundary rows.
            for (int j = 1; j < n - 1; ++j) // traverse non-boundary cells within a row.
                if (grid[i][j] == 0) // (i, j) is land.
                    for (int k = 0; k < 4; ++k) { // traverse the neighbors of (i, j).
                        int r = i + d[k], c = j + d[k + 1];
                        if (grid[r][c] == 0) // (r, c) is a land neighbor.
                            union(i * n + j, r * n + c);
                    }
        int cnt = 0; // number of closed islands: number of the non-boundary lands that are ids (parent) of itself.
        for (int i = 1; i < m - 1; ++i) // traverse non-boundary rows.
            for (int j = 1; j < n - 1; ++j) // traverse non-boundary cells within a row.
                if (grid[i][j] == 0 && id[i * n + j] == i * n + j) // Is (i, j) a land as well as the id (parent) of self? 
                    ++cnt;
        return cnt;
    }
    
    private int find(int x) {
        while (x != id[x]) 
            x = id[x];
        return x;
    }
    
    private void union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) 
            return;
        if (isBoundary(rootY)) {
            id[rootX] = rootY; 
        }else {
            id[rootY] = rootX;
        } 
    }
    
    private boolean isBoundary(int id) {
        int i = id / n, j = id % n;
        return i == 0 || j == 0 || i == m - 1 || j == n - 1;
    }
}
