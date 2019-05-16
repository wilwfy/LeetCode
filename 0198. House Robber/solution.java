/*
 * The space complexity could be improved to O(1) since only a 3-element window of dp[ ] array is needed.
 * It can be done by value shifting between the dp[0], dp[1] and dp[2] in the for loop. Then finally return dp[2].
 */
class Solution {
    public int rob(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // dp[i] denotes the maximum amount of money could be when
        // checking the i th house to be robbed or not. And it depends
        // on if previous possible maximum amount at adjacent i-1 th house
        // is greater than the sum of money in i th house and the possible
        // maximum amount at the nonadjacent i-2 house.
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
    }
}
