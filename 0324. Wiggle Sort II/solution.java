/*
 * Other's solution of extra space
 *
 * Time: O(nlogn)
 * Space: O(n)
 */
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums.length < 2) return;
        
        Arrays.sort(nums);
        int[] tmp = Arrays.copyOf(nums, nums.length);
        
        int len = nums.length, small = (len - 1)/2, big = len - 1;
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (i % 2 == 0) ? tmp[small--] : tmp[big--];
        }
    }
}

