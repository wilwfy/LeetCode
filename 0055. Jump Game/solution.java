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


/**
 * Other's solution of Greedy
 *
 * Time: O(n)
 * Space: O(1)
 */
class Solution {
    public boolean canJump(int[] nums) {
        if ((nums == null) || (nums.length == 0)) return false;
        if (nums.length == 1) return true;
        
        // work backwards from the last index. Keep track of the smallest index that
        // can "jump" to the last index. Check whether the current index can jump to
        // this smallest index.
        int n = nums.length, last = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= last) last = i;
        }
        return last <= 0;
    }
}


/**
 * Official solution of Greedy
 *
 * Time: O(n)
 * Space: O(1)
 */
public class Solution {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }
}
