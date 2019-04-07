/*
 * Dynamic Programming
 * To improve over the brute force solution, we first observe how we can avoid unnecessary
 * re-computation while validating palindromes. Consider the case "ababa". If we already
 * knew that "bab" is a palindrome, it is obvious that "ababa" must be a palindrome since
 * the two left and right end letters are the same.
 *
 * Time complexity : O(n^2)
 * Space complexity : O(n^2). It uses O(n^2) space to store the table.
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


/*
 * Official Solution 4: Expand Around Center
 * In fact, we could solve it in O(n^2) time using only constant space.
 * 
 * We observe that a palindrome mirrors around its center. Therefore, a 
 * palindrome can be expanded from its center, and there are only
 *  2n - 1 such centers.
 * 
 * You might be asking why there are 2n - 1 but not n centers? The 
 * reason is the center of a palindrome can be in between two letters. 
 * Such palindromes have even number of letters (such as "abba") and its
 *  center are between the two 'b's.
 */
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }
    
    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
    
 
/*
 * Approach 5: Manacher's Algorithm
 * There is even an O(n) algorithm called Manacher's algorithm, explained here
 * in detail. However, it is a non-trivial algorithm, and no one expects you to 
 * come up with this algorithm in a 45 minutes coding session. But, please go 
 * ahead and understand it, I promise it will be a lot of fun.
 * https://en.wikipedia.org/wiki/Longest_palindromic_substring#Manacher's_algorithm
 */
