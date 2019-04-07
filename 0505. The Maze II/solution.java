
/*
 * Other's Solution
 */
class Solution {
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        if(maze==null || maze.length==0 || maze[0].length==0) return -1;
        int[][] distance = new int[maze.length][maze[0].length];
        for(int[] row : distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        helper(maze, start, distance);
        if (distance[destination[0]][destination[1]]==Integer.MAX_VALUE) return -1;
        return distance[destination[0]][destination[1]];
    }
    
    public void helper(int[][] maze, int[] start, int[][] distance){
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] dir : dirs){
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            int count = 0;
            while(x>=0 && x<maze.length && y>=0 && y<maze[0].length && maze[x][y]==0){
                x += dir[0];
                y += dir[1];
                count++;
            }
            if(distance[start[0]][start[1]] + count < distance[x-dir[0]][y-dir[1]]){
                distance[x-dir[0]][y-dir[1]] = distance[start[0]][start[1]] + count;
                helper(maze, new int[]{x-dir[0], y-dir[1]}, distance);
            }
        }
    }
}
