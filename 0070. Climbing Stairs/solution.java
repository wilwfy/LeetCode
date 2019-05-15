/*
 * Brute-force solution
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
