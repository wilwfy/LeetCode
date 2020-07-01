/**
 * My solution of naive iteration
 *
 * Time Complexity: O(n) ?
 * Space Complexity: O(1)
 */
class Solution {
    public int arrangeCoins(int n) {
        int cnt = 0;
        while (n > cnt++) {
            n -= cnt;
        }
        return --cnt;
    }
}


/**
 * Official solution of Binary Search
 *
 * We could now reformulate the problem as follows:
 *     Find the maximum k such that k(k+1)/2 ≤ N.
 * The problem seems to be one of those search problems. And instead of naive iteration, one could resort to another more efficient algorithm called binary search, as we
 * can find in another similar problem called search insert position.
 *
 * Time Complexity: O(logN)
 * Space Complexity: O(1)
 */
class Solution {
    public int arrangeCoins(int n) {
        long left = 0, right = n;
        long k, curr;
        while (left <= right) {
            k = left + (right - left) / 2;
            curr = k * (k + 1) / 2; // the total number of coins needed from 1st to k-th row
            
            if (n == curr) return (int)k;
            
            if (n < curr)
                right = k - 1;
            else
                left = k + 1;
        }
        return (int)right;
    }
}


/**
 * Official solution of Math
 *
 * We could now reformulate the problem as follows:
 *     Find the maximum k such that k(k+1)/2 ≤ N.
 * As a reminder, the constraint of the problem can be expressed as follows:
 *     k(k+1) ≤ 2N
 * This could be solved by completing the square technique,
 *     (k + 1/2)^2 − 1/4 ≤ 2N
 * that results in the following answer:
 *     k = [sqrt{2N + 1/4} - 1/2]
 *
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */
class Solution {
  public int arrangeCoins(int n) {
    return (int)(Math.sqrt(2 * (long)n + 0.25) - 0.5);
  }
}
