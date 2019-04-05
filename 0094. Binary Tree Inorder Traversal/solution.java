/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
// Recursive Solution
class Solution {
    List<Integer> inorder_nodes = new ArrayList<>();
    
    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return inorder_nodes;
        if (root.left != null) inorderTraversal(root.left);
        inorder_nodes.add(root.val);
        if (root.right != null) inorderTraversal(root.right);
        return inorder_nodes;
    }
}
