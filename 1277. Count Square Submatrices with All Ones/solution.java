/**
 * Other's solution of Dynamic Programming
 *
 * Explanation
 * dp[i][j] means the size of biggest square with matrix[i][j] as bottom-right corner.
 * dp[i][j] also means the number of squares with matrix[i][j] as bottom-right corner.
 * 
 * If matrix[i][j] == 0, no possible square.
 * If matrix[i][j] == 1,
 * we compare the size of square dp[i-1][j-1], dp[i-1][j] and dp[i][j-1].
 * min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1 is the maximum size of square that we can find.
 *
 * 1. dp[i][j] means the size of biggest square with matrix[i][j] as bottom-right corner.
 * 2. dp[i][j] also means the number of squares with matrix[i][j] as bottom-right corner.
 * For example:
 * 1, 1, 1
 * 1, 1, 1
 * 1, 1, 1
 * 
 * dp[0][0] = 1
 * dp[1][1] = 2 (the square including matrix[1][1] can be square with element matrix[1][1] with size: 2 * 2, 1 * 1)
 * dp[2][2] = 3 (the square including matrix[2][2] can be square with element matrix[2][2] with size: 3 * 3, 2 * 2, 1 * 1)
 *
 * Time Complexity: O(MN)
 * Space Complexity: O(1)
 */
class Solution {
    public int countSquares(int[][] matrix) {
        int res = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if ((matrix[i][j] > 0) && (i > 0) && (j > 0)) {
                    matrix[i][j] = Math.min(matrix[i-1][j-1], Math.min(matrix[i-1][j], matrix[i][j-1])) + 1;
                }
                res += matrix[i][j];
            }
        }
        return res;
    }
}
