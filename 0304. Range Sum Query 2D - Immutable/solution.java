/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */

/*
 * Similar to the official solution: Caching Rows
 *
 * Time complexity : O(m) time per query, O(mn) time pre-computation.
 *                   The pre-computation in the constructor takes O(mn) time.
 *                   The sumRegion query takes O(m) time.
 * Space complexity : O(mn). The algorithm uses O(mn) space to store the cumulative sum of all rows.
 */
class NumMatrix {
    private int[][] sumMatrix;

    public NumMatrix(int[][] matrix) {
        if ((matrix == null) || (matrix.length == 0)) return;
        sumMatrix = new int[matrix.length][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sumMatrix[i][j+1] = sumMatrix[i][j] + matrix[i][j];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int row = row1; row <= row2; row++) {
            sum += sumMatrix[row][col2+1] - sumMatrix[row][col1];
        }
        return sum;
    }
}




/*
 * Official solution: Caching Smarter
 * We could use the principle of inclusion-exclusion to calculate Sum(ABCD) as following:
 * Sum(ABCD) = Sum(OD) - Sum(OB) - Sum(OC) + Sum(OA)
 *
 * Time complexity : O(1) time per query, O(mn) time pre-computation. The pre-computation
 *                   in the constructor takes O(mn) time. Each sumRegion query takes O(1) time.
 * Space complexity : O(mn). The algorithm uses O(mn) space to store the cumulative region sum.
 */
class NumMatrix {
    private int[][] dp;
    
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                dp[r + 1][c + 1] = dp[r + 1][c] + dp[r][c + 1] + matrix[r][c] - dp[r][c];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2 + 1][col2 + 1] - dp[row1][col2 + 1] - dp[row2 + 1][col1] + dp[row1][col1];
    }
}
