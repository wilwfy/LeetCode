/**
 * Other's solution of Two Pointers
 *
 * Original post: http://yucoding.blogspot.com/2013/02/leetcode-question-123-wildcard-matching.html
 *
 * The basic idea is to have one pointer for the string and one pointer for the pattern. This algorithm iterates
 * at most length(string) + length(pattern) times, for each iteration, at least one pointer advance one step.
 *
 * Time: O(N + M) normally,
 *       but O(N * M) in worst case that like: s ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" to match p ="*aaaaaab"
 * Space: O(1)
 */
class Solution {
    public boolean isMatch(String s, String p) {
        int sIdx = 0, pIdx = 0, match = 0, starIdx = -1;
        while (sIdx < s.length()) {
            if (pIdx < p.length() && (p.charAt(pIdx) == '?' || s.charAt(sIdx) == p.charAt(pIdx))) {
                // advancing both pointers when (both characters match) or ('?' found in pattern)
                sIdx++;
                pIdx++;
            } else if (pIdx < p.length() && p.charAt(pIdx) == '*') {
                // '*' found, track index of '*', only advancing pattern pointer
                starIdx = pIdx;
                match = sIdx; // the last position of matched char in string s
                pIdx++;
            } else if (starIdx != -1) {
                // current characters didn't match, current pattern pointer is not '*'
                // last pattern pointer was '*', advancing string pointer
                pIdx = starIdx + 1;
                match++;
                sIdx = match;
            } else {
                // current pattern pointer is not '*', last patter pointer was not '*'
                // So characters do not match
                return false;
            }
        }
        
        // check for remaining characters in pattern
        while (pIdx < p.length() && p.charAt(pIdx) == '*')
            pIdx++;
        
        return pIdx == p.length();
    }
}
