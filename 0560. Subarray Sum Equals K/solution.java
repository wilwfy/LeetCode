/**
 * My solution
 *
 * Time complexity : O(n^2). We need to consider every subarray possible.
 * Space complexity : O(1). Constant space is used.
 */
class Solution {
    public int subarraySum(int[] nums, int k) {
        if ((nums == null) || (nums.length == 0)) return 0;
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) res++;
            }
        }
        return res;
    }
}
