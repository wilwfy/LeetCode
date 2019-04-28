/*
 * The run time depends on the number of bits in nn. Because nn in this piece of code is a 32-bit integer, the time complexity is O(1).
 * The space complexity is O(1), since no additional space is allocated.
 */
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += (n & 1);
            n >>= 1;
        }
        return res;
    }
}


/*
 * Official solution 2
 *
 * The run time depends on the number of 11-bits in nn. In the worst case, all bits in nn are 11-bits.
 * In case of a 32-bit integer, the run time is O(1).
 * The space complexity is O(1), since no additional space is allocated.
 */
public class Solution {
    public int hammingWeight(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
}
