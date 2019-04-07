/*
 * Dynamic Programming
 * To improve over the brute force solution, we first observe how we can avoid unnecessary
 * re-computation while validating palindromes. Consider the case "ababa". If we already
 * knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome since
 * the two left and right end letters are the same.
 */
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        int start = n-1, end = n-1;
        boolean[][] dp = new boolean[n][n];
        // we need prove Str(i, j) based on Str(i+1, j-1), 
        // so need iterate i from n-1 to 0
        for (int i = n-1; i >= 0; i--) {
            for (int j = i; j<n; j++) {
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && ((j - i < 2) || dp[i+1][j-1]);
                if (dp[i][j] && (j - i > end - start)) {
                    start = i;
                    end = j;
                }
            }
        }
        return (n == 0) ? "" : s.substring(start, end+1);
    }
}
