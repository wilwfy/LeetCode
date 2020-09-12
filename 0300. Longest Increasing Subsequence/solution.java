/**
 * Official solution of Recursion with Memoization
 * 
 * Algorithm
 * 
 * Store the results obtained for a particular call in a 2-d memoization array memomemo.
 * memo[i][j]memo[i][j] represents the length of the LIS possible using nums[i]nums[i] as
 * the previous element considered to be included/not included in the LIS, with nums[j]nums[j]
 * as the current element considered to be included/not included in the LIS. Here, numsnums
 * represents the given array.
 *
 * Time complexity : O(n^2). Size of recursion tree can go upto n^2.
 * Space complexity : O(n^2). memomemo array of n*n is used.
 */
public class Solution {
    
    public int lengthOfLIS(int[] nums) {
        int memo[][] = new int[nums.length + 1][nums.length];
        for (int[] l : memo) {
            Arrays.fill(l, -1);
        }
        return lengthofLIS(nums, -1, 0, memo);
    }
    
    public int lengthofLIS(int[] nums, int previndex, int curpos, int[][] memo) {
        if (curpos == nums.length) {
            return 0;
        }
        if (memo[previndex + 1][curpos] >= 0) {
            return memo[previndex + 1][curpos];
        }
        int taken = 0;
        if (previndex < 0 || nums[curpos] > nums[previndex]) {
            taken = 1 + lengthofLIS(nums, curpos, curpos + 1, memo);
        }

        int nottaken = lengthofLIS(nums, previndex, curpos + 1, memo);
        memo[previndex + 1][curpos] = Math.max(taken, nottaken);
        return memo[previndex + 1][curpos];
    }
}


/**
 * Official solution of Dynamic Programming
 * 
 * Algorithm
 * 
 * This method relies on the fact that the longest increasing subsequence possible upto the i^{th} index
 * in a given array is independent of the elements coming later on in the array. Thus, if we know the length
 * of the LIS upto i^{th} index, we can figure out the length of the LIS possible by including the (i+1)^{th}
 * element based on the elements with indices j such that 0≤j≤(i+1).
 * 
 * We make use of a dp array to store the required data. dp[i] represents the length of the longest increasing
 * subsequence possible considering the array elements upto the i^{th} index only ,by necessarily including the
 * i^{th} element. In order to find out dp[i], we need to try to append the current element(nums[i]) in every
 * possible increasing subsequences upto the (i-1)^{th} index(including the (i-1)^{th} index), such that the new
 * sequence formed by adding the current element is also an increasing subsequence. Thus, we can easily determine
 * dp[i] using:
 * 
 * dp[i] = max(dp[j]) + 1, ∀ 0 ≤ j <i
 * 
 * At the end, the maximum out of all the dp[i]'s to determine the final result.
 * 
 * LIS_{length} = max(dp[i]), ∀ 0 ≤ i < n
 * 
 * Time complexity : O(n^2). Two loops of n are there.
 * Space complexity : O(n). dp array of size n is used.
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxans = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            dp[i] = maxval + 1;
            maxans = Math.max(maxans, dp[i]);
        }
        return maxans;
    }
}
