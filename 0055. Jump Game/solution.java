/**
 * My solution of One Pass
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public boolean canJump(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return false;
        if (nums.length == 1) return true;
        
        boolean res = true;
        int zeroIdx = -1;
        for (int i = nums.length-1; i >= 0; i--) {
            if (nums[i] == 0) {
                // If the previous 0 is not overcomed yet, then
                // still use the previous index of 0
                zeroIdx = res == false ? zeroIdx : i;
                res = false;
                continue;
            }
            if (zeroIdx != -1) {
                if ( (nums[i] > zeroIdx - i) || 
                     ((nums[i] == zeroIdx - i) && (zeroIdx == nums.length-1)) ) {
                    res = true;
                    zeroIdx = -1;
                }
            }
        }
        return res;
    }
}
