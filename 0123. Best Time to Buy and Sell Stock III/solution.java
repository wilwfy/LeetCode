/*
 * Refer to other's Dynamic Programming solution
 *
 * For instance, in the case 3,3,5,0,0,3,1,4:
 * the max profit is (4 - 1) + (3 - 0), which is also equal to 4 - (1 - (3 - 0))
 * '0' is the min of cost1, (3 - 0) is the profit1, (1 - (3 - 0)) is the cost2, and 4 - (1 - (3 - 0)) is the profit2.
 * this also works for the increasing sequence, say, 1,2,3,4, the max profit is 4 - (4 - (4 - 1))
 * in order to get the max profit eventually, profit1 must be as relatively most as possible to produce a small cost2,
 * and therefore cost2 can be as less as possible to give us the final max profit2. So now we understand why and where
 *  we need to take min or max of all cost and profit variables.....
 */
class Solution {
    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;
        int cost1 = Integer.MAX_VALUE, cost2 = Integer.MAX_VALUE;
        int profit1 = 0, profit2 = 0;
        for (int p: prices) {
            cost1 = Math.min(p, cost1);
            profit1 = Math.max(p - cost1, profit1);
            cost2 = Math.min(p - profit1, cost2);
            profit2 = Math.max(p - cost2, profit2);
        }
        return profit2;
    }
}


/*
 * More DP solution from others
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int hold1 = Integer.MIN_VALUE, hold2 = Integer.MIN_VALUE;
        int release1 = 0, release2 = 0;
        for(int i:prices){                              // Assume we only have 0 money at first
            release2 = Math.max(release2, hold2+i);     // The maximum if we've just sold 2nd stock so far.
            hold2    = Math.max(hold2,    release1-i);  // The maximum if we've just buy  2nd stock so far.
            release1 = Math.max(release1, hold1+i);     // The maximum if we've just sold 1nd stock so far.
            hold1    = Math.max(hold1,    -i);          // The maximum if we've just buy  1st stock so far. 
        }
        return release2; ///Since release1 is initiated as 0, so release2 will always higher than release1.
    }
}
