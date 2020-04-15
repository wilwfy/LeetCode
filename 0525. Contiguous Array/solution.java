/*
 * Official Brute Force solution
 *
 * Time complexity : O(n^2). We consider every possible subarray by traversing over the complete array for every start point possible.
 * Space complexity : O(1). Only two variables zeroeszeroes and onesones are required.
 */
public class Solution {

    public int findMaxLength(int[] nums) {
        int maxlen = 0;
        for (int start = 0; start < nums.length; start++) {
            int zeroes = 0, ones = 0;
            for (int end = start; end < nums.length; end++) {
                if (nums[end] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
                if (zeroes == ones) {
                    maxlen = Math.max(maxlen, end - start + 1);
                }
            }
        }
        return maxlen;
    }
}
