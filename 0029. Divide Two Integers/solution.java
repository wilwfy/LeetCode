/**
 * Other's solution with subtraction
 *
 * The key observation is that the quotient of a division is just the number of times that we can
 * subtract the divisor from the dividend without making it negative.
 * 
 * Suppose dividend = 15 and divisor = 3, 15 - 3 > 0. We now try to subtract more by shifting 3 to
 * the left by 1 bit (6). Since 15 - 6 > 0, shift 6 again to 12. Now 15 - 12 > 0, shift 12 again
 * to 24, which is larger than 15. So we can at most subtract 12 from 15. Since 12 is obtained by
 * shifting 3 to left twice, it is 1 << 2 = 4 times of 3. We add 4 to an answer variable (initialized
 * to be 0). The above process is like 15 = 3 * 4 + 3. We now get part of the quotient (4), with a
 * remaining dividend 3.
 * 
 * Then we repeat the above process by subtracting divisor = 3 from the remaining dividend = 3 and
 * obtain 0. We are done. In this case, no shift happens. We simply add 1 << 0 = 1 to the answer variable.
 * 
 * This is the full algorithm to perform division using bit manipulations. The sign also needs to be
 * taken into consideration. And we still need to handle one overflow case:
 * dividend = INT_MIN and divisor = -1.
 *
 * Time: O(logN^2).
 * Space: O(1).
 */
class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (divisor == 1 || dividend == 0) return dividend;
        if (divisor == Integer.MIN_VALUE) {
            if (dividend == Integer.MIN_VALUE) return 1;
            else return 0;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1)
                return Integer.MAX_VALUE;
            else
                // if there is no process below, the result will be 0 which is wrong
                return ((divisor & 1) == 1) ? divide(dividend + 1, divisor) : divide(dividend >> 1, divisor >> 1);
        }
        
        int res = 0;
        int dvd = Math.abs(dividend), dvs = Math.abs(divisor);
        while (dvd >= dvs) {
            int tmp = dvs, mult = 1;
            while ( (tmp << 1) > 0 && dvd > (tmp << 1) ) {
                tmp <<= 1;
                mult <<= 1;
            }
            dvd -= tmp;
            res += mult;
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }
}


/**
 * Another's solution with subtraction
 *
 * Only one corner case is -2^31 / 1 and it is processed at the first line.
 *
 * Time: O(logN^2).
 * Space: O(1).
 */
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 1 << 31 && divisor == -1)
            return (1 << 31) - 1;
        
        int a = Math.abs(dividend), b = Math.abs(divisor), res = 0, x = 0;
        while (a - b >= 0) {
            for (x = 0; a - (b << x << 1) >= 0; x++);
            res += 1 << x;
            a -= b << x;
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }
}
