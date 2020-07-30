/**
 * My solution with looping
 */
class Solution {
    public int addDigits(int num) {
        int res = num;
        while (res > 9) {
            int sum = 0;
            while (res > 0) {
                sum += (res%10);
                res /= 10;
            }
            res = sum;
        }
        return res;
    }
}


/**
 * Official solution based on Digital Root
 *
 * https://en.wikipedia.org/wiki/Digital_root#Congruence_formula
 * For base b (decimal case b = 10), the digit root of an integer is:
 *   dr(n) = 0 if n == 0
 *   dr(n) = (b-1) if n != 0 and n % (b-1) == 0
 *   dr(n) = n mod (b-1) if n % (b-1) != 0
 * or
 *   dr(n) = 1 + (n - 1) % 9
 *
 * Time Complexity: O(1).
 * Space Complexity: O(1).
 */
class Solution {
    public int addDigits(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }
}


/**
 * Another official solution based on Digital Root
 */
class Solution {
    public int addDigits(int num) {
        return num == 0 ? 0 : 1 + (num - 1) % 9;
    }
}
