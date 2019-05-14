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


/*
 * Other's solution
 *
 * We need to do the work from biggest to smallest, right to left.
 * pre will record the previous value the we get, which the total sum of bigger values.
 * For each node, we update root.val with root.val + pre.
 */
class Solution {
    public TreeNode bstToGst(TreeNode root) {
        if (root == null) return null;
        helper(root, 0);
        return root;
    }
    
    int pre = 0;
    public TreeNode bstToGst(TreeNode root) {
        if (root.right != null) bstToGst(root.right);
        pre = root.val = pre + root.val;
        if (root.left != null) bstToGst(root.left);
        return root;
    }
}
