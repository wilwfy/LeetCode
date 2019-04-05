/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // Using BST InOrder Traversal because it will access all the nodes like a sorted array.
    Integer min_diff = Integer.MAX_VALUE;
    Integer pre_val = null;
    public int minDiffInBST(TreeNode root) {        
        if (root.left != null) {
            minDiffInBST(root.left);
        }
        
        if (pre_val != null) {
            min_diff = Math.min(min_diff, (root.val - pre_val));
        }
        
        pre_val = root.val;
        if (root.right != null) {
            minDiffInBST(root.right);
        }
        return min_diff;
    }
}
