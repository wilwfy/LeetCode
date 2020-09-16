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
 * 1 ~ 9 include 9 one digit number;
 * 10 ~ 99 include 90 two digits number;
 * 100 ~ 999 include 900 three digits number;
 * 1000 ~ 9999 include 9000 four digits number;
 * ...
 * 
 * len is how many digits:1 or 2 or 3 ..., range is 9 or 90 or 900 ...
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
