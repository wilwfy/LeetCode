/**
 * My solution of DFS
 *
 * Time: O(N)
 * Space: O(Height). Recursion space.
 */
class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        return dfs(root, 0, limit) ? null : root;
    }
    
    private boolean dfs(TreeNode node, int sum, int limit) {
        if (node == null) return true; // base case. A null node can be deleted
        sum += node.val;
        if (node.left == null && node.right == null)
            return (sum < limit);
        boolean removeLeft = dfs(node.left, sum, limit);
        boolean removeRight = dfs(node.right, sum, limit);
        if (removeLeft) node.left = null;
        if (removeRight) node.right = null;
        return removeLeft && removeRight;
    }
}


/**
 * Other's solution of DFS
 *
 * Intuition
 * If root is leaf,
 * we compare the limit and root.val,
 * and return the result directly.
 * 
 * If root is not leaf,
 * we recursively call the function on root's children with limit = limit - root.val.
 * 
 * Note that if a node become a new leaf,
 * it means it has no valid path leading to an original leaf,
 * we need to remove it.
 *
 * Time: O(N)
 * Space: O(Height). Recursion space.
 */
class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null)
            return root.val < limit ? null : root;
        root.left = sufficientSubset(root.left, limit - root.val);
        root.right = sufficientSubset(root.right, limit - root.val);
        return root.left == root.right ? null : root;
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
