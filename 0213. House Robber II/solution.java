class Solution {
    public int rob(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int n = nums.length;
        int[] dp = new int[n];
        // can not rob house 0 and house n-1 both
        
        // Round 1 to find the max value robbed from house 0 to house n-2
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n-1; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        int res = dp[n-2];
        // Round 2 to find the max value robbed from house 1 to house n-1
        dp[1] = nums[1];
        dp[2] = Math.max(nums[1], nums[2]);
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        // Return the greater result between these 2 rounds
        return Math.max(res, dp[n-1]);
    }
}
