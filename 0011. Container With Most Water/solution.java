/**
 * Official solution of 2 pointers
 *
 * The intuition behind this approach is that the area formed between the lines will always be limited by the height of 
 * the shorter line. Further, the farther the lines, the more will be the area obtained.
 * We take two pointers, one at the beginning and one at the end of the array constituting the length of the lines. Futher,
 * we maintain a variable \text{maxarea}maxarea to store the maximum area obtained till now. At every step, we find out the
 * area formed between them, update \text{maxarea}maxarea and move the pointer pointing to the shorter line towards the other
 * end by one step.
 *
 * Time complexity : O(n). Single pass.
 * Space complexity : O(1). Constant space is used.
 */
class Solution {
    public int maxArea(int[] height) {
        int l = 0, r = height.length - 1, maxArea = 0;
        while (l < r) {
            maxArea = Math.max(maxArea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r])
                l++;
            else
                r--;
        }
        return maxArea;
    }
}
