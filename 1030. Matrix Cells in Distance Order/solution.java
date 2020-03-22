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
