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
                oneCnt[i] = i != 2 ? oneCnt[i>>>1] : 1;
            else
                oneCnt[i] = i > 2 ? oneCnt[i-1] + 1 : 1;
        }
        return oneCnt;
    }
}
