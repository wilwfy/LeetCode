/*
 * Other's solution of BFS
 */
class Solution {
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[R*C][2];
        boolean[][] visited = new boolean[R][C];
        
        int i = 0;
        
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{r0, c0});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1];
            if (r < 0 || r >= R || c < 0 || c >= C)
                continue;
            
            if (visited[r][c]) continue;
            
            res[i++] = cur;
            visited[r][c] = true;
            
            queue.offer(new int[]{r, c-1});
            queue.offer(new int[]{r, c+1});
            queue.offer(new int[]{r-1, c});
            queue.offer(new int[]{r+1, c});
        }
        return res;
    }
}


/*
 * Other's solution of BFS
 */
class Solution {
    
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        
        int[][] res = new int[R * C][2];
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        
        int i = 0;
        res[i++] = new int[] {r0, c0};
        q.add(new int[] {r0, c0});
        visited[r0][c0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            
            for (int[] dir : dirs) {
                int x = r + dir[0];
                int y = c + dir[1];
                if (x < 0 || x >= R || y < 0 || y >= C || visited[x][y]) {
                    continue;
                }
                res[i++] = new int[] {x, y};
                visited[x][y] = true;
                q.add(new int[] {x, y});
            }
        }
        return res;
    }
}


/*
 * Other's solution of BFS
 */
class Solution {
    
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        
        int[][] res = new int[R * C][2];
        boolean[][] visited = new boolean[R][C];
        Queue<int[]> q = new LinkedList<>();
        
        int i = 0;
        res[i++] = new int[] {r0, c0};
        q.add(new int[] {r0, c0});
        visited[r0][c0] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];
            
            for (int[] dir : dirs) {
                int x = r + dir[0];
                int y = c + dir[1];
                if (x < 0 || x >= R || y < 0 || y >= C || visited[x][y]) {
                    continue;
                }
                res[i++] = new int[] {x, y};
                visited[x][y] = true;
                q.add(new int[] {x, y});
            }
        }
        return res;
    }
}


