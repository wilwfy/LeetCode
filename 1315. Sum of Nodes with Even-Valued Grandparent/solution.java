/**
 * My solution of DFS
 *
 * Time: O(n)
 * Space: O(height)
 */
class Solution {
    private int sum;
    
    public int sumEvenGrandparent(TreeNode root) {
        sum = 0;
        dfs(root, false, false);
        return sum;
    }
    
    private void dfs(TreeNode node, boolean parentIsEven, boolean gradpIsEven) {
        if (node == null) return;
        if (gradpIsEven) sum += node.val;
        boolean isEven = (node.val % 2 == 0);
        dfs(node.left, isEven, parentIsEven);
        dfs(node.right, isEven, parentIsEven);
    }
}


/**
 * Other's solution of DFS
 *
 * Intuition
 * Let children know who their grandparent is.
 * 
 * 
 * Explanation
 * Assume node has parent.val = 1 and grandparent.val = 1.
 * Recursive iterate the whole tree and pass on the value of parent and grandparent.
 * Count the node.val when grandparant is even-valued
 *
 *
 * Time: O(n)
 * Space: O(height)
 */
class Solution {
    public int sumEvenGrandparent(TreeNode root) {
        return helper(root, 1, 1);
    }

    public int helper(TreeNode node, int p, int gp) {
        if (node == null) return 0;
        return helper(node.left, node.val, p) + helper(node.right, node.val, p) + (gp % 2 == 0 ? node.val : 0);
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
