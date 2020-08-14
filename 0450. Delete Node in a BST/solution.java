/**
 * Other's solution of Recursion
 */
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else { // Now find the target node to be removed
            // if the target doesn't have a left child
            if (root.left == null) return root.right;
            
            // if the target doesn't have a right child
            if (root.right == null) return root.left;
            
            // if the target has two children
            if(root.right.left == null) {
                root.right.left = root.left;
                return root.right;
            } else {
                TreeNode rightSmallest = deleteSmallest(root.right);
                rightSmallest.left = root.left;
                rightSmallest.right = root.right;
                return rightSmallest;
            }
        }
        return root;
    }
    
    private TreeNode deleteSmallest(TreeNode node) {
        TreeNode cur = node.left;       
        TreeNode pre = node;
        while(cur.left != null) {
            pre = cur;
            cur = cur.left;
        }
        pre.left = cur.right;
        return cur;
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
