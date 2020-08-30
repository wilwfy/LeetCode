/**
 * My solution with Deque
 *
 * Similiar to the solution for 862. Shortest Subarray with Sum at Least K
 *
 * Time: O(N)
 * Space: O(N)
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] preSum = new int[nums.length + 1];
        preSum[0] = nums[0];
        for (int i = 0; i < preSum.length - 1; i++)
            preSum[i + 1] = preSum[i] + nums[i];
        
        int ans = nums.length + 1;
        Deque<Integer> deq = new LinkedList<>();
        for (int i = 0; i < preSum.length; i++) {
            while (deq.size() > 0 && preSum[i] - preSum[deq.getFirst()] >= s)
                ans = Math.min(ans, i - deq.pollFirst());
            
            deq.addLast(i);
        }
        return (ans < nums.length + 1) ? ans : 0;
    }
}


/**
 * Official solution of Two Pointers
 *
 * Intuition
 * 
 * Until now, we have kept the starting index of subarray fixed, and found the last position.
 * Instead, we could move the starting index of the current subarray as soon as we know that
 * no better could be done with this index as the starting index. We could keep 2 pointer, one
 * for the start and another for the end of the current subarray, and make optimal moves so as
 * to keep the sum greater than s as well as maintain the lowest size possible.
 * 
 * Algorithm
 * 
 * - Initialize left pointer to 0 and sum to 0
 * - Iterate over the nums:
 *   -- Add nums[i] to sum
 *   -- While sum is greater than or equal to s:
 *      --- Update ans = min(ans, i+1−left), where i+1−left is the size of current subarray
 *      --- It means that the first index can safely be incremented, since, the minimum subarray
 *          starting with this index with sum ≥ s has been achieved
 *      --- Subtract nums[left] from sum and increment left
 *
 * Time complexity: O(n). Single iteration of O(n).
 *                  Each element can be visited atmost twice, once by the right pointer(i) and
 *                  (atmost)once by the left pointer.
 * Space complexity: O(1) extra space. Only constant space required for left, sum, ans and i.
 */
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int sum = 0, left = 0;
        int ans = nums.length + 1; // nums.length + 1 is impossible
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                ans = Math.min(ans, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return (ans < nums.length + 1) ? ans : 0;
    }
}
