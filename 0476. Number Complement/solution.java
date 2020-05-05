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


/**
 * Other's solution of bit-manipulation
 ×
 * input: 5 = 0...0101(+)
 * output: 2 = 0...0010(+)
 * input + output = 0...0111(+)
 * ~5 = 1..1010 (-)（all bits inverse）
 * (Integer.highestOneBit(num) << 1) - 1 //it's mean 1000-1=0..0111(+)
 × ~5 & (Integer.highestOneBit(num) << 1) - 1  //result is  0...0 010
 */
public class Solution {
    public int findComplement(int num) {
        return ~num & (Integer.highestOneBit(num) - 1);
    }
}

public class Solution {
    public int findComplement(int num) {
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        return num ^ mask;
    }
}
