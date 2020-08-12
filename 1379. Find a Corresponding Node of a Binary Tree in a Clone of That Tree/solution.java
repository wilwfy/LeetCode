/**
 * My solution of Recursion
 *
 * Time: O(n). Worst case that all the nodes in the tree has only left subtree or only right subtree.
 * Space: O(n). Recursion space. Worst case that all the nodes in the tree has only left subtree or only right subtree.
 */
class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) return null;
        if (original.val == target.val) return cloned;
        TreeNode node = getTargetCopy(original.left, cloned.left, target);
        return (node != null) ? node : getTargetCopy(original.right, cloned.right, target);
    }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
