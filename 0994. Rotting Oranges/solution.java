/**
 * Other's BFS solution
 */
class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int row = grid.length, col = grid[0].length;
        int fresh_cnt = 0;
        Queue<int[]> rotQue = new LinkedList<>();
        //Put the position of all rotten oranges in queue
        //count the number of fresh oranges
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2)
                    rotQue.offer(new int[]{i, j});
                else if (grid[i][j] == 1)
                    fresh_cnt++;
            }
        }
        //if count of fresh oranges is zero --> return 0
        if (fresh_cnt == 0) return 0;
        int timeCnt = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        //bfs starting from initially rotten oranges
        while(!rotQue.isEmpty()) {
            timeCnt++;
            int quSize = rotQue.size();
            for (int i = 0; i < quSize; i++) {
                int[] rottenOrg = rotQue.poll();
                for (int[] dir: dirs) {
                    int x = rottenOrg[0] + dir[0];
                    int y = rottenOrg[1] + dir[1];
                    //if x or y is out of bound
                    //or the orange at (x , y) is already rotten
                    //or the cell at (x , y) is empty
                    //we do nothing
                    if (x < 0 || y < 0 || x >= row || y >= col || grid[x][y] == 0 || grid[x][y] == 2) continue;
                    //mark the orange at (x , y) as rotten
                    grid[x][y] = 2;
                    //put the new rotten orange at (x , y) in queue
                    rotQue.offer(new int[]{x, y});
                    //decrease the count of fresh oranges by 1
                    fresh_cnt--;
                }
            }
        }
        return fresh_cnt == 0 ? timeCnt - 1 : -1;
    }
}
