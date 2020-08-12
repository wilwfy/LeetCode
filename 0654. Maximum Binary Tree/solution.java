/**
 * Official Recursive solution
 *
 * Time complexity : O(n^2). The function construct is called nn times. At each level of the recursive tree, we
 *                   traverse over all the nn elements to find the maximum element. In the average case, there
 * 				           will be a logn levels leading to a complexity of O(nlogn). In the worst case, the depth of
 * 				           the recursive tree can grow upto nn, which happens in the case of a sorted numsnums array,
 * 				           giving a complexity of O(n^2).
 * 
 * Space complexity : O(n). The size of the setset can grow upto nn in the worst case. In the average case, the
 *                    size will be logn for n elements in numsnums, giving an average case complexity of O(logn)
 */
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return construct(nums, 0, nums.length);
    }
    
    private TreeNode construct(int[] nums, int left, int right) {
        if (left == right) return null;
        int maxIdx = getMaxIndex(nums, left, right);
        TreeNode root = new TreeNode(nums[maxIdx]);
        root.left = construct(nums, left, maxIdx);
        root.right = construct(nums, maxIdx + 1, right);
        return root;
    }
    
    public int getMaxIndex(int[] nums, int left, int right) {
        int maxIdx = left;
        for (int i = left; i < right; i++) {
            if (nums[i] > nums[maxIdx])
                maxIdx = i;
        }
        return maxIdx;
    }
}


/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
