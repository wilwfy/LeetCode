/**
 * Official solution of Single Pass Approach
 *
 * Time complexity : O(n). In worst case, only two scans of the whole array are needed.
 * Space complexity : O(1). No extra space is used. In place replacements are done.
 */
class Solution {
    public void nextPermutation(int[] nums) {
        //find the first pair of two successive numbers a[i] and a[i-1], from the right,
        //which satisfy a[i] > a[i-1]
        int i = nums.length - 2;
        for (; i >= 0; i--) {
            if (nums[i] < nums[i+1])
                break;
        }
        
        if (i < 0)
            // did not find such pair, so the whole array is in descending order,
            // just reverse it
            reverse(nums, 0);
        else {
            // We want to create the permutation just larger than the current one. Therefore,
            // we need to replace the number a[i-1] with the number which is just larger than
            // itself among the numbers lying to its right section
            int j = nums.length - 1;
            for (; j > i; j--) {
                if (nums[j] > nums[i]) break;
            }
            swap(nums, i, j);
            
            // We need the smallest permutation that can be formed by using the numbers only to the
            // right of a[i-1]. Therefore, we need to place those numbers in ascending order to get
            // their smallest permutation. And because all numbers to the right of a[i-1] were already
            // sorted in descending order, so we simply need to reverse the numbers following a[i-1]
            // to get the next smallest lexicographic permutation.
            reverse(nums, i+1);
        }
    }
    
    public void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
    
    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
