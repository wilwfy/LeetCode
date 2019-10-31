/**
 * Official solution: Binary Search + Sliding Window
 *
 * Intuition: Binary search for the answer, and we will focus on evaluating our possible function quickly.
 * Algorithm: We will use a sliding window approach to count the number of pairs with distance <= guess.
 *            For every possible right, we maintain the loop invariant: left is the smallest value such that
 *            nums[right] - nums[left] <= guess. Then, the number of pairs with right as it's right-most 
 *            endpoint is right - left, and we add all of these up.
 *
 * Time Complexity: O(NlogW+NlogN), where N is the length of nums, and W is equal to nums[nums.length - 1] - nums[0].
 *                  The logW factor comes from our binary search, and we do O(N) work inside our call to possible (or
 *                  to calculate count in Java). The final O(NlogN) factor comes from sorting.
 * Space Complexity: O(1). No additional space is used except for integer variables.
 */
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);

        int lo = 0;
        int hi = nums[nums.length - 1] - nums[0];
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            int count = 0, left = 0;
            for (int right = 0; right < nums.length; ++right) {
                while (nums[right] - nums[left] > mi) left++;
                count += right - left;
            }
            //count = number of pairs with distance <= mi
            if (count >= k) hi = mi;
            else lo = mi + 1;
        }
        return lo;
    }
}
