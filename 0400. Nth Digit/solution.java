/**
 * Other's solution
 *
 * Explanation
 *
 * Straight forward way to solve the problem in 3 steps:
 * 1. find the length of the number where the nth digit is from
 * 2. find the actual number where the nth digit is from
 * 3. find the nth digit and return
 *
 *
 * Time: O(log_10(n))
 * Space: O(1)
 */
class Solution {
    public int findNthDigit(int n) {
        int start = 1;
        int len = 1;
        long count = 9;
        while (n > len * count) {
            n -= len * count;
            len++;
            count *= 10;
            start *= 10;
        }
        
        start += (n - 1) / len;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }
}
