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


/**
 * Solution by referring to other's solution of Binary Search + Sliding Window
 *
 * A very good discussion and explanation can be found at the post "Approach the problem using the 'trial and error' algorithm":
 *    https://leetcode.com/problems/find-k-th-smallest-pair-distance/discuss/109082/Approach-the-problem-using-the-%22trial-and-error%22-algorithm
 *
 * Another post: https://www.geeksforgeeks.org/k-th-smallest-absolute-difference-two-elements-array/
 */
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        
        int n = nums.length;
        int lo = 0, hi = nums[n-1] - nums[0];
        while (lo < hi) {
            int count = 0;
            int mi = (lo + hi) / 2;
            for (int i = 0, j = 0; i < n; i++) {
                while(j < n && (nums[j] - nums[i] <= mi)) j++;
                count += j - i - 1;
            }
            if (count < k)
                lo = mi + 1;
            else
                hi = mi;
        }
        return lo;
    }
}
