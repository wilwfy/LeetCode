/**
 * Other's DFS solution
 *
 * Time: O(n). Need walk through every node in the tree.
 * Space: O(H). H is the height of the tree which is the stack space used for recursion.
 */
class Solution {
    int len;
    public int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        len = 0;
        dfs(root);
        return len;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int leftChildLen = dfs(node.left); // Longest-Univalue-Path-Start-At - left child
        int rightChildLen = dfs(node.right); // Longest-Univalue-Path-Start-At - right child
        int leftLen = (node.left != null && node.left.val == node.val) ? leftChildLen + 1 : 0; // Longest-Univalue-Path-Start-At - node, and go left
        int rightLen = (node.right != null && node.right.val == node.val) ? rightChildLen + 1 : 0; // Longest-Univalue-Path-Start-At - node, and go right
        len = Math.max(len, leftLen + rightLen); // Longest-Univalue-Path-Across - node
        return Math.max(leftLen, rightLen);
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
