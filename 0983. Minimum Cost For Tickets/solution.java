/**
 * Official solution of DP (Day Variant, top-down, recursion, with memoization)
 *
 * Intuition and Algorithm
 * 
 * For each day, if you don't have to travel today, then it's strictly better to wait to buy a pass.
 * If you have to travel today, you have up to 3 choices: you must buy either a 1-day, 7-day, or 30-day pass.
 * 
 * We can express those choices as a recursion and use dynamic programming. Let's say dp(i) is the cost to
 * fulfill your travel plan from day i to the end of the plan. Then, if you have to travel today, your cost is:
 * 
 * dp(i) = min(dp(i+1) + costs[0], dp(i+7) + costs[1], dp(i+30) + costs[2])
 *
 * Time Complexity: O(W), where W = 365 is the maximum numbered day in your travel plan.
 * Space Complexity: O(W).
 */
class Solution {
    int[] costs;
    Integer[] memo;
    Set<Integer> dayset;
    
    public int mincostTickets(int[] days, int[] costs) {
        this.costs = costs;
        memo = new Integer[366];
        dayset = new HashSet<>();
        for (int d: days) dayset.add(d);
        // top-down DP with Memoization
        return dp(1); // calculation starts from day 1
    }
    
    public int dp(int i) { // i is the travel day
        if (i > 365) return 0;
        if (memo[i] != null) return memo[i];
        
        int ans;
        if (dayset.contains(i)) {
            ans = Math.min(dp(i + 1) + costs[0], dp(i + 7) + costs[1]);
            ans = Math.min(ans, dp(i + 30) + costs[2]);
        } else {
            ans = dp(i + 1);
        }
        
        memo[i] = ans;
        return ans;
    }
}


/**
 * Official solution of DP (Window Variant, top-down, recursion, with memoization)
 *
 * Intuition and Algorithm
 * 
 * As in Approach 1, we only need to buy a travel pass on a day we intend to travel.
 * 
 * Now, let dp(i) be the cost to travel from day days[i] to the end of the plan.
 * If say, j1 is the smallest index such that days[j1] > days[i] + 1, j7 is the
 * smallest index such that days[j7] > days[i] + 7, and j30 is the smallest index
 * such that days[j30] > days[i] + 30, then we have:
 * 
 * dp(i) = min(dp(j1) + costs[0], dp(j7) + costs[1], dp(j30) + costs[2])
 *
 * Time Complexity: O(N), where N is the number of unique days in your travel plan.
 * Space Complexity: O(N).
 */
class Solution {
    int[] days, costs;
    Integer[] memo;
    int[] durations = new int[]{1, 7, 30};
    
    public int mincostTickets(int[] days, int[] costs) {
        this.days = days;
        this.costs = costs;
        memo = new Integer[days.length];
        return dp(0); // calculation starts from element 0 of days array
    }
    
    public int dp(int i) { // i is the index of the days array
        if (i >= days.length) return 0;
        if (memo[i] != null) return memo[i];
        
        int ans = Integer.MAX_VALUE;
        int j = i;
        for (int k = 0; k < 3; ++k) {
            while (j < days.length && days[j] < days[i] + durations[k])
                j++;
            ans = Math.min(ans, dp(j) + costs[k]); // we have taken costs[k] to cover the days before days[j], so need
                                                   // furtherly calculate the costs from days[j] to the last travel day
        }
        
        memo[i] = ans;
        return ans;
    }
}
