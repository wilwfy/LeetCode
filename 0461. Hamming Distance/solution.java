/**
 * My solution
 *
 * Time: O(logN).  N = x ^ y
 * Space: O(1)
 */
class Solution {
    public int hammingDistance(int x, int y) {
        int res = 0, tmp = x ^ y;
        while (tmp > 0) {
            res += (tmp & 1); // res += tmp % 2;
            tmp /= 2;
        }
        return res;
    }
}


/**
 * Other's solution
 */
public class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}
