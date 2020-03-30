class Solution {
    public int maxProfit(int[] prices) {
        if ((prices == null) || (prices.length == 0)) return 0;
        int low, high, pre, best_profit, cur_profit;
        pre = low = high = prices[0];
        best_profit = cur_profit = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] < pre) {
                if (prices[i] < low) low = prices[i];
            } else {
                cur_profit = prices[i] - low;
            }
            if (cur_profit > best_profit) {
                high = prices[i];
                best_profit = cur_profit;
            }
            pre = prices[i];
        }
        return best_profit;
    }
}


/*
 * Official solution
 * We need to find the largest peak following the smallest valley. We can maintain two
 * variables - minprice and maxprofit corresponding to the smallest valley and maximum
 * profit (maximum difference between selling price and minprice) obtained so far respectively.
 */
public class Solution {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}


/*
 * Other's solution of Kadane's Algorithm
 *
 * Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original array, and find a contiguous
 * subarray giving maximum profit. If the difference falls below 0, reset it to zero.
 *
 * Suppose we have original array:
 *   [a0, a1, a2, a3, a4, a5, a6]
 * what we are given here(or we calculate ourselves) is:
 *   [b0, b1, b2, b3, b4, b5, b6]
 * where,
 *   b[i] = 0, when i == 0
 *   b[i] = a[i] - a[i - 1], when i != 0
 * suppose if a2 and a6 are the points that give us the max difference (a2 < a6)
 * then in our given array, we need to find the sum of sub array from b3 to b6.
 *   b3 = a3 - a2
 *   b4 = a4 - a3
 *   b5 = a5 - a4
 *   b6 = a6 - a5
 * adding all these, all the middle terms will cancel out except two
 * i.e.
 *   b3 + b4 + b5 + b6 = a6 - a2
 *   a6 - a2 is the required solution.
 * so we need to find the largest sub array sum to get the most profit
 */
public class Solution {
    public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
            maxSoFar = Math.max(maxCur, maxSoFar);
        }
        return maxSoFar;
    }
}
