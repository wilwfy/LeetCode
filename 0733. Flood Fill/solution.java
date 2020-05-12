/**
 * My solution of DFS
 *
 * Time: O(m*n)
 * Space: O(m*n)
 */
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image.length == 0) return image;
        int[][] visited = new int[image.length][image[0].length];
        return helper(visited, image, sr, sc, image[sr][sc], newColor);
    }
    
    public int[][] helper(int[][] visited, int[][] image, int row, int col, int oldColor, int newColor) {
        if ((row < 0) || (row >= image.length) ||
            (col < 0) || (col >= image[0].length) || (visited[row][col] == 1))
            return image;
        
        visited[row][col] = 1;
        if (image[row][col] == oldColor) {
            image[row][col] = newColor;
            helper(visited, image, row - 1, col, oldColor, newColor);
            helper(visited, image, row + 1, col, oldColor, newColor);
            helper(visited, image, row, col - 1, oldColor, newColor);
            helper(visited, image, row, col + 1, oldColor, newColor);
        }
        
        return image;
    }
}


/**
 * Other's solution of DFS
 *
 * Time Complexity: O(N), where N is the number of pixels in the image. We might process every pixel.
 * Space Complexity: O(N), the size of the implicit call stack when calling dfs.
 */
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        fill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }
    
    private void fill(int[][] image, int sr, int sc, int color, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color) return;
        image[sr][sc] = newColor;
        fill(image, sr + 1, sc, color, newColor);
        fill(image, sr - 1, sc, color, newColor);
        fill(image, sr, sc + 1, color, newColor);
        fill(image, sr, sc - 1, color, newColor);
    }
}
