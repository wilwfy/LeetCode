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


/*
 * Other's solution
 *
 * Shift non-zero values as far forward as possible, fill remaining space with zeros.
 *
 * Time Complexity: O(n). However, the total number of operations are still sub-optimal. The total operations (array writes) that
 *                  code does is n (Total number of elements).
 * Space Complexity: O(1). Only constant space is used.
 */
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;        
    
        int insertPos = 0;
        for (int num: nums) {
            if (num != 0) nums[insertPos++] = num;
        }        
    
        while (insertPos < nums.length) {
            nums[insertPos++] = 0;
        }
    }
}


/*
 * Other's solution
 *
 * Set leftMostZeroIndex to 0. Iterate through the array, at each iteration i, if nums[i] != 0 and i > leftMostZeroIndex, replace
 * the leftmost zero element nums[leftMostZeroIndex] with nums[i], and set nums[i] to 0.
 *
 * Time Complexity: O(n). 
 * Space Complexity: O(1). Only constant space is used.
 */
class Solution {
    public void moveZeroes(int[] nums) {
        int leftMostZeroIndex = 0; // The index of the leftmost zero
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i > leftMostZeroIndex) { // There are zero(s) on the left side of the current non-zero number, swap!
                    nums[leftMostZeroIndex] = nums[i];
                    nums[i] = 0;
                }
                leftMostZeroIndex++;
            }
        }
    }
}
