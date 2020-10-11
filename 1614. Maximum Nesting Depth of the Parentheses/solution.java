/**
 * Other's solution
 *
 * Exlanation
 * Ignore digits and signs,
 * only count the current open parentheses cur.
 * 
 * The depth equals to the maximum open parentheses.
 * 
 * 
 * Complexity
 * Time: O(N)
 * Space: O(1)
 */
class Solution {
    public int maxDepth(String s) {
        int res = 0, cur = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                cur++;
                res = Math.max(res, cur);
            } else if (c == ')')
                cur--;
        }
        return res;
    }
}
