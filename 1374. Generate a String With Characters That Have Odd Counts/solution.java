/**
 * Other's solution with repeat()
 *
 * Time: O(N)
 * Space: O(N)
 */
class Solution {
    public String generateTheString(int n) {
        // If n is odd, we return "bbbb....b".
        // If n is even, we return "baaa...a".
        return "b" + "ab".substring(n % 2, 1 + n % 2).repeat(n - 1);
    }
}
