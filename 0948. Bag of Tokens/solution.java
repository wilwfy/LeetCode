/**
 * Official solution of Greedy with Two Pointers
 *
 * Intuition
 * 
 * If we play a token face up, we might as well play the one with the smallest value. Similarly,
 * if we play a token face down, we might as well play the one with the largest value.
 * 
 * Algorithm
 * 
 * We don't need to play anything until absolutely necessary. Let's play tokens face up until we
 * can't, then play a token face down and continue.
 * 
 * We must be careful, as it is easy for our implementation to be incorrect if we do not handle
 * corner cases correctly. We should always play tokens face up until exhaustion, then play one
 * token face down and continue.
 * 
 * Our loop must be constructed with the right termination condition: we can either play a token
 * face up or face down.
 * 
 * Our final answer could be any of the intermediate answers we got after playing tokens face up
 * (but before playing them face down.)
 *
 * Time Complexity: O(NlogN), where N is the length of tokens.
 * Space Complexity: O(N).
 */
class Solution {
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int lo = 0, hi = tokens.length - 1;
        int points = 0, ans = 0;
        while (lo <= hi && (P >= tokens[lo] || points > 0)) {
            while (lo <= hi && P >= tokens[lo]) {
                P -= tokens[lo++];
                points++;
            }

            ans = Math.max(ans, points);
            if (lo <= hi && points > 0) {
                P += tokens[hi--];
                points--;
            }
        }

        return ans;
    }
}

/**
 * Other's solution of Greedy with Two Pointers
 *
 * Explanation:
 * Sort tokens.
 * Buy at the cheapest and sell at the most expensive.
 */
class Solution {
    public int bagOfTokensScore(int[] tokens, int P) {
        Arrays.sort(tokens);
        int res = 0, points = 0, i = 0, j = tokens.length - 1;
        while (i <= j) {
            if (P >= tokens[i]) {
                P -= tokens[i];
                points++;
                i++;
                res = Math.max(res, points);
            } else if (points > 0) {
                points--;
                P += tokens[j];
                j--;
            } else
                break;
        }
        return res;
    }
}
