/**
 * Other's BFS solution with queue and extra matrix of visited cell
 *
 * Algorithm
 * 1. We choose BFS because we need to visit the neighbors
 * 2. Mark each 0 as visited in another boolean array as TRUE
 * 3. Now let's go all four directions, and jump to non zero neighbor(having value 1) and not visited ones, assign this cell's value to "cell
 *    value from where we jumped" + 1
 * 4. Add this incremented row and column to Queue to visit it's neighbors as well to follow the same pattern
 */
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        Queue<int[]> qu = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    qu.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        
        int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!qu.isEmpty()) {
            // The queue (FIFO) makes sure that the '0' cells are processed at first,
            // then process the '1' cells which are next to the '0' cells,
            // then process the '1' cells which are next to the '1' cells above, and so on.
            int[] cur = qu.poll();
            for (int[] d: dir) {
                int row = cur[0] + d[0];
                int col = cur[1] + d[1];
                if (row < 0 || row >= m || col < 0 || col >= n || visited[row][col])
                    continue;
                visited[row][col] = true;
                matrix[row][col] = matrix[cur[0]][cur[1]] + 1;
                qu.offer(new int[]{row, col});
            }
        }
        
        return matrix;
    }
}


/**
 * Other's BFS solution with queue and no extra matrix of visited cell
 */
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[] {i, j});
                }
                else { //set cell value to Integer.MAX_VALUE if it is not 0
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                // If the calculated distance of the cell is not initialized Integer.MAX_VALUE yet,
                // then we don't need to explore that cell again
                if (r < 0 || r >= m || c < 0 || c >= n || matrix[r][c] != Integer.MAX_VALUE) continue;
                queue.add(new int[] {r, c});
                matrix[r][c] = matrix[cell[0]][cell[1]] + 1;
            }
        }
        
        return matrix;
    }
}


/**
 * Other's solution of DP without extra queue or matrix
 *
 * Time: O(m*n)
 * Space: O(1)
 */
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
			return matrix;
		}
		int range = matrix.length * matrix[0].length;

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] != 0) {
					int upCell = (i > 0) ? matrix[i - 1][j] : range;
					int leftCell = (j > 0) ? matrix[i][j - 1] : range;
					matrix[i][j] = Math.min(upCell, leftCell) + 1;
				}
			}
		}

		for (int i = matrix.length - 1; i >= 0; i--) {
			for (int j = matrix[0].length - 1; j >= 0; j--) {
				if (matrix[i][j] != 0) {
					int downCell = (i < matrix.length - 1) ? matrix[i + 1][j] : range;
					int rightCell = (j < matrix[0].length - 1) ? matrix[i][j + 1] : range;
					matrix[i][j] = Math.min(Math.min(downCell, rightCell) + 1, matrix[i][j]);
				}
			}
		}

		return matrix;
    }
}
