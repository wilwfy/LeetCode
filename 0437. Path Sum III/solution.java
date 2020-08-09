/**
 * Other's solution of DFS with Brute Force
 *
 * Time: O(n^2) in worst case (no branching);
 *       O(nlogn) in best case (balanced tree).
 * Space: O(h) due to recursion and h is the height of the tree.
 */
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        int restSum = sum - node.val;
        return (restSum == 0 ? 1 : 0) + pathSumFrom(node.left, restSum) + pathSumFrom(node.right, restSum);
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
