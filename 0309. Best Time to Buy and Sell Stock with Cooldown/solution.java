/*
 * Other's DP solution
 *
 * There are three states, according to the action that you can take.
 * Hence, from there, you can now the profit at a state at time i as:
 *   s0[i] = max(s0[i - 1], s2[i - 1]); // Stay at s0, or rest from s2
 *   s1[i] = max(s1[i - 1], s0[i - 1] - prices[i]); // Stay at s1, or buy from s0
 *   s2[i] = s1[i - 1] + prices[i]; // Only one way from s1
 *
 * Then, you just find the maximum of s0[n] and s2[n], since they will be the maximum profit we need (No one
 * can buy stock and left with more profit that sell right :) )
 *
 * Time: O(n)
 * Space: O(n)
 */
class Solution {
    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;
        int n = prices.length;
        int[] s0 = new int[n];
        int[] s1 = new int[n];
        int[] s2 = new int[n];
        s0[0] = 0;
        s1[0] = -prices[0];
        s2[0] = 0;
        for (int i = 1; i < n; i++) {
            s0[i] = Math.max(s0[i - 1], s2[i - 1]); // Stay at s0, or rest from s2
            s1[i] = Math.max(s1[i - 1], s0[i - 1] - prices[i]); // Stay at s1, or buy from s0
            s2[i] = s1[i - 1] + prices[i]; // Only one way from s1
        }
        return Math.max(s0[n-1], s2[n-1]);
    }
}


/*
 * Other's optimized DP solution
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;

        int sold = 0, hold = Integer.MIN_VALUE, rest = 0;
        for (int i = 0; i < prices.length; i++) {
            int prevSold = sold;
            sold = hold + prices[i];
            hold = Math.max(hold, rest - prices[i]);
            rest = Math.max(rest, prevSold);
        }
        return Math.max(rest, sold);
    }
}
