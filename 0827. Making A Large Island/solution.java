/**
 * Official solution of DFS
 *
 * Intuition
 * 
 * As in the previous solution, we check every 0. However, we also store the size of each group, so that we do
 * not have to use depth-first search to repeatedly calculate the same size.
 * 
 * However, this idea fails when the 0 touches the same group. For example, consider grid = [[0,1],[1,1]]. The
 * answer is 4, not 1 + 3 + 3, since the right neighbor and the bottom neighbor of the 0 belong to the same group.
 * 
 * We can remedy this problem by keeping track of a group id (or index), that is unique for each group. Then, we'll
 * only add areas of neighboring groups with different ids.
 * 
 * Algorithm
 * 
 * For each group, fill it with value index and remember it's size as area[index] = dfs(...).
 * 
 * Then for each 0, look at the neighboring group ids seen and add the area of those groups, plus 1 for the 0 we
 * are toggling. This gives us a candidate answer, and we take the maximum.
 * 
 * To solve the issue of having potentially no 0, we take the maximum of the previously calculated areas.
 *
 * Time Complexity: O(N^2), where N is the length and width of the grid.
 * Space Complexity: O(N^2), the additional space used in the depth first search by area.
 */
class Solution {
    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};
    int[][] grid;
    int N;

    public int largestIsland(int[][] grid) {
        this.grid = grid;
        N = grid.length;

        int index = 2;
        int[] area = new int[N*N + 2];
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] == 1)
                    area[index] = dfs(r, c, index++);

        int ans = 0;
        for (int x: area) ans = Math.max(ans, x);
        for (int r = 0; r < N; ++r)
            for (int c = 0; c < N; ++c)
                if (grid[r][c] == 0) {
                    Set<Integer> seen = new HashSet();
                    for (Integer move: neighbors(r, c))
                        if (grid[move / N][move % N] > 1)
                            seen.add(grid[move / N][move % N]);

                    int bns = 1;
                    for (int i: seen) bns += area[i];
                    ans = Math.max(ans, bns);
                }

        return ans;
    }

    public int dfs(int r, int c, int index) {
        int ans = 1;
        grid[r][c] = index;
        for (Integer move: neighbors(r, c)) {
            if (grid[move / N][move % N] == 1) {
                grid[move / N][move % N] = index;
                ans += dfs(move / N, move % N, index);
            }
        }

        return ans;
    }

    public List<Integer> neighbors(int r, int c) {
        List<Integer> ans = new ArrayList();
        for (int k = 0; k < 4; ++k) {
            int nr = r + dr[k];
            int nc = c + dc[k];
            if (0 <= nr && nr < N && 0 <= nc && nc < N)
                ans.add(nr * N + nc);
        }

        return ans;
    }
}


/**
 * Other's DFS solution
 *
 * PreWord
 * The solution is long, but in fact it is really straight forward.
 * I suggest not going into my codes but reading my explanations, which should be enough.
 * 
 * Prepare
 * I have several simple sub function to help me on this kind of problem.
 * 
 * valid(int x, int y), check if (x, y) is valid in the grid.
 * move(int x, int y), return all possible next position in 4 directions.
 * 
 * Explanation
 * Only 2 steps:
 * 
 * Explore every island using DFS, count its area, give it an island index and save the result to a {index: area} map.
 * Loop every cell == 0, check its connected islands and calculate total islands area.
 * 
 * Complexity
 * Time O(N^2)
 * Space O(N^2)
 */
class Solution {
    public int N = 0;
    public int largestIsland(int[][] grid) {
        N = grid.length;
        //DFS every island and give it an index of island
        int index = 3, res = 0;
        HashMap<Integer, Integer> area = new HashMap<>();
        for (int x = 0; x < N; ++x) for (int y = 0; y < N; ++y)
            if (grid[x][y] == 1) {
                area.put(index, dfs(grid, x, y, index));
                res = Math.max(res, area.get(index++));
            }

        //traverse every 0 cell and count biggest island it can conntect
        for (int x = 0; x < N; ++x) for (int y = 0; y < N; ++y)
            if (grid[x][y] == 0) {
                HashSet<Integer> seen = new HashSet<>();
                int cur = 1;
                for (Pair<Integer, Integer> p : move(x, y)) {
                    index = grid[p.getKey()][p.getValue()];
                    if (index > 1 && !seen.contains(index)) {
                        seen.add(index);
                        cur += area.get(index);
                    }
                }
                res = Math.max(res, cur);
            }
        return res;
    }

    public List <Pair<Integer, Integer>> move(int x, int y) {
        ArrayList <Pair<Integer, Integer>> res = new ArrayList<>();
        if (valid(x, y + 1)) res.add(new Pair<Integer, Integer>(x, y + 1));
        if (valid(x, y - 1)) res.add(new Pair<Integer, Integer>(x, y - 1));
        if (valid(x + 1, y)) res.add(new Pair<Integer, Integer>(x + 1, y));
        if (valid(x - 1, y)) res.add(new Pair<Integer, Integer>(x - 1, y));
        return res;
    }

    public boolean valid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    public int dfs(int[][] grid, int x, int y, int index) {
        int area = 0;
        grid[x][y] = index;
        for (Pair<Integer, Integer> p : move(x, y))
            if (grid[p.getKey()][p.getValue()] == 1)
                area += dfs(grid, p.getKey(), p.getValue(), index);
        return area + 1;
    }
}
