/**
 * My solution of Brute Force with Sort (accepted)
 */
class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length < 2) return 0;
        int res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] == k) {
                    res++; // since the array is sorted, so the found pair is unique and
                    break; // enough in this loop, so no need to search further
                }
            }
        }
        return res;
    }
}
