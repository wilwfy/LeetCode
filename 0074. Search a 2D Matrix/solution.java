/**
 * My solution of Binary Search
 *
 * Time: O(log(m*n))
 * Space: O(1)
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int m = matrix.length, n = matrix[0].length;
        int start = 0, end = m * n - 1;
        int mid, row = 0, col = 0;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            row = mid / n;
            col = mid % n;
            if (matrix[row][col] == target)
                return true;
            else if (matrix[row][col] > target)
                end = mid;
            else
                start = mid;
        }
        row = start / n;
        col = start % n;
        if (matrix[row][col] == target)
            return true;
        
        row = end / n;
        col = end % n;
        if (matrix[row][col] == target)
            return true;
        
        return false;
    }
}
