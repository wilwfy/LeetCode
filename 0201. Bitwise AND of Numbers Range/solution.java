/**
 * Other's solution
 *
 * For example, for number 26 to 30
 * Their binary form are:
 * 11010
 * 11011
 * 11100
 * 11101
 * 11110
 * Because we are trying to find bitwise AND, so if any bit there are at least one 0 and one 1, it always 0. In this case, it is 11000.
 * So we are go to cut all these bit that they are different. In this case we cut the right 3 bit.
 */
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        int i = 0; // i means we have how many bits are 0 on the right
        while (m != n) {
            m >>= 1;
            n >>= 1;
            i++;
        }
        return m << i;
    }
}

/**
 * Other's solution
 */
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        // Desired Range: [5,12]
        // 12 ---- 1100
        // 11 ---- 1011
        // 10 ---- 1010
        // 9  ---- 1001
        // 8  ---- 1000
        // 7  ---- 0111
        // 6  ---- 0110
        // 5  ---- 0101
        //
        // Starting from 12, the loop will first do
        // 12 & 11 = 8
        // 
        // Next iteration, the loop will do
        // 8 & 7 = 0
        // 
        // why did we skip anding of 10,9? Because even if we did so, the result would eventually be anded
		// with 8 whose value would be lesser than equal to 8.
        // 
        // Hence, you start from the range end and keep working your way down the range till you reach the start.
        if (m == 0) return 0;
        while (n > m) {
            n = n & n-1;
        }
        return m & n;
    }
}
