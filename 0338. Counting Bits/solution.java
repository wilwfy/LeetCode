/**
 * My solution without DP
 *
 * Time: O(n*sizeof(integer))
 * Space: O(n)
 */
class Solution {
    public int[] countBits(int num) {
        int[] oneCnt = new int[num+1];
        for (int i = 0; i <= num; i++) {
            int val = i;
            while (val > 0) {
                oneCnt[i] += val%2;
                val = val >>> 1;
            }
        }
        return oneCnt;
    }
}


/**
 * My solution of DP
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public int[] countBits(int num) {
        int[] oneCnt = new int[num+1];
        for (int i = 0; i <= num; i++) {
            if (i%2 == 0)
                oneCnt[i] = i != 2 ? oneCnt[i>>>1] : 1; // The operator >>> is 'zero fill right shift'.
            else                                        // If A is 00111100, then A >>> 2 will get 00001111, but A >> 2 will get 1111
                oneCnt[i] = i > 2 ? oneCnt[i-1] + 1 : 1;
        }
        return oneCnt;
    }
}


/**
 * Other's solution of Recurrence
 *
 * An easy recurrence for this problem is f[i] = f[i / 2] + i % 2.
 */
class Solution {
    public int[] countBits(int num) {
        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++)
            f[i] = f[i >> 1] + (i & 1);
        return f;
    }
}
