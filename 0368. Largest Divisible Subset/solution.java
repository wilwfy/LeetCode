/**
 * Other's solution of DP
 *
 * Intuition:
 * Use DP to track max Set and pre index.
 * using an array of Arraylist to store current valid subsets for every element in the original list.
 *
 * Classic DP solution similar to LIS, O(n^2)
 */
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) return new ArrayList<Integer>();
        
        Arrays.sort(nums);
        
        List<Integer>[] dp = (ArrayList<Integer>[]) new ArrayList[nums.length];
        int maxIndex = 0, maxSize = -1;
        int finalIndex = 0, finalSize = -1;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = new ArrayList<Integer>();
            for (int j = i - 1; j >= 0; j--) {
                //if nums[i] can be divided by nums[j], it can also be divided by every element in dp[j].
			    //Find a previous nums[j] that has most element in
                if (nums[i] % nums[j] == 0) {
                    if (dp[j].size() > maxSize) {
                        maxSize = dp[j].size();
                        maxIndex = j;
                    }
                }
            }

            //if maxSize not equal to -1, which means divisor for nums[i] is found, 
		    //add the one with most element in.
            if (maxSize != -1) dp[i].addAll(dp[maxIndex]);
            
            // add nums[i] itself to dp
            dp[i].add(nums[i]);
            if (dp[i].size() > finalSize) {
                finalSize = dp[i].size();
                finalIndex = i;
            }
            maxIndex = 0;
            maxSize = -1;
        }
        return dp[finalIndex];
    }
}
