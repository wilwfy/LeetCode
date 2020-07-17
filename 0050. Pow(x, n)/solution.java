/**
 * Other's solution of Binary Search
 */
class Solution {
    public double myPow(double x, int n) {
        if (n == 0)
            return 1;
        if (n < 0) {
            // avoid integer overflow
            // INT_MIN is -2147483648 but INT_MAX is 2147483647 ,so n = -n will be failed!!
            return 1/x * myPow(1/x, -(n + 1));
        }
        return (n%2 == 0) ? myPow(x*x, n/2) : x*myPow(x*x, n/2);
    }
}
