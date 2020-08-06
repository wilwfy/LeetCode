/**
 * Other's solution
 *
 * Time: O(n^2)
 * Space: O(n)
 */
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0) return;
        int start = 0, end = matrix.length - 1;
        
        // reverse up to down
        while (start < end) {
            int[] tmp = matrix[start];
            matrix[start] = matrix[end];
            matrix[end] = tmp;
            start++;
            end--;
        }
        
        // swap the symmetry
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        return;
    }
}
