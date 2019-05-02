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
