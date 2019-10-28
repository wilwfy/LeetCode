/**
 * Other's solution by using Piorioty Queue
 *
 * Time Complexity: m*n log (m * n)
 */
class Solution {
    
    public class Cell {
        private int row;
        private int col;
        private int height;
        
        public Cell (int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }
    }
    
    public int trapRainWater(int[][] heightMap) {
        // if length is less then 2, it means the matrix has only border cells
        if ((heightMap == null) || (heightMap.length <= 2) || (heightMap[0].length <= 2))
            return 0;
        
        PriorityQueue<Cell> pq = new PriorityQueue<>(new Comparator<Cell>() {
           public int compare(Cell a, Cell b) {
               return a.height - b.height;
           } 
        });
        
        int m = heightMap.length;
        int n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        
        // Initially, add all the Cells which are on borders to the queue.
        for (int i = 0; i < m; i++) {
            visited[i][0] = true;
            visited[i][n-1] = true;
            pq.offer(new Cell(i, 0, heightMap[i][0]));
            pq.offer(new Cell(i, n-1, heightMap[i][n-1]));
        }
        for (int i = 1; i < n-1; i++) {
            visited[0][i] = true;
            visited[m-1][i] = true;
            pq.offer(new Cell(0, i, heightMap[0][i]));
            pq.offer(new Cell(m-1, i, heightMap[m-1][i]));
        }
        
        // from the borders, pick the shortest cell visited and check its neighbors:
        // if the neighbor is shorter, collect the water it can trap and update its height as its height plus the water trapped
        // add all its neighbors to the queue
        int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int res = 0;
        while (!pq.isEmpty()) {
            Cell cell = pq.poll();
            for (int[] dir: dirs) {
                int row = cell.row + dir[0];
                int col = cell.col + dir[1];
                if (row >=0 && row < m && col >= 0 && col < n && !visited[row][col]) {
                    visited[row][col] = true;
                    res += Math.max(0, cell.height - heightMap[row][col]);
                    pq.offer(new Cell(row, col, Math.max(cell.height, heightMap[row][col])));
                }
            }
        }
        return res;
    }
}
