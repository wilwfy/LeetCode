/**
 * Other's solution of Greedy with Two Pointers
 *
 */
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int j = 0, count = 0, res = 0;
        
        for(int i = 0; i < nums.length; i++){
            if(nums[i] >= left && nums[i] <= right){
                res += i - j + 1;
                count = i - j + 1;
            } else if(nums[i] < left){
                res += count;
            } else {
                j = i + 1;
                count = 0;
            }
        }
        return res;
    }
}


/**
 * Other's solution of DP
 *
 * Suppose dp[i] denotes the max number of valid sub-array ending with num[i]. We use following example
 * to illustrate the idea:
 * 
 * num = [2, 1, 4, 2, 3], L = 2, R = 3
 * 
 * if num[i] < L
 * For example, i = 1. We can only append num[i] to a valid sub-array ending with num[i-1] to create new
 * sub-array. So we have dp[i] = dp[i-1] (for i > 0)
 * 
 * if num[i] > R:
 * For example, i = 2. No valid sub-array ending with num[i] exist. So we have dp[i] = 0.
 * We also record the position of the invalid number 4 here as prev.
 * 
 * if L <= num[i] <= R
 * For example, i = 4. In this case any sub-array starts after the previous invalid number to num[i]
 * (num[prev+1..i], num[prev+2..i]) is a new valid sub-array. So dp[i] += i - prev
 * 
 * Finally the sum of the dp array is the solution. Meanwhile, notice dp[i] only relies on dp[i-1]
 * (and also prev), we can reduce the space complexity to O(1)
 */
class Solution {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int res = 0, dp = 0;
        int prev_invalid_idx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < left)
                res += dp;
            if (nums[i] > right) {
                dp = 0;
                prev_invalid_idx = i;
            }
            if (nums[i] >= left && nums[i] <= right) {
                dp = i - prev_invalid_idx;
                res += dp;
            }
        }
        return res;
    }
}
