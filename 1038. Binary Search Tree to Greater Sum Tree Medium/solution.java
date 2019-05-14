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
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;
        helper(root, 0);
        return root;
    }
    
    public int helper(TreeNode node, int sumOfGreater) {
        if (node == null) return 0;
        int sum = 0;
        if (node.right != null)
            sum += helper(node.right, sumOfGreater);
        else
            sum += sumOfGreater;
        node.val += sum;
        if (node.left != null)
            return helper(node.left, node.val);
        else
            return node.val;
    }
}


