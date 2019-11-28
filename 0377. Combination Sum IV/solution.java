/**
 * Solution of Backtracking, but Time Limit Exceeded
 */
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) return 0;
        Arrays.sort(nums);
        if (nums[0] > target) return 0;
        
        //int res = 0;
        //res = backtrack(nums, target);
        //return res;
        return backtrack(nums, target);
    }
    
    public int backtrack(int[] nums, int target) {
        int count = 0;
        if (target < 0)
            return 0;
        else if (target == 0) {
            return 1;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (target - nums[i] < 0) break;
                
                count += backtrack(nums, target - nums[i]);
            }
            return count;
        }
    }
}


/**
 * Recursive Solution, but Time Limit Exceeded
 */
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int count = 0;
        if (target < 0)
            return 0;
        else if (target == 0) {
            return 1;
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (target - nums[i] < 0) break;
                
                count += combinationSum4(nums, target - nums[i]);
            }
        }
        return count;
    }
}


/**
 * Solution of Dynamic Programming - Top Down
 */
class Solution {
    private int[] dp;
    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) return 0;

        dp = new int[target+1];
        // 0 is not a good choice for initialization because it means
        // there is no combination sum for the target.
        Arrays.fill(dp, -1);
        
        // when target == 0, we think we find a combination which is one solution
        dp[0] = 1;
        
        helper(nums, target);
        return dp[target];
    }
    
    public int helper(int[] nums, int target) {
        if (dp[target] != -1)
            return dp[target];
        
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (target - nums[i] < 0) continue;
                
            count += helper(nums, target - nums[i]);
        }
        
        dp[target] = count;
        return count;
    }
}


/**
 * Solution of Dynamic Programming - Bottom Up
 */
class Solution {

    public int combinationSum4(int[] nums, int target) {
        if (nums.length == 0) return 0;

        int[] dp = new int[target+1];
        
        // when target == 0, we think we find a combination which is one solution
        dp[0] = 1;
        
        for (int t = 1; t <= target; t++) {
            for (int i = 0; i < nums.length; i++) {
                if (t - nums[i] < 0) continue;
                
                dp[t] += dp[t - nums[i]];
            }
        }
        
        return dp[target];
    }
}
