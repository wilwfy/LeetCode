/**
 * My solution of DP
 *
 * Performance: 30 ms
 *
 * Time: O(n * m)
 * Space: O(n * m)
 */
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        int[][] dp = new int[n][m];
        char tmp = text2.charAt(0);
        if (text1.charAt(0) == text2.charAt(0)) dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            if (text1.charAt(i) == tmp) {
                dp[i][0] = 1;
            }
            else
                dp[i][0] = dp[i-1][0];
            //System.out.println("dp[" + i +"][0] = " + dp[i][0]);
        }
        tmp = text1.charAt(0);
        for (int j = 1; j < m; j++) {
            if (text2.charAt(j) == tmp)
                dp[0][j] = 1;
            else
                dp[0][j] = dp[0][j-1];
            //System.out.println("dp[0][" + j +"] = " + dp[0][j]);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (text1.charAt(i) == text2.charAt(j))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                //System.out.println("dp[" + i +"][" + j +"] = " + dp[i][j]);
            }
        }
        return dp[n-1][m-1];
    }
}
