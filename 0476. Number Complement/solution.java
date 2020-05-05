/**
 * My solution of bit-manipulation
 */
class Solution {
    public int findComplement(int num) {
        int res = 0, shift = 0;
        while (num > 0) {
            int remainder = num % 2;
            num /= 2;
            remainder = remainder == 0 ? 1 : 0;
            res += (remainder << shift);
            shift++;
        }
        return res;
    }
}


/**
 * Other's solution of bit-manipulation
 *
 * for example:
 * 100110, its complement is 011001, the sum is 111111. So we only need get the min number large or equal to num, then do substraction.
 */
public class Solution {
    public int findComplement(int num) {
        int n = 0;
        while (n < num) {
            n = (n << 1) | 1;
        }
        return n - num;
    }
}
