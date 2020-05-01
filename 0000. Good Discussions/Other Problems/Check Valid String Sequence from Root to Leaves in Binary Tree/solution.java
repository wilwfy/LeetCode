/**
 * My solution with Recursion
 */
class Solution {
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return helper(root, arr, 0);
    }
    
    public boolean helper(TreeNode node, int[] arr, int idx) {
        if (idx < arr.length - 1) {
            if (node == null) return false;
            if (node.val != arr[idx]) return false;
            boolean left = helper(node.left, arr, idx+1);
            boolean right = helper(node.right, arr, idx+1);
            return left || right;
        } else if (idx == arr.length - 1) {
            if ((node != null) && (node.val == arr[idx]) && (node.left == null) && (node.right == null))
                return true;
            else
                return false;
        } else
            return false;
    }
}


/**
 * Huaigu's solution with Recursion
 */
class Solution {
    public boolean isValidSequence(TreeNode root, int[] arr) {
        return isValidSequence(root, arr, 0);
    }
    
    private boolean isValidSequence(TreeNode root, int[] arr, int index) {
        if (root == null) {
            return false;
        }
        if (index == arr.length) {
            return false;
        }
        if (root.val != arr[index]) {
            return false;
        }
        if (index == arr.length - 1) {
            if (root.left == null && root.right == null) {
                return true;
            }
            return false;
        }
        boolean left = isValidSequence(root.left, arr, index + 1);
        boolean right = isValidSequence(root.right, arr, index + 1);
        return left || right;
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
