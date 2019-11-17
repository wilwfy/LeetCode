/**
 * Other's solution of Binary Search
 *
 * Time Complexity : Same as Binary Search O(logN)
 * Space Complexity : O(1)
 */
class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;
		
		// loop invariant: 1. low < high
        //                 2. mid != high and thus A[mid] != A[high] (no duplicate exists)
        //                 3. minimum is between [low, high]
        // The proof that the loop will exit: after each iteration either the 'high' decreases
        // or the 'low' increases, so the interval [low, high] will always shrink.
        while (low < high) {
            if (nums[low] < nums[high])
                return nums[low];
            
            int mid = (low + high) / 2;
            
            // The Binary Search basically split the range into two different part, one is [lo, mid], the one is [mid + 1, high],
            // therefore we change the next lo equals to mid + 1, or the high equals to mid, depending on the search.
            if (nums[mid] < nums[0]) {
                high = mid;
            } else if (nums[mid] >= nums[0]) {
                low = mid + 1;
            }
        }
        return nums[low];
    }
}
