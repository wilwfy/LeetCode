/**
 * Other's solution of Binary Search from top-right corner
 *
 * Intution
 *
 * We start search the matrix from top right corner, initialize the current position to top right corner,
 * if the target is greater than the value in current position, then the target can not be in entire row
 * of current position because the row is sorted, if the target is less than the value in current position,
 * then the target can not in the entire column because the column is sorted too. We can rule out one row
 * or one column each time, so the time complexity is O(m+n).
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] > target)
                col--;
            else
                row++;
        }
        return false;
    }
}


/**
 * Other's solution of Binary Search from bottom-left corner
 *
 * it's like the matrix contains two "binary search tree" and it has two "roots" correspondingly.
 *
 * Time Complexity: O(m+n).
 * Space Complexity: O(1).
 */
class Solution {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1)
            return false;
    
        int col = 0;
        int row = matrix.length - 1;
        while (col <= matrix[0].length - 1 && row >= 0) {
            if (target == matrix[row][col])
                return true;
            else if (target < matrix[row][col])
                row--;
            else if (target > matrix[row][col])
                col++;
        }
        return false;
    }
}
