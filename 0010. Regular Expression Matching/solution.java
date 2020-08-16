/**
 * Official solution of Recursion
 */
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) // for case of that * means zero of the preceding char
                || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }
}


/**
 * Official solution of DP (Top-Down Variation)
 */
enum Result {
    TRUE, FALSE
}
class Solution {
    Result[][] memo;

    public boolean isMatch(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()){
            ans = i == text.length();
        } else{
            boolean first_match = (i < text.length() &&
                                   (pattern.charAt(j) == text.charAt(i) ||
                                    pattern.charAt(j) == '.'));

            if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                ans = (dp(i, j+2, text, pattern) ||
                       first_match && dp(i+1, j, text, pattern));
            } else {
                ans = first_match && dp(i+1, j+1, text, pattern);
            }
        }
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }
}


/**
 * Official solution of DP (Bottom-Up Variation)
 *
 * Time Complexity: Let T, P be the lengths of the text and the pattern respectively.
 *                  The work for every call to dp(i, j) for i=0, ... ,Ti=0,...,T; j=0, ... ,Pj=0,...,P is done once, and it is O(1) work.
 *                  Hence, the time complexity is O(T*P).
 * Space Complexity: The only memory we use is the O(T*P) boolean entries in our cache. Hence, the space complexity is O(T*P).
 */
class Solution {
    public boolean isMatch(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--){
            for (int j = pattern.length() - 1; j >= 0; j--){
                boolean first_match = (i < text.length() &&
                                       (pattern.charAt(j) == text.charAt(i) ||
                                        pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}


/**
 * Other's solution of DP
 *
 * Time Complexity: O(T*P). Where T, P is the lengths of the text and the pattern respectively.
 * Space Complexity: O(T*P).
 */
class Solution {
    public boolean isMatch(String s, String p) {
        // corner case
        if (s == null || p == null) return false;
        //return true;
        
        int m = s.length();
        int n = p.length();
        
        // M[i][j] represents if the 1st i characters in s can match the 1st j characters in p
        boolean[][] M = new boolean[m+1][n+1];
        
        // initialization: 
		// 1. M[0][0] = true, since empty string matches empty pattern
        M[0][0] = true;
        
        // 2. M[i][0] = false(which is default value of the boolean array) since empty pattern cannot match non-empty string
		// 3. M[0][j]: what pattern matches empty string ""? It should be #*#*#*#*..., or (#*)* if allow me to represent regex using regex, 
		// and for this case we need to check manually: 
        // as we can see, the length of pattern should be even && the character at the even position should be *, 
		// thus for odd length, M[0][j] = false which is default. So we can just skip the odd position, i.e. j starts from 2, the interval of j is also 2. 
		// and notice that the length of repeat sub-pattern #* is only 2, we can just make use of M[0][j - 2] rather than scanning j length each time 
		// for checking if it matches #*#*#*#*.
        for (int j = 2; j < n + 1; j += 2) {
            if (p.charAt(j - 1) == '*' && M[0][j - 2])
                M[0][j] = true;
        }
        
		// Induction rule is very similar to edit distance, where we also consider from the end. And it is based on what character in the pattern we meet.
        // 1. if p.charAt(j) == s.charAt(i), M[i][j] = M[i - 1][j - 1]
		//    ######a(i)
		//    ####a(j)
        // 2. if p.charAt(j) == '.', M[i][j] = M[i - 1][j - 1]
        // 	  #######a(i)
        //    ####.(j)
        // 3. if p.charAt(j) == '*':
        //    1. if p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i), then b* is counted as empty. M[i][j] = M[i][j - 2]
        //       #####a(i)
        //       ####b*(j)
        //    2.if p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i):
        //       ######a(i)
        //       ####.*(j)
		//
		// 	  	 #####a(i)
        //    	 ###a*(j)
        //      2.1 if p.charAt(j - 1) is counted as empty, then M[i][j] = M[i][j - 2]
        //      2.2 if counted as one, then M[i][j] = M[i - 1][j - 2]
        //      2.3 if counted as multiple, then M[i][j] = M[i - 1][j]
                
		// recap:
		// M[i][j] = M[i - 1][j - 1]
		// M[i][j] = M[i - 1][j - 1]
		// M[i][j] = M[i][j - 2]
		// M[i][j] = M[i][j - 2]
        // M[i][j] = M[i - 1][j - 2]
        // M[i][j] = M[i - 1][j]
		// Observation: from above, we can see to get M[i][j], we need to know previous elements in M, i.e. we need to compute them first. 
		// which determines i goes from 1 to m + 1, j goes from 1 to n + 1
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                char curS = s.charAt(i - 1);
                char curP = p.charAt(j - 1);
                if (curS == curP || curP == '.') {
                    M[i][j] = M[i - 1][j - 1];
                } else if (curP == '*') {
                    char preP = p.charAt(j - 2); // need make sure j >= 2 here ?
                    if (preP != '.' && preP != curS)
                        M[i][j] = M[i][j - 2];
                    else
                        M[i][j] = M[i][j - 2] || M[i - 1][j - 2] || M[i - 1][j];
                }
            }
        }
        return M[m][n];
    }
}
