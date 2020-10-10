/**
 * Official solution of Binary Search
 *
 * Time complexity : O(logN).
 *                   Let's compute time complexity with the help of master theorem T(N)=aT(N/b)+Θ(N^d).
 *                   The equation represents dividing the problem up into aa subproblems of size N/b in Θ(N^d) time.
 *                   Here at step there is only one subproblem a = 1, its size is a half of the initial problem b = 2,
 *                   and all this happens in a constant time d = 0. That means that logb(a)=d and hence we're dealing
 *                   with case 2 that results in O(n^logb(a) * log(d+1) * N) = O(logN) time complexity.
 * Space complexity : O(1) since it's a constant space solution.
 */
class Solution {
    public int search(int[] nums, int target) {
        int pivot, left = 0, right = nums.length - 1;
        while (left <= right) {
          pivot = left + (right - left) / 2;
          if (nums[pivot] == target) return pivot;
          if (target < nums[pivot]) right = pivot - 1;
          else left = pivot + 1;
        }
        return -1;
    }
}


/**
 * My solution of Binary Search
 *
 * Time: O(logN)
 * Space: O(1)
 */
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else
                return mid;
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
}
