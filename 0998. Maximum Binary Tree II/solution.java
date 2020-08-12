/**
 * My solution of Recursion
 *
 * Time: O(n)
 * Space: O(n). Recursion space.
 */
class Solution {
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) return newNode;
        return helper(root, newNode);
    }
    
    public TreeNode helper(TreeNode node, TreeNode newNode) {
        if (node.val < newNode.val) {
            newNode.left = node;
            return newNode;
        }
        if (node.right == null) {
            node.right = newNode;
        } else {
            if (node.right.val < newNode.val) {
                newNode.left = node.right;
                node.right = newNode;
            } else {
                helper(node.right, newNode);
            }
        }
        return node;
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
