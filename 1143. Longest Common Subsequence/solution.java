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


/**
 * Other's solution of DP
 *
 * https://leetcode.com/problems/longest-common-subsequence/discuss/351689/JavaPython-3-Two-DP-codes-of-O(mn)-and-O(min(m-n))-spaces-w-picture-and-analysis
 */
class Solution {
    // Method 1:
    // Time & space: O(m * n)
    public int longestCommonSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < s1.length(); ++i)
            for (int j = 0; j < s2.length(); ++j)
                if (s1.charAt(i) == s2.charAt(j)) dp[i + 1][j + 1] = 1 + dp[i][j];
                else dp[i + 1][j + 1] =  Math.max(dp[i][j + 1], dp[i + 1][j]);
        return dp[s1.length()][s2.length()];
    }

    // Method 2:
    // Space Optimization
    // Obviously, the code in method 1 only needs information of previous row to update current row. So we just use a two-row 2D array to
    // save and update the matching results for chars in s1 and s2.
    // Note: use k ^ 1 and k ^= 1 to switch between dp[0] (row 0) and dp[1] (row 1).
    // Time: O(m * n). space: O(min(m, n)).
    public int longestCommonSubsequence(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        if (m < n)  return longestCommonSubsequence(s2, s1);
        int[][] dp = new int[2][n + 1];
        for (int i = 0, k = 1; i < m; ++i, k ^= 1)
            for (int j = 0; j < n; ++j)
                if (s1.charAt(i) == s2.charAt(j)) dp[k][j + 1] = 1 + dp[k ^ 1][j];
                else dp[k][j + 1] = Math.max(dp[k ^ 1][j + 1], dp[k][j]);
        return dp[m % 2][n];
    }

    // Method 3:
    // Further Space Optimization to save half space.
    // Obviously, the above code in method 2 only needs information of previous and current columns of previous row to update current row.
    // So we just use a 1-row 1D array and 2 variables to save and update the matching results for chars in text1 and text2.
    // Time: O(m * n). space: O(min(m, n)).
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        if (m < n) {
            return longestCommonSubsequence(text2, text1);
        }
        int[] dp = new int[n + 1];
        for (int i = 0; i < text1.length(); ++i) {
            for (int j = 0, prevRow = 0, prevRowPrevCol = 0; j < text2.length(); ++j) {
                prevRowPrevCol = prevRow;
                prevRow = dp[j + 1];
                dp[j + 1] = text1.charAt(i) == text2.charAt(j) ? prevRowPrevCol + 1 : Math.max(dp[j], prevRow);
            }
        }
        return dp[n];
    }
}


/**
 * Other's solution of DP
 *
 * https://leetcode.com/problems/longest-common-subsequence/discuss/398711/ALL-4-ways-Recursion-greater-Top-down-greaterBottom-Up-greater-Efficient-Solution-O(N)-including-VIDEO-TUTORIAL
 */
class Solution {
    // Method1()- recursive solution(Top- down approach)
	// time complexity - O(2^(m+n))
	// space complexity - O(m+n)
	public static int LCSM1(char[] X, char[] Y, int i, int j) {
		if (i <= 0 || j <= 0)
			return 0;
		if (X[i - 1] == Y[j - 1])
			return 1 + LCSM1(X, Y, i - 1, j - 1);
		else
			return Math.max(LCSM1(X, Y, i, j - 1), LCSM1(X, Y, i - 1, j));

	}

	// Method2()- recursive solution with memoization
	// time complexity - O(m*n)
	// space complexity - O(m*n)
	public static int LCSM2(char[] X, char[] Y, int i, int j, Integer[][] dp) {
		if (i <= 0 || j <= 0)
			return 0;

		if (dp[i][j] != null)
			return dp[i][j];

		if (X[i - 1] == Y[j - 1])
			return 1 + LCSM2(X, Y, i - 1, j - 1, dp);
		else
			return dp[i][j] = Math.max(LCSM2(X, Y, i, j - 1, dp), LCSM2(X, Y, i - 1, j, dp));

	}

	// Method3()- DP solution(Bottom up approach)
	// time complexity - O(m*n)
	// space complexity - O(m*n)
	public static int LCSM3(char[] X, char[] Y, int m, int n) {
		int memo[][] = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == 0 || j == 0)
					memo[i][j] = 0;
				else if (X[i - 1] == Y[j - 1])
					memo[i][j] = memo[i - 1][j - 1] + 1;
				else
					memo[i][j] = Math.max(memo[i - 1][j], memo[i][j - 1]);
			}
		}
		return memo[m][n];
	}

	// Method4()- DP solution(Bottom up approach)
	// time complexity - O(m*n)
	// space complexity - O(n)
	public static int LCSM4(char[] X, char[] Y, int m, int n) {
		int memo[] = new int[n + 1];

		for (int i = 1; i <= m; i++) {
			int prev = 0;
			for (int j = 1; j <= n; j++) {
				int temp = memo[j];
				if (X[i - 1] == Y[j - 1]) {
					memo[j] = prev + 1;
				} else {
					memo[j] = Math.max(memo[j], memo[j - 1]);
				}
				prev = temp;
			}

		}
		return memo[n];
	}
}
