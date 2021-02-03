/**
 * My solution of One Pass
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int maxPower(String s) {
        if (s == null) return 0;
        if (s.length() == 1) return 1;
        char prev = s.charAt(0);
        int max = 1;
        int cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == prev) cnt++;
            else {
                prev = c;
                max = Math.max(max, cnt);
                cnt = 1;
            }
        }
        return Math.max(max, cnt);
    }
}
