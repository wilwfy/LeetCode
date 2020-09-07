/**
 * My solution of DFS
 */
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        return dfs(root) ? root : null;
    }
    
    private boolean dfs(TreeNode node) {
        if (node == null) return false;
        
        boolean leftHasOne = dfs(node.left);
        if (!leftHasOne) node.left = null;
        boolean rightHasOne = dfs(node.right);
        if (!rightHasOne) node.right = null;
        return (!leftHasOne && !rightHasOne && node.val == 0) ? false : true;
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
