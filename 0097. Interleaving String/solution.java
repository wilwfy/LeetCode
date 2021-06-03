/**
 * Official solution of Recursion with memoization
 *
 * Algorithm
 * In the recursive approach discussed above, we are considering every possible string formed by interleaving the two given strings. But, there will
 * be cases encountered in which, the same portion of s1s1 and s2s2 would have been processed already but in different orders(permutations). But irrespective
 * of the order of processing, if the resultant string formed till now is matching with the required string(s3s3), the final result is dependent only on the
 * remaining portions of s1s1 and s2s2, but not on the already processed portion. Thus, the recursive approach leads to redundant computations.
 *
 * This redundancy can be removed by making use of memoization along with recursion. For this, we maitain 3 pointers i, j, ki,j,k which correspond to the index
 * of the current character of s1, s2, s3s1,s2,s3 respectively. Also, we maintain a 2D memo array to keep a track of the substrings processed so far. memo[i][j]
 * stores a 1/0 or -1 depending on whether the current portion of strings i.e. upto i th index for s1 and upto j th index for s2 has already been evaluated. Again,
 * we start by selecting the current character of s1 (pointed by i). If it matches the current character of s3 (pointed by k), we include it in the processed string
 * and call the same function recurively as: is_Interleave(s1, i+1, s2, j, s3, k+1, memo)
 *
 * Thus, here we have called the function by incrementing the pointers i and k since the portion of strings upto those indices has already been processed. Similarly,
 * we choose one character from the second string and continue. The recursive function ends when either of the two strings s1 or s2 has been fully processed. If, let's
 * say, the string s1 has been fully processed, we directly compare the remaining portion of s2 with the remaining portion of s3. When the backtrack occurs from the
 * recursive calls, we store the value returned by the recursive functions in the memoization array memo appropriatelys so that when it is encountered the next time,
 * the recursive function won't be called, but the memoization array itself will return the previous generated result.
 *
 * Time complexity: O(m⋅n), where m is the length of s1 and n is the length of s2. That's a consequence of the fact that each (i, j) combination is computed only once.
 * Space complexity: O(m⋅n) to keep double array memo.
 */
 public class Solution {
    public boolean is_Interleave(String s1, int i, String s2, int j, String s3, int k, int[][] memo) {
        if (i == s1.length()) {
            return s2.substring(j).equals(s3.substring(k));
        }
        if (j == s2.length()) {
            return s1.substring(i).equals(s3.substring(k));
        }
        if (memo[i][j] >= 0) {
            return memo[i][j] == 1 ? true : false;
        }
        boolean ans = false;
        if (s3.charAt(k) == s1.charAt(i) && is_Interleave(s1, i + 1, s2, j, s3, k + 1, memo)
                || s3.charAt(k) == s2.charAt(j) && is_Interleave(s1, i, s2, j + 1, s3, k + 1, memo)) {
            ans = true;
        }
        memo[i][j] = ans ? 1 : 0;
        return ans;
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        int memo[][] = new int[s1.length()][s2.length()];
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return is_Interleave(s1, 0, s2, 0, s3, 0, memo);
    }
}
