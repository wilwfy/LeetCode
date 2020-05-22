/**
 * Official solution of Dynamic Programming
 *
 * We will explain this approach with the help of an example.
 * 
 * 0 1 1 1 0
 * 1 1 1 1 1
 * 0 1 1 1 1
 * 0 1 1 1 1
 * 0 0 1 1 1
 * 
 * We initialize another matrix (dp) with the same dimensions as the original one initialized with all 0’s.
 * dp(i,j) represents the side length of the maximum square whose bottom right corner is the cell with index (i,j) in the original matrix.
 * Starting from index (0,0), for every 1 found in the original matrix, we update the value of the current element as
 * dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1.
 * 
 * We also remember the size of the largest square found so far. In this way, we traverse the original matrix once and find out the required
 * maximum size. This gives the side length of the square (say maxsqlen). The required result is the area maxsqlen^2.
 *
 * Time complexity : O(mn). Single pass.
 * Space complexity : O(mn). Another matrix of same size is used for dp.
 */
public class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }
}


/**
 * Official solution of Optimized Dynamic Programming
 *
 * In the previous approach for calculating dp of ith row we are using only the previous element and the (i−1)th row. Therefore,
 * we don't need 2D dp matrix as 1D dp array will be sufficient for this.
 * Initially the dp array contains all 0's. As we scan the elements of the original matrix across a row, we keep on updating the
 * dp array as per the equation dp[j]=min(dp[j−1],dp[j],prev), where prev refers to the old dp[j−1]. For every row, we repeat the
 * same process and update in the same dp array.
 *
 * Time complexity : O(mn). Single pass.
 * Space complexity : O(n). Another array which stores elements in a row is used for dp.
 */
public class Solution {
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}
