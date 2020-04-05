/*
 * My solution with in-place sorting
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int i = 0, j = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != 0) continue;
            j = i;
            while ((j < nums.length) && (nums[j] == 0))
                j++;
            if (j < nums.length) {
                nums[i] = nums[j];
                nums[j] = 0;
            }
        }
    }
}
