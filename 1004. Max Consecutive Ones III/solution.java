/**
 * Other's solution of Sliding Window
 *
 * Intuition
 * Translation:
 * Find the longest subarray with at most K zeros.
 * 
 * 
 * Explanation
 * For each A[j], try to find the longest subarray.
 * If A[i] ~ A[j] has zeros <= K, we continue to increment j.
 * If A[i] ~ A[j] has zeros > K, we increment i (as well as j).
 *
 * More Similar Sliding Window Problems
 * Here are some similar sliding window problems.
 * Also find more explanations.
 * Good luck and have fun.
 * 
 * - Count Number of Nice Subarrays
 * - Replace the Substring for Balanced String
 * - Max Consecutive Ones III
 * - Binary Subarrays With Sum
 * - Subarrays with K Different Integers
 * - Fruit Into Baskets
 * - Shortest Subarray with Sum at Least K
 * - Minimum Size Subarray Sum
 */
class Solution {
    public int longestOnes(int[] nums, int k) {
        int i = 0, j;
        for (j = 0; j < nums.length; ++j) {
            if (nums[j] == 0) k--;
            if (k < 0 && nums[i++] == 0) k++;
        }
        return j - i;
    }
}
