/**
 * My solution of naive iteration
 *
 * Time: O(n) ?
 * Space: O(1)
 */
class Solution {
    public int arrangeCoins(int n) {
        int cnt = 0;
        while (n > cnt++) {
            n -= cnt;
        }
        return --cnt;
    }
}
