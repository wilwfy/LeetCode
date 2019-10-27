/**
 * Official solution based on Dynamic Programming.
 *
 * Intuition:
 *           In brute force, we iterate over the left and right parts again and again just to find the highest bar size upto
 *           that index. But, this could be stored. Voila, dynamic programming.
 * Algorithm:
 *           Find maximum height of bar from the left end upto an index i in the array left_max.
 *           Find maximum height of bar from the right end upto an index i in the array right_max.
 *           Iterate over the height array and update ans: Add min(max_left[i], max_right[i]) − height[i] to ans
 *
 * Time complexity: O(n).
 *                  We store the maximum heights upto a point using 2 iterations of O(n)O(n) each.
 *                  We finally update \text{ans}ans using the stored values in O(n)O(n).
 * Space complexity: O(n) extra space.
 *                   Additional O(n) space for left_max and right_max arrays.
 */
class Solution {
    public int trap(int[] height) {
        if ((height == null) || (height.length == 0)) return 0;
        int res = 0;
        int len = height.length;
        
        int[] maxLeft = new int[len];
        maxLeft[0] = height[0];
        for (int i = 1; i < len; i++) {
            maxLeft[i] = Math.max(maxLeft[i-1], height[i]);
        }
        
        int[] maxRight = new int[len];
        maxRight[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i+1], height[i]);
        }
        
        // The bars at both ends can not retain water
        //for (int i = 0; i < len; i++) {
        for (int i = 1; i < len - 1; i++) {
            res += Math.max(Math.min(maxLeft[i], maxRight[i]) - height[i], 0);
        }
        return res;
    }
}
