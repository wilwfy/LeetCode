/**
 * My solution of DFS
 *
 * Time: O(H). H is the height of the tree
 * Sapce: O(1)
 */
class Solution {
    int res = 0;
    int pathValue = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return res;
    }
    
    public void dfs(TreeNode node) {
        if (node != null) pathValue *= 10;
        if (node.left == null && node.right == null) {
            res += pathValue + node.val;
            return;
        }
        pathValue += node.val;
        int curPathValue = pathValue; // record current path value
        if (node.left != null) dfs(node.left);
        pathValue = curPathValue; // resume current path value before search right subtree
        if (node.right != null) dfs(node.right);
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
