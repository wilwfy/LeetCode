/**
 * Other's solution of Sliding Window
 *
 * Intuition
 * Change the input of string s and t into an array of difference.
 * Then it's a standard sliding window problem.
 * 
 * 
 * Complexity
 * Time: O(N) for one pass
 * Space: O(1)
 */
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length(), i = 0, j; // i ~ j is the sliding window
        for (j = 0; j < n; j++) {
            maxCost -= Math.abs(s.charAt(j) - t.charAt(j));
            if (maxCost < 0) {
                maxCost += Math.abs(s.charAt(i) - t.charAt(i));
                i++;
            }
        }
        // why we can just return r - l instead of using Math.max(max, r - l + 1).
        // The reason is the width of sliding window (in this case) will never be
        // smaller than before: every time you move the left bound of the window,
        // then you move the right bound, in other words, ++l => ++r.
        return j - i;
    }
}
