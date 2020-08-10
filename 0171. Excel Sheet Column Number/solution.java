/**
 * My solution
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'A' + 1;
            res = res * 26 + val;
        }
        return res;
    }
}
