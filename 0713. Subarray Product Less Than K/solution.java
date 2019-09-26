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


/**
 * Official and Other's Solution: Sliding Window with Two Pointers
 *
 * The idea is always keep an max-product-window less than K;
 * Every time shift window by adding a new number on the right(j), if the product is greater than k, 
 * then try to reduce numbers on the left(i), until the subarray product fit less than k again, (subarray could be empty);
 * Each step introduces x new subarrays, where x is the size of the current window (j + 1 - i);
 *
 * Time Complexity: O(N), where N is the length of nums. pointer2 can only be incremented at most N times.
 * Space Complexity: O(1)
 */
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int cnt = 0, prod = 1;
        for (int pointer1 = 0, pointer2 = 0; pointer1 < nums.length; pointer1++) {
            prod *= nums[pointer1];
            while (prod >= k && pointer2 <= pointer1) {
                prod /= nums[pointer2++];
            }
            cnt += pointer1 - pointer2 + 1;
        }
        return cnt;
    }
}
