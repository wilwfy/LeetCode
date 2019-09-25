/**
 * Official solution: Binary Search on Logarithms
 *
 * Time Complexity: O(NlogN), where N is the length of nums. Inside our for loop, each binary search operation takes O(logN) time.
 * Space Complexity: O(N), the space used by preLogSum.
 */
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if ((nums == null) || (nums.length == 0)) return 0;
        double logk = Math.log(k);
        double[] preLogSum = new double[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preLogSum[i+1] = preLogSum[i] + Math.log(nums[i]);
        }
        
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            int low = i + 1, high = preLogSum.length;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (preLogSum[mid] < preLogSum[i] + logk - 1e-9)
                    low = mid + 1;
                else
                    high = mid;
            }
            cnt += low - i - 1;
        }
        return cnt;
    }
}
