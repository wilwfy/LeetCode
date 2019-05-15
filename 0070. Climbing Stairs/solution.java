/*
 * Brute-force solution
 * Issue: time limitation exceeded
 * Time complexity : O(2^n). Size of recursion tree will be 2^n.
 */
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        return helper(n);
    }
    
    public int helper(int stairs) {
        if (stairs < 0) return 0; // Complete more steps than stairs, invalid
        if (stairs == 0) return 1; // Just finish stairs, valid
        int waysCount = 0;
        // Stairs not finished, then take 1 step or 2 steps
        waysCount = helper(stairs-1) + helper(stairs-2);
        return waysCount;
    }
}


/*
 * Solution: Recursion with Memoization
 *
 * Time complexity : O(n). Size of recursion tree can go upto n.
 * Space complexity : O(n). The depth of recursion tree can go upto n.
 */
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        int[] memo = new int[n+1]; // memo[0] ~ memo[n]
        return helper(n, memo);
    }
    
    public int helper(int stairs, int[] m) {
        if (stairs < 0) return 0; // Complete more steps than stairs, invalid
        if (stairs == 0) return 1; // Just finish stairs, valid
        if (m[stairs] > 0) return m[stairs];
        // Stairs not finished, then take 1 step or 2 steps
        m[stairs] = helper(stairs-1, m) + helper(stairs-2, m);
        return m[stairs];
    }
}


/*
 * Solution: Dynamic Programming
 * One can reach i th step in one of the two ways:
 *    - Taking a single step from (i−1) th step.
 *    - Taking a step of 2 from (i−2) th step.
 * Let dp[i] denotes the number of ways to reach on i th step: dp[i] = dp[i-1] + dp[i-2]
 *
 * Time complexity : O(n). Single loop upto n.
 * Space complexity : O(n). dp array of size nn is used.
 */
class Solution {
    public int climbStairs(int n) {
        if (n <= 1) return 1;
        int[] wayCount = new int[n+1];
        wayCount[0] = 1;
        wayCount[1] = 1;
        for (int i = 2; i < n+1; i++) {
            wayCount[i] = wayCount[i-1] + wayCount[i-2];
        }
        return wayCount[n];
    }
}


/*
 * Official Solution: Fibonacci Number
 *
 * It can be easily analysed that dp[i] is nothing but i th fibonacci number.
 * Fib(n) = Fib(n-1) + Fib(n-2)
 *
 * Time complexity : O(n). Single loop upto nn is required to calculate n th fibonacci number.
 * Space complexity : O(1). Constant space is used.
 */
public class Solution {
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
}
