/**
 * My solution of DP
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m * n)
 */
class Solution {
    public int maxUncrossedLines(int[] A, int[] B) {
        int[][] dp = new int[A.length+1][B.length+1];
        for (int i = 1; i <= A.length;  i++) {
            for (int j = 1; j <= B.length; j++) {
                if (A[i-1] == B[j-1])
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j-1], Math.max(dp[i][j-1], dp[i-1][j]));
            }
        }
        return dp[A.length][B.length];
    }
}


/**
 * My solution of DP with less space
 *
 * Time Complexity: O(m * n)
 * Space Complexity: O(m)
 */
class Solution {
    public int maxUncrossedLines(int[] A, int[] B) {
        int[] dp = new int[B.length+1];
        int prev = 0;
        for (int i = 1; i <= A.length;  i++) {
            prev = 0;
            for (int j = 1; j <= B.length; j++) {
                int cur = dp[j];
                if (A[i-1] == B[j-1])
                    dp[j] = prev + 1;
                else
                    dp[j] = Math.max(dp[j-1], dp[j]);
                
                prev = cur;
            }
        }
        return dp[B.length];
    }
}


/**
 * Other's solution of DP
 *
 * Time O(N^2), Space O(N^2)
 */
class Solution {
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length, dp[][] = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (A[i - 1] == B[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        return dp[m][n];
    }
}


/**
 * Other's solution of DP
 *
 * Time O(N^2), Space O(N)
 */
class Solution {
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length;
        if (m < n) return maxUncrossedLines(B, A);
	    
        int dp[] = new int[n+1];
        for (int i = 0; i < m; i++) {
            for (int j = 0, prev = 0, cur; j < n; j++) {
                cur = dp[j+1];
                if (A[i] == B[j]) dp[j+1] = 1+prev;
                else dp[j+1] = Math.max(dp[j+1], dp[j]);
                prev = cur;
            }
        }
        return dp[n];
    }
}
