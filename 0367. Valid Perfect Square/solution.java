/**
 * Other's solution
 *
 * Time: O(sqrt(n))
 */
class Solution {
    public boolean isPerfectSquare(int num) {
        // 1 = 1
        // 4 = 1 + 3
        // 9 = 1 + 3 + 5
        // 16 = 1 + 3 + 5 + 7
        // 25 = 1 + 3 + 5 + 7 + 9
        // 36 = 1 + 3 + 5 + 7 + 9 + 11
        // ....
        // so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = n*n
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
}


/**
 * Other's solution of Binary Search
 *
 * Time: O(log(n))
 */
class Solution {
    public boolean isPerfectSquare(int num) {
        int low = 1, high = num;
        while (low <= high) {
            // use long for mid to avoid mid*mid from overflow
            long mid = low + (high - low)/2;
            if (mid * mid == num)
                return true;
            else if (mid * mid < num)
                low = (int) mid + 1;
            else
                high = (int) mid - 1;
        }
        return false;
    }
}


/**
 * Other's solution of Newton Method
 *
 * https://en.wikipedia.org/wiki/Integer_square_root#Using_only_integer_division
 */
class Solution {
    public boolean isPerfectSquare(int num) {
        // use long for x to avoid x*x from overflow
        long x = num;
        while (x * x > num) {
            x = (x + num/x) / 2;
        }
        return x * x == num;
    }
}
